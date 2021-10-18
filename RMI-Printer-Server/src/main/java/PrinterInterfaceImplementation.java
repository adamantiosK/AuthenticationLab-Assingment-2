import java.rmi.RemoteException;

/**
 * Created by Adam on 18/10/2021
 *
 * @author : Adam
 * @date : 18/10/2021
 * @projects : AuthenticationLab-Assingment-2
 */
public class PrinterInterfaceImplementation implements PrinterInterface {


    @Override
    public int functionOne(String str) throws RemoteException {
        System.out.println( "Got a message from the client: " + str );
        return 0;
    }
}
