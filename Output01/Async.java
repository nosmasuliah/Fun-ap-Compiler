
public abstract class Async<T> extends Thread {
	T value;
	T getValue(){
		this.start();
		return value;		
	}
}
