package ast;
import lexer.DataType;


/**
 * @author samy
 *
 */
public class If extends Stmt {
	Expr expr; Stmt stmt;
	public If(Expr expr,Stmt stmt) {
		this.expr=expr;
		this.stmt=stmt;
		if(expr.type!=DataType.Bool) error("boolean required in if");
	}
	
	
	@Override
	public String toString() {
		StringBuffer buf=new StringBuffer();
		buf.append("if (");
		buf.append(expr.toString());
		buf.append(" ) \n \t ");
		buf.append(stmt.toString());
		
		return buf.toString();
		
	}
	
	@Override
	public	boolean containsNestedFunc(){
		if(this.expr.containsNestedFunc() || this.stmt.containsNestedFunc()) return true;
		return false;
	}
	
	
	
}
