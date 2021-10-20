import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrinterServant extends UnicastRemoteObject implements PrinterInterface {

    public PrinterServant() throws RemoteException{
        super();
    }

    public String echo(String input) throws RemoteException {
    return "From server: " + input;
    }

    @Override
    public String print(String filename, String printer) throws RemoteException {
        return null;
    }

    @Override
    public String queue(String printer) throws RemoteException {
        return null;
    }

    @Override
    public void topQueue(String printer, int job) throws RemoteException {

    }

    @Override
    public void start() throws RemoteException {

    }

    @Override
    public void stop() throws RemoteException {

    }

    @Override
    public void restart() throws RemoteException {

    }

    @Override
    public String status(String printer) throws RemoteException {
        return null;
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return null;
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {

    }
}
