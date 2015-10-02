package ast;

import java.util.Iterator;
import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;


/**
 * @author samy
 *
 */
public class Program extends Node {
	Func mainFunction;
	List<Func> userDefinedFuncs;
	public Program(List<Func> userDefinedFuncs,Func mainFunc) {
		this.mainFunction=mainFunc;
		this.userDefinedFuncs=userDefinedFuncs;
		
	}
	@Override
	public String toString() {
		StringBuilder buf=new StringBuilder();
		
		buf.append("import java.util.concurrent.Callable;\n");
		buf.append("import java.util.concurrent.ExecutionException;\n");
		buf.append("import java.util.concurrent.ExecutorService;\n");
		buf.append("import java.util.concurrent.Executors;\n");
		buf.append("import java.rmi.*;\n");
		buf.append("import java.rmi.registry.*;\n");
		Iterator<Func> FuncIterator=userDefinedFuncs.iterator();
		while(FuncIterator.hasNext()){
			Func f=FuncIterator.next();
			if(f!=null)
				buf.append(f.toString());
		}
		buf.append("public class Generated {\n");
		buf.append("public static ExecutorService service = Executors.newCachedThreadPool();\n");
		buf.append("public static void main(String[] args) throws Exception ");
		buf.append(mainFunction.body.toString());
		buf.append("\n}");
		
		
		
		return buf.toString();
	}
	
	@Override
	public boolean containsNestedFunc() {
			return false;
	}
	
	

}
