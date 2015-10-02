package ast;
import lexer.DataType;
import lexer.Token;


/**
 * @author samy
 *
 */
public abstract class Op extends Expr {

	public Op(Token op, DataType type) {
		super(op, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return  op+"";
	}
	
	//public abstract Constant value();
	

}
