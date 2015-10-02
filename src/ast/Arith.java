package ast;
import lexer.DataType;
import lexer.Token;
import parser.SyntaxException;




/**
 * @author samy
 *
 */

public class Arith extends Op {
	public Expr expr1,expr2;
	public Arith(Token op,Expr expr1,Expr expr2) throws SyntaxException {
		super(op, null); this.expr1=expr1; this.expr2=expr2;
		type=DataType.max(expr1.type, expr2.type);
		if(type==null) throw new SyntaxException("type error; Arithmetic Expressions should be composed of only numeric types");
	}
	
	@Override
	public String toString()
	{
		return expr1.toString()+" "+op.toString()+" "+expr2.toString();
	}

	@Override
	public	boolean containsNestedFunc() {
		if(this.expr1.containsNestedFunc() || this.expr1.containsNestedFunc()) return true;
		return false;
	}

//	@Override
//	public Constant value() {
//		switch(this.op.tokenType){
//		case PLUS:
//		//	return new Constant(expr1.value().getValue() + expr2.value());
//		case MINUS:
//			return expr1.value() + expr2.value();
//		case TIMES:
//			return expr1.value() + expr2.value();
//		case DIVIDED:
//			return expr1.value() + expr2.value();
//		default:
//			throw new SyntaxException("Invalid Operator");
//		}
//	}
	
	}
