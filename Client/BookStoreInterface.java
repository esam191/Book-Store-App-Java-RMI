import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookStoreInterface extends Remote {
   public byte[] downloadBookFile(String fileName) throws RemoteException;
   public double calcTotal(String bookStore, String bookName, int quantity) throws RemoteException;
   public String displayTotal(double cost) throws RemoteException;
   public String displayReceipt(double cost) throws RemoteException;
   public String useRecommended(String bookStore) throws RemoteException;
}
