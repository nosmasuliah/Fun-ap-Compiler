package ast;
import lexer.DataType;
import lexer.Token;
import parser.SyntaxException;


/**
 * @author samy
 *
 */

public class Rel extends Logical {

	public Rel(Token op, Expr exp1, Expr exp2) throws SyntaxException {
		super(op, exp1, exp2);
		if(check(exp1.type,exp2.type)==null){
			throw new SyntaxException("type mismatch, can not compare "+exp1.type+" with "+exp2.type);
		}
					}
	
	public DataType check(DataType p1,DataType p2){
		if(p1==p2) return DataType.Bool;
		return null;
	}

	@Override
	public String toString() {
		return expr1.toString()+" "+ op.toString()+ " " + expr2.toString();
	}

	@Override
	public boolean containsNestedFunc() {
		if(this.expr1.containsNestedFunc() || this.expr2.containsNestedFunc()) return true;
		return false;
	}
	
	

}
