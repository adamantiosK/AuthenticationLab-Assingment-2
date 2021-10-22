import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PrinterServant extends UnicastRemoteObject implements PrinterInterface {

    HashMap<String, Printer> printers;
    HashMap<String,String> tokens = new HashMap<String,String>();


    public PrinterServant() throws RemoteException{
        super();
        printers = new HashMap<>();
        Printer printer1 = new Printer("printer1", List.of("file1", "file2"));
        Printer printer2 = new Printer("printer2", List.of("file3", "file4"));
        printers.put("printer1", printer1);
        printers.put("printer2", printer2);
    }

    @Override
    public String print(String filename, String printer,String token) throws RemoteException {
        return tokens.containsKey(token)?printers.get(printer).print(filename):"Not Authenticated";
    }

    @Override
    public String queue(String printer, String token) throws RemoteException {
        return tokens.containsKey(token)?printers.get(printer).queue():"Not Authenticated";
    }

    @Override
    public String topQueue(String printer, int job, String token) throws RemoteException {
        return tokens.containsKey(token)?printers.get(printer).topQueue(job):"Not Authenticated";
    }

    @Override
    public String start(String token) throws RemoteException {
        return tokens.containsKey(token)?"Starting server...":"Not Authenticated";
    }

    @Override
    public String stop(String token) throws RemoteException {
        return tokens.containsKey(token)?"Shutting down server...":"Not Authenticated";
    }

    @Override
    public String restart(String token) throws RemoteException {
        if(tokens.containsKey(token)) {
            StringBuilder b = new StringBuilder();
            b.append("Shutting down server...\n");
            b.append("Clearing print queue...\n");
            for (Printer printer : this.printers.values()) {
                printer.clear();
            }
            b.append("Print queue cleared\n");
            b.append("Starting server...\n");
            return String.valueOf(b);
        }else{
            return "Not Authenticated";
        }
    }

    @Override
    public String status(String printer, String token) throws RemoteException {
        if(tokens.containsKey(token)) {
            if (printers.containsKey(printer)) {
                return "Printer is online + \n" + printers.get(printer).status();
            }
            return "Printer is offline";
        }else{
            return "Not Authenticated";
        }
    }

    @Override
    public String readConfig(String parameter, String token) throws RemoteException {
        return null;
    }

    @Override
    public String setConfig(String parameter, String value, String token) throws RemoteException {
        return "";
    }

    @Override
    public String login(String username, String password) throws RemoteException {
        return UserExists(username,password)?generateARandomToken(username):"username or Password incorrect";
    }

    @Override
    public String logout(String token) throws RemoteException {
        tokens.remove(token);
        return "Logged Out";
    }

    private Boolean UserExists(String Username, String Password){
        DatabaseConnection db = new DatabaseConnection();
        return db.userAuthenticated(Username,Password);
    }

    private String generateARandomToken(String Username) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        tokens.put(generatedString,Username);
        return generatedString;
    } // Number 5 from https://www.baeldung.com/java-random-string

}
