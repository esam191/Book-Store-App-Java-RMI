import java.rmi.*;

public class BookStoreServer {
   public static void main(String argv[]) {
      // if(System.getSecurityManager() == null) {
      //    System.setSecurityManager(new SecurityManager());
      // }
      try {
         BookStoreImpl fi = new BookStoreImpl("BookStoreServer");
         Naming.rebind("rmi://127.0.0.1/BookStoreServer", fi);
      } catch(Exception e) {
         System.out.println("BookStore Server: "+e.getMessage());
         e.printStackTrace();
      }
   }
}