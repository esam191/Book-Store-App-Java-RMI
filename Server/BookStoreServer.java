import java.rmi.*;

public class BookStoreServer {
   public static void main(String argv[]) {
      //uncomment below lines to add policy check
      // if(System.getSecurityManager() == null) {
      //    System.setSecurityManager(new SecurityManager());
      // }
      try {
         //creating object of implementation file
         BookStoreImpl fi = new BookStoreImpl("BookStoreServer");
         //connecting server
         Naming.rebind("rmi://127.0.0.1/BookStoreServer", fi);
         System.out.println("Server listening... ");
      } catch(Exception e) {
         System.out.println("BookStore Server: "+e.getMessage());
         e.printStackTrace();
      }
   }
}