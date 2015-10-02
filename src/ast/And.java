package ast;
import lexer.Token;
import parser.SyntaxException;



public class And extends Logical {

	public And(Token op, Expr exp1, Expr exp2) throws SyntaxException {
		super(op, exp1, exp2);
			}

	@Override
	public String toString() {
		return expr1.toString() + " && " + expr2.toString(); 
	}

	@Override
	public 	boolean containsNestedFunc() {
		if(this.expr1.containsNestedFunc() || this.expr2.containsNestedFunc()) return true;
		return false;
	}
	

}
