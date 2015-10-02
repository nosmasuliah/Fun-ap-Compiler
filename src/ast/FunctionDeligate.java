package ast;

import java.util.*;

import lexer.DataType;
import lexer.TokenType;
import lexer.Word;
import parser.SyntaxException;
import Symbol.SymbolTable;

/**
 * @author samy
 *
 */
public class FunctionDeligate extends Func {

	public FunctionDeligate(Id name, Block body, SymbolTable ST,
			List<Id> TypeList) throws SyntaxException {
		super(name, body, ST, TypeList);
		
	}
	
	public FunctionDeligate(Word name) throws SyntaxException{
		super(new Id(name,DataType.Function),null,new SymbolTable(null),new LinkedList<Id>());
	}
	
	
		
	public FunctionDeligate(Word name,DataType retType,List<DataType> paramTypeList) throws SyntaxException{
		
		
		
		this(new Id(name,retType),null,new SymbolTable(null),typeListToIdList(paramTypeList));
	}
	
	static List<Id> typeListToIdList(List<DataType> L){
		List<Id> paramList=new LinkedList<Id>();
		int i=0;
		for(DataType t:L){
			paramList.add(new Id(new Word("arg"+ i++,TokenType.ID),t));
		}
		return paramList;
	}
	
	
	public void setType(DataType t){
		this.type=t;
	}
	
	public void setParams(List<Id> params){
		this.paramList=params;
	}
	
	public void setBody(Block b){
		this.body=b;
	}

	@Override
	public String toString() {
		return this.op+"";
	}
	
	

}
