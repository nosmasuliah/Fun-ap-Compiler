package ast;

import java.util.Iterator;
import java.util.List;

import parser.SyntaxException;
import Symbol.SymbolTable;

public class AnonymousFunc extends Func {

	public AnonymousFunc(Id name, Block body, SymbolTable ST, List<Id> TypeList) throws SyntaxException {
		super(name, body, ST, TypeList);
		
	}

	public AnonymousFunc(Func f) {
		super(f);
		
	}

	@Override
	public String toString() {
		StringBuffer buff=new StringBuffer();
		buff.append("new Function");
		
		buff.append(this.paramList.size());
		buff.append("<");
		for(Id id: this.paramList){
			buff.append(id.type.toString());
			buff.append(", ");
		}
		buff.append(this.type.toString());
		buff.append(">() { \n");
		buff.append("private static final long serialVersionUID = 8716358603888347340L;");
		//generate the apply method
		buff.append(" public ");
		buff.append(this.type.toString());
		buff.append(" apply(  ");
		Iterator<Id> iterator=this.paramList.iterator();
		while(iterator.hasNext()){
			Id id=iterator.next();
			buff.append(id.type.toString());
			buff.append(" "+id.toString());
			
			if(iterator.hasNext())buff.append(", ");
			}
		buff.append(" ) throws Exception");
		buff.append(this.body.toString());
		buff.append("\n } \n");
		

		
		return buff.toString();
	}
	
	

}
