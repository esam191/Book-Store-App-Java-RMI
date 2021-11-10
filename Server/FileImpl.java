import java.io.*;
import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;

public class FileImpl extends UnicastRemoteObject implements FileInterface {

   private String name;

   public FileImpl(String s) throws RemoteException{
      super();
      name = s;
   }

   public byte[] downloadFile(String fileName){
      try {
         File file = new File(fileName);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer,0,buffer.length);
         input.close();
         return(buffer);
      } catch(Exception e){
         System.out.println("FileImpl: "+e.getMessage());
         e.printStackTrace();
         return(null);
      }
   } 

   public int addNum(int a, int b){
      return (a + b);
   }

   //method to display the client's total cost of order
   public String displayTotal(double cost){
      String total_Cost = "Your total cost is: $" + cost + " plus tax";
      return total_Cost;   
  }

   //displays receipt that includes tax calculation and time stamp
   public String displayReceipt(double cost){
      double tax = 0.08 * cost;
      double total = cost + tax;
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
      String date = sdf.format(d);
      String timeStamp = "Receipt: Your total order for today including HST (ON - 8%): ";
      String result = timeStamp + total + " - Today's Date: " + date;
      return result;  
   }

   //calculates the total cost by finding book+cost in the text file
   public double calcTotal(String bookName, int quantity){
      File file = new File("book_store.txt");
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
   public String useRecommended(){
      System.out.println("Recommending Top Seller... ");
      List<String> topSellers = Arrays.asList("To Kill A Mockingbird", "The Great Gatsby", "The Hunger Games", "The Fault in Our Stars", "Gone Girl");
      Random r = new Random();
      int random = r.nextInt(topSellers.size());
      String topSellingBook = topSellers.get(random);
      return topSellingBook;
  }
}
