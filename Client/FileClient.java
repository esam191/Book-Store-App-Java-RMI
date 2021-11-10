import java.io.*; 
import java.util.Scanner;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileClient{

   public static int selected;
   private static String bookName;
   private static int quantity;
   private static double tCost;
   private static double cost;
   
   public static void main(String argv[]) {
      if(argv.length != 1) {
        System.out.println("Usage: java FileClient machineName");
        System.exit(0);
      }
      try {
         String name = "rmi://" + argv[0] + "/FileServer";
         System.out.println("Client Ready - remote stub active...");
         FileInterface fi = (FileInterface) Naming.lookup(name);
         while(selected != -1){
            Scanner sc = new Scanner(System.in);
            System.out.println("Please select one of the options: 1 - Get Book File" + 
            "2 - Give New Book Order , 3 - View Total Cost, 3 - Best Seller Recommendation," + 
            "4 - View Receipt, -1 - exit");
            selected = Integer.parseInt(sc.nextLine());
            System.out.println("Client Selected Option " + selected);
            if(selected == 1){
               System.out.println("Pick a Book File to download: book_store.txt , book_store_new.txt");
               String bookFile = sc.nextLine();
               byte[] filedata = fi.downloadFile(bookFile);
               File file = new File(bookFile);
               BufferedOutputStream output = new
               BufferedOutputStream(new FileOutputStream(file.getName()));
               output.write(filedata,0,filedata.length);
               output.flush();
               output.close();
               System.out.println("Successfully Recevied Book File From Server!");
            } else if(selected == 2){
               System.out.println("Enter the name of the book: ");
               bookName = sc.nextLine();

               System.out.println("Enter the quantity: ");
               quantity = Integer.parseInt(sc.nextLine());

               System.out.println("New Order Added Successfully!");
               cost = fi.calcTotal(bookName, quantity);
               tCost += cost;
            } else if(selected == 3){
               String costString = fi.displayTotal(tCost);
               System.out.println("Server Sent: " + costString);
            } else if(selected == 4){
               bookName = fi.useRecommended();
               System.out.println("Server Recommended Book: " + bookName);
               System.out.println("Enter the quantity: ");
               quantity = Integer.parseInt(sc.nextLine());
               System.out.println("New Order Added Successfully!");
               cost = fi.calcTotal(bookName, quantity);
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
}
