import java.rmi.*;

public class FileServer {
   public static void main(String argv[]) {
      // if(System.getSecurityManager() == null) {
      //    System.setSecurityManager(new SecurityManager());
      // }
      try {
         FileImpl fi = new FileImpl("FileServer");
         Naming.rebind("rmi://127.0.0.1/FileServer", fi);
      } catch(Exception e) {
         System.out.println("FileServer: "+e.getMessage());
         e.printStackTrace();
      }
   }
}