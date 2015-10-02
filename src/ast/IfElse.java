package ast;
import lexer.DataType;



/**
 * @author samy
 *
 */

public class IfElse extends Stmt {
Expr expr; Stmt stmt1,stmt2;
	
	public IfElse(Expr expr,Stmt stmt1,Stmt stmt2) {
		this.expr=expr;
		this.stmt1=stmt1;
		this.stmt2=stmt2;
		if(expr.type!=DataType.Bool) error("boolean expected in if");
	}

	@Override
	public String toString() {
	StringBuffer buf=new StringBuffer();
	
	buf.append("if (");
	buf.append(expr.toString());
	buf.append(") \n \t");
	buf.append(stmt1.toString());
	buf.append("\n else \n \t");
	buf.append(stmt2.toString());
	
	return buf.toString();
	}
	
	public boolean checkReturnType(DataType dt){
		boolean ret1,ret2;
		if(this.stmt1 instanceof ReturnExpression ) ret1=true;
		else if(this.stmt1 instanceof IfElse) ret1=((IfElse)stmt1).checkReturnType(dt);
		else if(this.stmt1 instanceof Block) return ((Block)stmt1).checkReturnType(dt);
		else ret1=false;
		
		if(this.stmt2 instanceof ReturnExpression ) ret2=true;
		else if(this.stmt2 instanceof IfElse) ret2=((IfElse)stmt2).checkReturnType(dt);
		else if(this.stmt2 instanceof Block) return ((Block)stmt2).checkReturnType(dt);
		else ret2=false;
		
		return (ret1 && ret2);
		
	}
	
	@Override
	public	boolean containsNestedFunc(){
		if(this.expr.containsNestedFunc() || this.stmt1.containsNestedFunc() || this.stmt2.containsNestedFunc())
			return true;
		
		return false;
		
	}
	
 }