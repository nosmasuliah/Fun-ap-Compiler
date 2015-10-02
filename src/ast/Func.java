package ast;


import java.util.Iterator;
import java.util.List;



import lexer.*;
import parser.SyntaxException;
//import java.util.Iterator;
import Symbol.*;


/**
 * @author samy
 *
 */
public class Func extends Id {
	
	public Block body;
	public SymbolTable ST;
	public List<Id> paramList;
	 
	
	public Func(Id name, Block body,SymbolTable ST,List<Id> TypeList) throws SyntaxException {
		super((Word)name.op,name.type);
		this.body = body;
		this.ST=ST;
		this.paramList=TypeList;
		
		for(Id id:this.paramList){
			if(id!=null)
				this.ST.put((Word)id.op, id);
								}
		//check if the body contains a return statement that returns an expression with the same type as returnType 
		if(this.body!=null){
			if(this.op.toString()!="main"){
				if(!typeCheck(name.type)) throw new SyntaxException("function must return "+name.type);
			}
			
		
		}
		
		
	}
	
	public Func(Func f){
		super((Word) f.op,f.type);
	}	
	

	
	boolean typeCheck(DataType dt){ 
		if(dt instanceof FunctionReturnType) return true;//TODO
		return this.body.checkReturnType(dt);
		
	}
	
	public void setBody(Block body) throws SyntaxException{
		this.body=body;
		if(!(this instanceof FunctionDeligate) && this.op.toString()!="main"){
			if(!typeCheck(this.type)) throw new SyntaxException("function must return "+this.type);
		}
	}
	
	public void setType(DataType dt){
		this.type=dt;
	}
	
	@Override
	public String toString() {
		
		StringBuffer buf=new StringBuffer();
		buf.append("\n class ");
		buf.append(this.op);
		buf.append(" implements Function");
		buf.append(this.paramList.size());
		buf.append("<");
		for(Id id: this.paramList){
			buf.append(id.type.toString());
			buf.append(", ");
		}
		buf.append(this.type.toString());
		buf.append("> { \n");
		buf.append("private static final long serialVersionUID = 8716358603888347340L;");
		//generate an empty constructor
		buf.append("public ");
		buf.append(this.op);
		buf.append("(){} \n \n");
		
		//generate the apply method
		buf.append("public ");
		buf.append(this.type.toString());
		buf.append(" apply(  ");
		Iterator<Id> iterator=this.paramList.iterator();
		while(iterator.hasNext()){
			if(this.body.containsNestedFunc()) buf.append(" final ");
			Id id=iterator.next();
			buf.append(id.type.toString());
			buf.append(" "+id.toString());
			
			if(iterator.hasNext())buf.append(", ");
			}
		buf.append(" ) throws Exception");
		buf.append(this.body.toString());
		buf.append("\n } \n");
		
		
		return buf.toString();
	}
	




	public static Func NullFunction;
		
}
