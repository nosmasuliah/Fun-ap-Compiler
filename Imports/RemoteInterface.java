import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteInterface  extends Remote {
	public <T> T execute(Function_ f, Object ... params) throws RemoteException;
}
