package lexer;




public class DataType extends Word {
	//public int width;
	public DataType(String lexeme,TokenType type) {
		super(lexeme,type);
		//this.width=width;
	}
	
	
	
	public static final DataType Int=new DataType("int",TokenType.BASICTYPE),
								 Double=new DataType("double",TokenType.BASICTYPE),
								 Bool=new DataType("bool",TokenType.BASICTYPE),
								 STring=new DataType("String",TokenType.BASICTYPE),
								 Void = new DataType("void",TokenType.BASICTYPE),
								 Function=new DataType("func",TokenType.BASICTYPE);
								 //FuncReturned=new FunctionReturnType("func_",TokenType.BASICTYPE,null,null);
	
	//checks if a data type is numeric, so that an arithmetic operation can be applied on it
	public static boolean isNumeric(DataType p){
		if(p==DataType.Double || p==DataType.Int/*||p==DataType.Bool*/)return true;
		return false;
	}
	
	//for numeric types  the type should be casted with higher precedence given to double,then int and finally boolean
	public static DataType max(DataType p1, DataType p2) {
		if(!isNumeric(p1) ||!isNumeric(p2)) return null;
		
		else if(p1==DataType.Double || p2==DataType.Double) return DataType.Double;
		else if(p1==DataType.Int || p2==DataType.Int) return DataType.Int;
		else return DataType.Bool;
		
	}

	@Override
	public String toString() {
		switch(lexeme){
		case "int":
			return "Integer";
		case "double":
			return "Double";
		case "bool":
			return "Boolean";
		case "func":
			return "Function_";
		default:
			return lexeme;
		}
		//return lexeme;
	}
	


}
