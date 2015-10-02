package ast;
import lexer.DataType;
import lexer.Token;



/**
 * @author samy
 *
 */

public abstract class Logical extends Expr {
	Expr expr1,expr2;
	
	public Logical(Token op, Expr exp1, Expr exp2) {
		super(op, DataType.Bool);
		expr1=exp1;
		expr2=exp2;
		type=check(expr1.type,expr2.type);
		if(type==null) error("type error, Logical expression expects operands of type boolean"); //should be removed and added on all the subclasses except relational
		
	}
	
	public DataType check(DataType t1, DataType t2){
		if(t1==DataType.Bool && t2==DataType.Bool) return DataType.Bool;
		//if(DataType.isNumeric(t1) && DataType.isNumeric(t2)) return DataType.max(t1, t2);
		return null;
		
	}

	@Override
	public  String toString()	{
		return expr1.toString()+" "+ op.toString()+expr2.toString();
	}

}
