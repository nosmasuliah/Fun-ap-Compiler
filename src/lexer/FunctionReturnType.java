package lexer;

import java.util.*;

public class FunctionReturnType extends DataType {
	
	public DataType returnType;
	public List<DataType> paramTypeList;

	public FunctionReturnType(String lexeme, TokenType type, DataType rType,List<DataType> paramList) {
		super(lexeme, type);
		returnType=rType;
		paramTypeList=paramList;
	}

	public DataType getReturnType() {
		return returnType;
	}

	public void setReturnType(DataType returnType) {
		this.returnType = returnType;
	}

	public List<DataType> getParamTypeList() {
		return paramTypeList;
	}
	
	

	@Override
	public String toString() {
		StringBuffer buf=new StringBuffer();
		
		buf.append( "Function");
		buf.append(this.paramTypeList.size());
		buf.append("<");
		Iterator<DataType> it=this.paramTypeList.iterator();
		while(it.hasNext()){
			buf.append(it.next().toString());
			//if(it.hasNext())
				buf.append(", ");
		}
		//buf.append("List<DataType>,");
		buf.append(returnType.toString());
		buf.append(">");
		

		return buf.toString();
	}

	public void setParamTypeList(List<DataType> paramTypeList) {
		this.paramTypeList = paramTypeList;
	}

}
