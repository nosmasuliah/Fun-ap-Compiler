package ast;
import lexer.DataType;
import lexer.Token;




/**
 * @author samy
 *
 */
public abstract class Expr extends Node {
	public Token op;
	public DataType type;
	
	public Expr(Token op,DataType type) {
		this.op=op;
		this.type=type;
	} 

	@Override
	public String toString() {
		return this.op+"";
	}
	
	
	
	
	
	
	
	

}
