import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrinterServant extends UnicastRemoteObject implements PrinterInterface {

    HashMap<String, Printer> printers;


    public PrinterServant() throws RemoteException{
        super();
        printers = new HashMap<>();
        Printer printer1 = new Printer("printer1", List.of("file1", "file2"));
        Printer printer2 = new Printer("printer2", List.of("file3", "file4"));
        printers.put("printer1", printer1);
        printers.put("printer2", printer2);
    }

    @Override
    public String print(String filename, String printer) throws RemoteException {
        return printers.get(printer).print(filename);
    }

    @Override
    public String queue(String printer) throws RemoteException {
        return printers.get(printer).queue();
    }

    @Override
    public String topQueue(String printer, int job) throws RemoteException {
        return printers.get(printer).topQueue(job);
    }

    @Override
    public void start() throws RemoteException {
        System.out.println("Starting server...");
    }

    @Override
    public void stop() throws RemoteException {
        System.out.println("Shutting down server...");
    }

    @Override
    public String restart() throws RemoteException {
        StringBuilder b = new StringBuilder();
        b.append("Shutting down server...\n");
        b.append("Clearing print queue...\n");
        for (Printer printer : this.printers.values()) {
            printer.clear();
        }
        b.append("Print queue cleared\n");
        b.append("Starting server...\n");
        return String.valueOf(b);
    }

    public String status(String printer) throws RemoteException {
        if (printers.containsKey(printer)) {
            return "Printer is online + \n" + printers.get(printer).status();
        }
            return "Printer is offline";
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return null;
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {

    }
}
