package ast;


/**
 * @author samy
 *
 */
public class AsynchExpr extends Expr {
	
	private Expr returnedExpr;
	
	private boolean containsNestedAsync=false;

	public AsynchExpr(Expr returnedExp){
		super(returnedExp.op,returnedExp.type);
		if(returnedExp.containsNestedFunc()) this.containsNestedAsync=true;
		this.returnedExpr=returnedExp;
	}

//	@Override
//	public String toString() {
//		StringBuffer buff=new StringBuffer();
//		buff.append("new Async<");
//		buff.append(returnedExpr.type.toString());
//		buff.append(">() { \n public void run() { \n");
//		buff.append("value=");
//		buff.append(returnedExpr.toString());
//		buff.append(";");
//		buff.append("\n } \n }.getValue();");		
//		
//		return buff.toString();
//	}

	
	@Override
	public String toString() {
		StringBuffer buff=new StringBuffer();
		
		buff.append("Generated.service.submit( new Callable<");
		buff.append(returnedExpr.type.toString()+">() { \n");
		buff.append("public ");
		buff.append(returnedExpr.type.toString());
		buff.append(" call() throws Exception {\n");
		buff.append("return ");
		buff.append(this.returnedExpr.toString());
		buff.append(";}}).get();");
		return buff.toString();
	}
	
	
	@Override
	public	boolean containsNestedFunc() {
		
		return this.containsNestedAsync;
	}
	
	

}
