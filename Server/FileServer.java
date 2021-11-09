import java.io.*;
import java.rmi.*;
//import java.rmi.server.UnicastRemoteObject;

public class FileServer {
   public static void main(String argv[]) {
      // if(System.getSecurityManager() == null) {
      //    System.setSecurityManager(new SecurityManager());
      // }
      try {
         FileImpl fi = new FileImpl("FileServer");
         //FileInterface stub = (FileInterface) UnicastRemoteObject.exportObject(fi, 0);
         //Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
         //registry.rebind("rmi://127.0.0.1/FileServer", stub);
         Naming.rebind("rmi://127.0.0.1/FileServer", fi);
      } catch(Exception e) {
         System.out.println("FileServer: "+e.getMessage());
         e.printStackTrace();
      }
   }
}