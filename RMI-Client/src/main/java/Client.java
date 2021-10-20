import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        PrinterService service = (PrinterService) Naming.lookup("rmi://localhost:8081/printer");
        System.out.println(service.echo("hey server"));

    }

}
