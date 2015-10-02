package ast;


/**
 * @author samy
 *
 */
public class Return extends Stmt{

	public Return(){}

	@Override
	public String toString() {
		return " return ";
	}
	
	@Override
	public	boolean containsNestedFunc(){
		return false;
		
	}
	
	}
