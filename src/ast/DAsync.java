package ast;


import lexer.DataType;
import lexer.Token;

public class DAsync extends Expr {
	
	public FuncCall funcCall;
	public String url;

	public DAsync(Token op, DataType type) {
		super(op, type);
		// TODO Auto-generated constructor stub
	}
	
	public DAsync(String url,FuncCall funcCal) {
		super(funcCal.op, funcCal.type);
		this.funcCall=funcCal;
		this.url=url;
	}

	
	
	@Override
	public boolean containsNestedFunc() {
		return this.funcCall.containsNestedFunc();
	}

	@Override
	public String toString() {
		StringBuilder buf=new StringBuilder();
		buf.append("("+this.funcCall.type.toString()+")");//cast
		buf.append("((RemoteInterface)LocateRegistry.getRegistry( ");
		buf.append(" \"" + this.url + "\" ");
		buf.append(",1099).lookup( \"dAsync\" )).execute(");
		buf.append("new "+funcCall.op.toString()+"()");
		for(Expr exp:funcCall.argList){
			buf.append(",");
			buf.append(exp.toString());
		}
		buf.append(")");
		return buf.toString();
	}
	
	

}
