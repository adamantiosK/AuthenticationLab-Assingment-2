import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Client {

    public static void main(String[] args) throws IOException, NotBoundException {
        PrinterInterface service = (PrinterInterface) Naming.lookup("rmi://localhost:8081/printer");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String token;

        while(true) {
            String printerResponse = "username or Password incorrect";

            while (printerResponse.equals("username or Password incorrect")) {
                System.out.println("Welcome to the Printer software");
                System.out.println("Input your Username to login :");
                String username = reader.readLine();
                System.out.println("Input your password:");
                String password = reader.readLine();

                printerResponse = service.login(username, password);

                System.out.println(printerResponse);
            }

            token = printerResponse;
            System.out.println("Successfully Logged In");

            while (token != null) {
                String response = printerAction(reader, service, selectService(reader),token);
                System.out.println(response);
                if(response.equals("Logged Out")){
                    token = null;
                }
            }

        }
    }

    private static int selectService(BufferedReader reader) throws IOException {
        System.out.println("Select a service you would like to use : (Type in Index) ");
        System.out.println("(1) - Print");
        System.out.println("(2) - Queue");
        System.out.println("(3) - Set file to top of the Queue");
        System.out.println("(4) - Start Server");
        System.out.println("(5) - Stop Server");
        System.out.println("(6) - Restart Server");
        System.out.println("(7) - Get Status");
        System.out.println("(8) - Read Configuration");
        System.out.println("(9) - Set Configuration");
        System.out.println("(10) - Log Out");

        return Integer.parseInt(reader.readLine());

    }

    private static String printerAction(BufferedReader reader,PrinterInterface service ,int operation, String token) throws IOException {
        String _responseMessage = "";
        switch (operation) {
            case 1:  operation = 1;
                System.out.println("Write in the file to print");
                String file = reader.readLine();
                System.out.println("Write in the printer to print from");
                String printer1 = reader.readLine();
                _responseMessage = service.print(file, printer1, token);
                break;
            case 2:  operation = 2;
                System.out.println("Write in the printer to select the queue from");
                String printer2 = reader.readLine();
                _responseMessage = service.queue(printer2, token);
                break;
            case 3:  operation = 3;
                System.out.println("Write in the file to move up ( Index )");
                int index = Integer.parseInt(reader.readLine());
                System.out.println("Write in the printer in which to move the file to the top of the queue");
                String printer3 = reader.readLine();
                _responseMessage = service.topQueue(printer3, index,token);
                break;
            case 4:  operation = 4;
                _responseMessage = service.start(token);
                break;
            case 5:  operation = 5;
                _responseMessage = service.stop(token);
                break;
            case 6:  operation = 6;
                _responseMessage = service.restart(token);
                break;
            case 7:  operation = 7;
                System.out.println("Write in the printer in which to move the file to the top of the queue");
                String printer4 = reader.readLine();
                _responseMessage = service.status(printer4,token);
                break;
            case 8:  operation = 8;
                System.out.println("Write in the parameter to read from");
                String parameter1 = reader.readLine();
                _responseMessage = service.readConfig(parameter1, token);
                break;
            case 9:  operation = 9;
                System.out.println("Write in the parameter to read from");
                String parameter2 = reader.readLine();
                System.out.println("Write the value to set it to");
                String value = reader.readLine();
                _responseMessage = service.setConfig(parameter2,value,token);
                break;
            case 10: operation = 10;
                _responseMessage = service.logout(token);
                break;
        }
        return _responseMessage;

    }


}
