import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RemoteExecuter extends UnicastRemoteObject implements
		RemoteInterface {

	protected RemoteExecuter() throws RemoteException {
		super();
			}
	


	@SuppressWarnings("unchecked")
	@Override
	public <T> T execute(Function_ f,Object ... params) throws RemoteException {
		Method m=f.getClass().getMethods()[0];
		
		try {
			return (T) m.invoke(f,params);
//			if(f instanceof Function0){
//			
//				T invoke = (T) m.invoke(f);
//				return invoke;
//				}
//			else if(f instanceof Function1){
//				return (T) m.invoke(f, params[0]);
//			}else if(f instanceof Function2){
//				return (T)m.invoke(f, params[0],params[1]);
//			}else if()
		
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return null;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
