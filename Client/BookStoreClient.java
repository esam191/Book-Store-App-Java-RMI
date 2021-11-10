import java.io.*; 
import java.util.Scanner;
import java.rmi.*;

public class BookStoreClient{

   public static int selected;
   private static String bookName;
   private static String bookStore;
   private static int quantity;
   private static double tCost;
   private static double cost;
   private static Scanner sc = new Scanner(System.in);
   
   public static void main(String argv[]) {
      if(argv.length != 1) {
        System.out.println("Usage: java BookStoreClient machineName");
        System.exit(0);
      }
      try {
         String name = "rmi://" + argv[0] + "/BookStoreServer";
         System.out.println("Client Ready - remote stub active...");
         BookStoreInterface fi = (BookStoreInterface) Naming.lookup(name);
         while(selected != -1){
            getStarted();
            if(selected == 1){
               System.out.println("Pick a Book File to download: book_store.txt , book_store2.txt");
               String bookFile = sc.nextLine();
               byte[] filedata = fi.downloadFile(bookFile);
               File file = new File(bookFile);
               BufferedOutputStream output = new
               BufferedOutputStream(new FileOutputStream(file.getName()));
               output.write(filedata,0,filedata.length);
               output.flush();
               output.close();
               System.out.println("Successfully Received Book File From Server!");
            } else if(selected == 2){
               getBookInfo();
               completeOrder();
               cost = fi.calcTotal(bookStore, bookName, quantity);
               tCost += cost;
            } else if(selected == 3){
               String costString = fi.displayTotal(tCost);
               System.out.println("Server Sent: " + costString);
            } else if(selected == 4){
               System.out.println("Enter the name of the store: ");
               String bStore = sc.nextLine();
               bookName = fi.useRecommended(bStore);
               System.out.println("Server Recommended Book: " + bookName);
               completeOrder();
               cost = fi.calcTotal(bookStore, bookName, quantity);
               tCost += cost;
            } else if(selected == 5){
                String receipt = fi.displayReceipt(tCost);
                System.out.println("Server Sent: " + receipt);
            }
         }
         //sc.close();
         selected = 0;
         //recommendBook = false;
      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }

   public static void completeOrder(){
      System.out.println("Enter the quantity: ");
      quantity = Integer.parseInt(sc.nextLine());
      System.out.println("New Order Added Successfully!");
   }

   public static void getBookInfo(){
      System.out.println("Enter the name of the store: ");
      bookStore = sc.nextLine();
      System.out.println("Enter the name of the book: ");
      bookName = sc.nextLine();
   }

   public static void getStarted(){
      System.out.println("Please select one of the options: 1 - Get Book File, " + 
      "2 - Give New Book Order, 3 - View Total Cost, 4 - Best Seller Recommendation, " + 
      "5 - View Receipt, -1 - exit");
      selected = Integer.parseInt(sc.nextLine());
      System.out.println("Client Selected Option " + selected);
   }
}
