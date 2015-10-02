package ast;
import lexer.DataType;
import lexer.Token;




/**
 * @author samy
 *
 */
public class Unary extends Op {
	public Expr expr1;
	public Unary(Token op,Expr expr1) {
		super(op, null); this.expr1=expr1;
		type=DataType.max(DataType.Int, expr1.type);
		if(type==null) error("type error");
	}
	
	
	
	public String toString(){return op.toString()+" "+expr1.toString();}



	@Override
	public	boolean containsNestedFunc() {
		if(this.expr1 instanceof AnonymousFunc || this.expr1 instanceof AsynchExpr) return true;
		return false;
	}




	}