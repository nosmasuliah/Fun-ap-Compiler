package ast;
import lexer.*;



/**
 * @author samy
 *
 */
public class Id extends Expr {
	
	protected Expr value;
	public boolean isFreeVar=false;
	
	public Id(Word op, DataType type, Expr val) {
		super(op, type);
		this.value=val;
	}
	
	public Id(Word op, DataType type) {
		super(op, type);
		if(type==DataType.Bool)	value=new Constant(false);
		else if(type==DataType.Double)	value=new Constant(0.0);
		else if(type==DataType.Int)	value=new Constant(0);
		else if(type==DataType.STring)	value=new Constant("");
	}
	public Expr getValue() {
		return value;
	}
	public void setValue(Expr value) {
		this.value = value;
	}

	@Override
	public	boolean containsNestedFunc() {
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder buf=new StringBuilder();
		buf.append(this.op);
		if(this.isFreeVar) buf.append("[0]");
		
		return buf.toString();
	}
	
	
	
	

}
