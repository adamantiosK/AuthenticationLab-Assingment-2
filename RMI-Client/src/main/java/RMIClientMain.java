import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Adam on 18/10/2021
 *
 * @author : Adam
 * @date : 18/10/2021
 * @projects : AuthenticationLab-Assingment-2
 */
public class RMIClientMain {

    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name = "AuthenticationLabAssignmentTwo";
            Registry registry = LocateRegistry.getRegistry("localhost");
            PrinterInterface comp = (PrinterInterface) registry.lookup(name);

            int returnedValueZero = comp.functionOne( "Example Message" );

            System.out.println( "The return value from the server is: " + returnedValueZero );

        }catch(Exception e){
            System.err.println( "Exception while trying to echo:" );
            e.printStackTrace();
        }



    }

}
