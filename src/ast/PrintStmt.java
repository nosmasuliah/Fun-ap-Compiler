package ast;

/**
 * @author samy
 *
 */
public class PrintStmt extends Stmt {
	Expr tobePrinted;
	
	public PrintStmt(Expr tobePrinted){
		this.tobePrinted=tobePrinted;
	}

	@Override
	public String toString() {
		StringBuilder buf=new StringBuilder();
		buf.append("System.out.println(");
		buf.append(this.tobePrinted.toString());
		buf.append(");");
		return buf.toString();
	}
	
	
}
