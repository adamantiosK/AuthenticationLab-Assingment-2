import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        PrinterInterface service = (PrinterInterface) Naming.lookup("rmi://localhost:8081/printer");
        System.out.println(service.status("printer1"));
        System.out.println(service.queue("printer1"));
        System.out.println(service.print("file1", "printer1"));
        System.out.println(service.topQueue("printer1", 0));
        System.out.println(service.restart());
    }

}
