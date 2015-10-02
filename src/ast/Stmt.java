package ast;



/**
 * @author samy
 *
 */

public class Stmt extends Node {

	public Stmt() {	}
	public static Stmt Null=new Stmt();//represents an empty statement
	
	
	
	@Override
	public String toString() {
		
		return "";
	}



	@Override
	public	boolean containsNestedFunc() {
			return false;
	}
	
	
	
	

}
