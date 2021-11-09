import java.io.*; 
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileClient{
   public static void main(String argv[]) {
      if(argv.length != 2) {
        System.out.println("Usage: java FileClient fileName machineName");
        System.exit(0);
      }
      try {
         String name = "rmi://" + argv[1] + "/FileServer";
         System.out.println("Client Ready - remote stub active...");

         //Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
         //FileInterface fi = (FileInterface) registry.lookup(name);

         FileInterface fi = (FileInterface) Naming.lookup(name);
         int n = fi.addNum(3, 2);
         byte[] filedata = fi.downloadFile(argv[0]);
         File file = new File(argv[0]);
         BufferedOutputStream output = new
           BufferedOutputStream(new FileOutputStream(file.getName()));
         output.write(filedata,0,filedata.length);
         output.flush();
         output.close();
         System.out.println("The summation is: " + n);
      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}
