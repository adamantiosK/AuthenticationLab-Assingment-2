import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Adam on 18/10/2021
 *
 * @author : Adam
 * @date : 18/10/2021
 * @projects : RMI-Printer-Server
 */
public class RMIServerMain {

    public static void main(String[] args) {

        PrinterInterfaceImplementation implementation = new PrinterInterfaceImplementation();

        try {
            PrinterInterface stub = (PrinterInterface) UnicastRemoteObject.exportObject(implementation, 0);
            Registry registry = LocateRegistry.getRegistry();

            registry.rebind("AuthenticationLabAssignmentTwo", stub);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return;
        }
        System.out.println( "Printer is not running ..." );

    }
}
