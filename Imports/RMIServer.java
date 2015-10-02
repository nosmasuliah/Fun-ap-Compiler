import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public class RMIServer {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		RemoteExecuter executer=new RemoteExecuter();
		Registry registry=LocateRegistry.createRegistry(1099);
		registry.bind("dAsync",executer);
		System.out.println("Server ready ...");

	}

}
