import java.io.*;
import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;

public class BookStoreImpl extends UnicastRemoteObject implements BookStoreInterface {

   private String name;

   public BookStoreImpl(String s) throws RemoteException{
      super();
      name = s;
   }

   //method to download requested book store file 
   public byte[] downloadBookFile(String fileName){
      try {
         System.out.println("Sending Book File... ");
         File file = new File(fileName);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer,0,buffer.length);
         input.close();
         return(buffer);
      } catch(Exception e){
         System.out.println("BookStoreImpl: "+e.getMessage());
         e.printStackTrace();
         return(null);
      }
   } 

   //method to display the client's total cost of order
   public String displayTotal(double cost){
      System.out.println("Displaying Cost... ");
      double tax = 0.08 * cost;
      double ans = cost + tax;
      String total_Cost = "Your total cost is $" + cost + " plus tax is: " + ans + "\n";
      return total_Cost;   
   }

   //displays receipt that includes tax calculation and time stamp
   public String displayReceipt(double cost){
      System.out.println("Displaying Receipt... ");
      double tax = 0.08 * cost;
      double total = cost + tax;
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
      String date = sdf.format(d);
      String timeStamp = "Receipt: Your total order for today including HST (ON - 8%): ";
      String result = timeStamp + total + " - Today's Date: " + date;
      return result;  
   }

   //calculates the total cost by finding book+cost in the appropriate text book file
   public double calcTotal(String bookStore, String bookName, int quantity){
      System.out.println("Calculating Total Cost... ");
      File file = new File(bookStore);
      double total_cost = 0;
      try {
          final Scanner sc = new Scanner(file);
          while(sc.hasNextLine()){
              final String bookLine = sc.nextLine();
              if(bookLine.contains(bookName)){
                  String[] parts = bookLine.split(",", 2);
                  int bookCost = Integer.parseInt(parts[1]);
                  total_cost = quantity * bookCost;
              }
          }
      sc.close();  
      } catch (Exception e) {
          e.printStackTrace();
      }
      return total_cost;
   }

  // returns a random book from the list of best sellers
   public String useRecommended(String bookStore){
      String topSellingBook;
      int random;
      System.out.println("Recommending Top Seller... ");
      List<String> topSellers = Arrays.asList("To Kill A Mockingbird", "The Great Gatsby", "The Hunger Games", "The Fault in Our Stars", "Gone Girl");
      List<String> topSellers2 = Arrays.asList("Will", "Atomic Habits", "Maid", "The Wish", "Going There");
      Random r = new Random();
      if(bookStore.equals("book_store.txt")){
         random = r.nextInt(topSellers.size());
         topSellingBook = topSellers.get(random);
      } else {
         random = r.nextInt(topSellers2.size());
         topSellingBook = topSellers2.get(random);
      }
      return topSellingBook;
  }
}
