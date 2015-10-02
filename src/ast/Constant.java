package ast;
import lexer.BooleaN;
import lexer.DataType;
import lexer.Doubl;
import lexer.Int;
import lexer.StringLiteral;
import lexer.Token;
import lexer.Word;



/**
 * @author samy
 *
 */
public class Constant extends Expr {

	public Constant(Token op, DataType type) {	super(op, type);}
	public Constant(int i){
		super(new Int(i),DataType.Int);
		}
	public Constant(boolean b){
		super(new BooleaN(b),DataType.Bool);
		}
	
	public Constant(double d){
		super(new Doubl(d),DataType.Double);
	}
	
	public Constant(String s){
		super(new StringLiteral(s),DataType.STring);
	}
	
	

	
	
	@Override
	public String toString() {
		return op.toString();
	}



	public static final Constant True=new Constant(Word.True,DataType.Bool),
								 False=new Constant(Word.False,DataType.Bool);



	@Override
	public	boolean containsNestedFunc() {
		return false;
	}



	
	
}
