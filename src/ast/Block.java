package ast;

import java.util.*;
import lexer.DataType;


/**
 * @author samy
 *
 */

public class Block extends Stmt {
	List<Declaration> declarationList;
	List<Stmt> statementList;
	
	public Block(List<Declaration> declarationList, List<Stmt> statementList) {
		this.declarationList=declarationList;
		this.statementList=statementList;
		
		setFreeVars();
	}

	
	/*
	 *  if the block contains a nested function or an async block make the variables declared in this block
	 *   a free variable(that is a final array of size one)
	 *   @see Declaration.java
	 *   @see Id.java
	 */
	public void setFreeVars(){
		
		if(containsNestedFunc()){
			Iterator<Declaration> declIterator=this.declarationList.iterator();
			
			while(declIterator.hasNext()){
				declIterator.next().declaredId.isFreeVar=true;
			}
			
		}
	}
	
	@Override
	public String toString() {
		StringBuffer buf=new StringBuffer();
		buf.append("{ \n");
		Iterator<Declaration> declIterator=declarationList.iterator();
		while(declIterator.hasNext()){
			buf.append(declIterator.next().toString());
			buf.append("\n");
		}
		
		Iterator<Stmt> stmtIterator=statementList.iterator();
		while(stmtIterator.hasNext() ){
			buf.append(stmtIterator.next().toString());
			buf.append("\n");
		}
		
		buf.append("}");
		return buf.toString();
	}
	
	@Override
	public	boolean containsNestedFunc(){
		for(Declaration d: this.declarationList){
			if(d.containsNestedFunc())return true;
		}
		
		for(Stmt st:this.statementList){
			if(st.containsNestedFunc()) return true;
		}
		
		return false;
		
	}
	
	public boolean checkReturnType(DataType dt){
		Stmt lastStmt=this.statementList.get(this.statementList.size()-1);
		if(lastStmt instanceof ReturnExpression ){
			//if(((ReturnExpression)lastStmt).returnedExpression instanceof AnonymousFunc){
			//TODO
			if(((ReturnExpression)lastStmt).returnedExpression.type==dt) return true;
		}
		else if(lastStmt instanceof IfElse) return ((IfElse)lastStmt).checkReturnType(dt);
		else if(lastStmt instanceof Block) return ((Block)lastStmt).checkReturnType(dt);
		return false;
		
	}

	
	
}
