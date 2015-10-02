package ast;

import java.util.Iterator;
import java.util.List;

import lexer.DataType;
import lexer.Token;

/**
 * @author samy
 *
 */
public class DeligateCall extends FuncCall {

	public DeligateCall(Token name, DataType type, List<Expr> argList) {
		super(name, type, argList);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public String toString() {
//		return "DeligateCall []";
//	}@Override
	public String toString() {
		
		StringBuilder buf=new StringBuilder();

		buf.append("(");
		buf.append(this.type.toString());
		buf.append(")");
		buf.append(this.op.toString());
		buf.append(".getClass().getMethod(\"apply\" ");
		Iterator<Expr> argTypeIterator=this.argList.iterator();
		while(argTypeIterator.hasNext()){
			buf.append(" , ");
			buf.append(argTypeIterator.next().type.toString());
			buf.append(".class");
		}
		buf.append(")");
		buf.append(".invoke(");
		buf.append(this.op.toString());
		
		Iterator<Expr> argueMentIterator=this.argList.iterator();
		
		while(argueMentIterator.hasNext()){
			buf.append(",");
			buf.append(argueMentIterator.next().toString());
					}
		buf.append(") ");
		
		return buf.toString();
		
	}
	

}
