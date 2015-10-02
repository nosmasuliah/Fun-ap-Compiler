package ast;
import lexer.Token;


/**
 * @author samy
 *
 */
public class Not extends Logical {

	public Not(Token op, Expr exp2) {
		super(op, exp2, exp2);
			}

	@Override
	public String toString() {
		return " !"+expr2.toString();
	}

	@Override
	public	boolean containsNestedFunc() {
		if(this.expr2.containsNestedFunc()) return true;
		return false;
	}
	

}
