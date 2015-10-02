package ast;


/**
 * @author samy
 *
 */
 public abstract class Node {
	int lexline=0;
	public Node() {
		// TODO Auto-generated constructor stub
	}
	public void error(String s){throw new Error("near line"+lexline+": "+s);}
	
	
	/*
	 * @return true if a nested function is part of the expression
	 * */
	public abstract boolean containsNestedFunc();
	
	
}
