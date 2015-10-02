package ast;

import java.util.Iterator;
import java.util.List;

import lexer.*;


/**
 * @author samy
 *
 */
public class FuncCall extends Expr {

	protected List<Expr> argList;
	
	public FuncCall(Token name, DataType type,List<Expr> argList) {
		super(name, type);
		this.argList=argList;
	}
	
	
	
	@Override
	public String toString() {
		
		StringBuffer buf=new StringBuffer();
		buf.append("new ");
		buf.append(op.toString());
		buf.append("().apply");
		buf.append('(');
		
		Iterator<Expr> argueMentIterator=this.argList.iterator();
		
		while(argueMentIterator.hasNext()){
			buf.append(argueMentIterator.next().toString());
			if(argueMentIterator.hasNext()) buf.append(" , ");
		}
		
		buf.append(") ");
		
		return buf.toString();
		
	}



	@Override
	public boolean containsNestedFunc() {
		
		for(Expr exp:this.argList){
			if(exp.containsNestedFunc()) return true;
		}
		
		return false;
	}

	
	
}
