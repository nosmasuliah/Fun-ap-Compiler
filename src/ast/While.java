package ast;
import lexer.DataType;



/**
 * @author samy
 *
 */

public class While extends Stmt {
	Expr expr; Stmt stmt;
	public While(){
		expr=null;
		stmt=null;
	}
	
	public While(Expr expr,Stmt stmt) {
		this.expr=expr;
		this.stmt=stmt;
		if(expr.type!=DataType.Bool) error("boolean required in while");
	}

	
	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public void setStmt(Stmt stmt) {
		this.stmt = stmt;
	}
	

	@Override
	public String toString() {
		StringBuffer buf=new StringBuffer();
		buf.append("while( ");
		buf.append(this.expr.toString());
		buf.append(")\n \t");
		buf.append(this.stmt.toString());
		return buf.toString();
	}
	
	@Override
	public	boolean containsNestedFunc(){
		if(this.expr.containsNestedFunc() || this.stmt.containsNestedFunc()) 
			return true;
		
		return false;
	}

	
	
	
	

}
