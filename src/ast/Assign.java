package ast;
import lexer.*;
import parser.SyntaxException;


/**
 * @author samy
 *
 */

public class Assign extends Stmt {
	Id id;
	Expr expr;
	public Assign(Id id, Expr expr) throws SyntaxException {
		this.id=id;
		this.expr=expr;
		if(check(id.type,expr.type)==null &&  id.type!=expr.type){ 
			if(!((id instanceof FunctionDeligate) && (expr.type instanceof FunctionReturnType)))//check if we are assigning a function to a function ptr 
				throw new SyntaxException("type error: assigning "+expr.type+" to "+ id.type);
		}
	}
	
	public DataType check(DataType p1,DataType p2){
		if(DataType.isNumeric(p1) && DataType.isNumeric(p2)) return p2;
		else if (p1==DataType.Bool && p2==DataType.Bool) return p2;
		return null;
	}

	@Override
	public String toString() {
		return id.toString() + " = " + expr.toString()+";";
	}
	
	@Override
	public	boolean containsNestedFunc(){
		if(this.expr.containsNestedFunc() || this.expr instanceof AsynchExpr) return true;
		
		return false;
		
	}
	
	


}