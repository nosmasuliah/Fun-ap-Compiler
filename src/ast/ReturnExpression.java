package ast;

/**
 * @author samy
 *
 */
public class ReturnExpression extends Stmt{

	public Expr returnedExpression;

	public ReturnExpression(Expr expr){

	returnedExpression=expr;
   
	}

	public Expr getReturnedExpression(){

	return returnedExpression;
	}

	@Override
	public String toString() {
		return "return " + returnedExpression.toString()+";" ;
	}
	
	@Override
	public	boolean containsNestedFunc(){
		if(this.returnedExpression.containsNestedFunc())
			return true;
		if(returnedExpression instanceof AsynchExpr || returnedExpression instanceof AnonymousFunc) 
			return true;
		return false;
	}
	
	


	}
