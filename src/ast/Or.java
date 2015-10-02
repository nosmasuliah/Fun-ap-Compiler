package ast;
import lexer.Token;


/**
 * @author samy
 *
 */
public class Or extends Logical {

	public Or(Token op, Expr exp1, Expr exp2) {
		super(op, exp1, exp2);
			}

	@Override
	public String toString() {
		return expr1.toString() + " || " + expr2.toString(); 
	}

	@Override
	public	boolean containsNestedFunc() {
		if(this.expr1.containsNestedFunc() || this.expr2.containsNestedFunc()) return true;
		return false;
	}
	

}
