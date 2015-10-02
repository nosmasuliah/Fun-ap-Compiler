package parser;

import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import lexer.*;
import Symbol.*;
import ast.*;

/**
 * @author samy
 *
 */
public class Parser {
	private Lexer lexer;
	private Token token;
	private SymbolTable top=null;
	private static int funcNum=0;
	
	
	public Parser() {
		
	}
	
	public Program parse(Reader r) throws Exception{
		lexer=new Lexer(r);
		nextToken();
		Program stmt=null;;
		try{
			stmt=program();
		}catch(SyntaxException se){
			error(se.getMessage());
		}
		return stmt;
	}
	
	
	
	/*
	 * PROGRAM = MAINFUNCTION [FUNCTIONLIST]
	 * */
	private Program program() throws Exception{
		top=new SymbolTable(top);
		return new Program(functionList(),mainFunc());
	}
	
	
	
	
	/*
	 * FUNCTIONLIST = [FUNCTION  FUNCTIONLIST]
	 * */
	private List<Func> functionList() throws Exception {
		List<Func> funcList=new LinkedList<Func>();
		
		if(token.tokenType==TokenType.EOF) error("Main function not found");
		expect(TokenType.FUN);
		
		if(token.tokenType==TokenType.MAIN) funcList.add(Func.NullFunction);
		else{
			//top=new SymbolTable(null);
			funcList.add(function());
			funcList.addAll(functionList());
		}
		
		return funcList;
	}
	
	
/*
 * MAINFUNCTION = "fun" "main" "(" ")" BLOCK
 * */
	private Func mainFunc() throws Exception{
		
		//expect(TokenType.FUN);
		expect(TokenType.MAIN);
		expect(TokenType.LEFT_PAREN);
		expect(TokenType.RIGHT_PAREN);
		
		return new Func(new Id(new Word("main",TokenType.MAIN),DataType.Int),block(top),new SymbolTable(top),new LinkedList<Id>());
	}
	
	
	
	
/*
 * FUNCTION = "fun" IDENTIFIER "(" PARAMLIST ")" [RETURN_TYPE] BLOCK
 * */
	private Func function() throws Exception{
		
		SymbolTable ST=new SymbolTable(top);
		Token tok=token;
		expect(TokenType.ID);
		
		expect(TokenType.LEFT_PAREN);
		List<Id> paramList=parameterList();
		for(Id id :paramList){
			ST.put((Word)id.op, id);
		}
		expect(TokenType.RIGHT_PAREN);
		Func f=new Func(new Id((Word)tok,returnType()),null,new SymbolTable(ST),paramList);
		top.put((Word)tok, f);
		
		Block b=block(ST);
		f.setBody(b);
		//returnType();
		
		top.put((Word) tok, f);
		return 	f;
		
	}
	
	
		
	/*
	 * RETURN_TYPE = DOUBLE | BOOL | INT | "fun" "(" [TYPELIST] ")" RETURN_TYPE
	 * */
	private DataType returnType() throws Exception {
		if(token.tokenType==TokenType.FUN){
			nextToken();
			expect(TokenType.LEFT_PAREN);
			List<DataType> paramTypeList= typeList();
			expect(TokenType.RIGHT_PAREN);
			DataType retType= returnType();
			return new FunctionReturnType("func",TokenType.BASICTYPE,retType,paramTypeList);
		}else{
			return dataType();
		}
		
	}

	/*
	 * TYPELIST = [TYPESPECIFIER "," TYPELIST] |[TYPESPECIFIER]
	 * */
	private List<DataType> typeList() throws Exception {
		List<DataType> typeList=new LinkedList<DataType>();
		while(token.tokenType==TokenType.BASICTYPE){
		DataType t=(DataType) token;
		typeList.add(t);
		nextToken();
		if(token.tokenType==TokenType.COMMA) nextToken();
		
		}
		return typeList;
		
			
	}

	/*
	 * PARAMLIST = [PARAMDECL "," PARAMLIST] | [PARAMDECL] 
	 *PARAMDECL = TYPESPECIFIER IDENTIFIER
	 * */
	private List<Id> parameterList(/*SymbolTable ST*/) throws Exception{
		Id id=null;
		List<Id> paramTypeList=new LinkedList<Id>();
		while(token.tokenType==TokenType.ID) {
			
			Token tok=token;
			nextToken();
			DataType p=dataType();
			if(token.tokenType==TokenType.COMMA) nextToken();
						
			
			id=new Id((Word)tok,p);
			paramTypeList.add(id);
			//ST.put((Word)tok, id);
			
		}
		
		return paramTypeList;
	}
	
	
	/*
	 * 
	 * 
	 * BLOCK = "{" DECLS  STMTS "}"
	 * */ 
	private Block block(SymbolTable ST) throws Exception{
		Block bl;
		expect(TokenType.LEFT_CURL);
		//if a new block is entered we create a new piece of table for that block
		SymbolTable savedEnv=ST;
		ST=new SymbolTable(ST);
		List<Declaration>dL=decls(ST);
		List<Stmt> s=stmts(ST);
		bl=new Block(dL,s);
		expect(TokenType.RIGHT_CURL);
		ST=savedEnv; 
		return bl;
	}
	
	/*
	 *DECLS = [DECL DECLS] 
		DECL = "var" IDENTIFIER TYPESPECIFIER [INITIALIZER]; 
	 * */
	private List<Declaration> decls(SymbolTable ST) throws Exception{
		List<Declaration> decList=new LinkedList<Declaration>();
		Id id=null;
		while(token.tokenType==TokenType.VAR) {
			nextToken();
			Token tok=token;
			expect(TokenType.ID);
			DataType p=dataType();
			
			
			
			if(token.tokenType==TokenType.ASSIGN) {
				
				nextToken();
				Expr defaultVal=logicalOr(ST);
				if(p==DataType.Function){
					if(!(defaultVal.type instanceof FunctionReturnType))error("the default value of "+tok+" must be of type "+p+" instead of " + defaultVal.type);}
				else if(defaultVal.type!=p ) //newly added
					error("the default value of "+tok+" must be of type "+p);
				
				if(p==DataType.Function) {//newly added
					DataType retType=((FunctionReturnType)defaultVal.type).returnType;
					List<DataType> paramTypeList=((FunctionReturnType)defaultVal.type).paramTypeList;
					id=new FunctionDeligate((Word)tok,retType,paramTypeList);//newly added
					}
				else
					id=new Id((Word)tok,p,defaultVal);
				decList.add(new Declaration(id,defaultVal));
			} else{
				if(p==DataType.Function)
					id=new FunctionDeligate((Word)tok);//newly added
				else
					id=new Id((Word)tok,p);
				decList.add(new Declaration(id));
			}
			expect(TokenType.SEMICOLON);	
			//decList.add(new Declaration(id));
			ST.put((Word)tok, id);
									
			
			}
	return decList;
	} 
	
	
	/*
	 * TYPESPECIFIER =BASICTYPE 
	 * */
	private DataType dataType() throws Exception{
		
		DataType p=null;
		try{
		 p=(DataType)token;
		}catch(ClassCastException ex){
			if(token.tokenType==TokenType.FUN){
				token=DataType.Function; //if the token was a fun keyword (which is used for function definition) assume it is a datatype not a function definition in this case 
				p=DataType.Function;
			}
			else error("Data type expected before " + token.tokenType);
		}
		expect(TokenType.BASICTYPE);
		
		return p;
	}
	
	/*
	 *STMTS = [STMT STMTS] 
	 * */
	private List<Stmt> stmts(SymbolTable ST) throws Exception{
		List<Stmt> statementList=new LinkedList<Stmt>();
		if(token.tokenType==TokenType.RIGHT_CURL || token.tokenType== TokenType.EOF);//statementList.add(Stmt.Null);
		else{
			Stmt s=stmt(ST);
			if(s!=Stmt.Null)statementList.add(s);
			statementList.addAll(stmts(ST));
			}
		return statementList;
	}
	
	/*<stmt> := <identifier> '=' <logicalORexpr> 
			 	 | 'if' <logicalORexpr> <stmt>
			 	 | 'if' <logicalORexpr> <stmt> 'else' <stmt>
			 	 | 'while' <logicalORexpr> <stmt>
				 |<returnStmt>
				 |<block> */
	private Stmt stmt(SymbolTable ST) throws Exception{
		Expr exp;
		Stmt s1,s2;
		switch(token.tokenType){
		case SEMICOLON:
		case RIGHT_CURL:
		
			nextToken();
			return Stmt.Null;
		case IF:
			expect(TokenType.IF);
			
			exp=logicalOr(ST);
			
			s1=stmt(ST);
			if(token.tokenType!=TokenType.ELSE) return new If(exp,s1);
			expect(TokenType.ELSE);
			s2=stmt(ST);
			return new IfElse(exp,s1,s2);
		case WHILE:
			
			expect(TokenType.WHILE);
			//expect(TokenType.LEFT_PAREN);
			exp=logicalOr(ST);
			//expect(TokenType.RIGHT_PAREN);
			s1=stmt(ST);
			
			return new While(exp,s1);
		
		case LEFT_CURL:
			return block(ST);
		case RETURN:
			return returnStmt(ST);
		case EOF:
			return Stmt.Null;
		case PRINTLN:
			return printStatement(ST);
		default:
			return assign(ST);
		}
		
	}
	/*
	 *PRINTLN = "println" "(" EXPR ")" SEMICOLON 
	 * */
	private Stmt printStatement(SymbolTable ST) throws Exception{
		expect(TokenType.PRINTLN);
		
		expect(TokenType.LEFT_PAREN);
		
		Expr exp=logicalOr(ST);
		
		expect(TokenType.RIGHT_PAREN);
		expect(TokenType.SEMICOLON);
		return new PrintStmt(exp);
		
	}
	
	/*<returnStmt> := 'return' ';' 
			  |'return' <logicalORexpr> */
	private Stmt returnStmt(SymbolTable ST) throws Exception{
	//Stmt stmt;
	expect(TokenType.RETURN);
	if(token.tokenType==TokenType.SEMICOLON)  return new Return();
	else return new ReturnExpression(logicalOr(ST));

	}

	/*
	 * ASSIGNMWNT_STMT = IDENTIFIER "=" EXPR SEMICOLON
	 * */
	Stmt assign(SymbolTable ST) throws Exception{
		Expr exp;
		Stmt stmt=null;
		Token t=token;
		expect(TokenType.ID);
		Word w=(Word)t;
		Id id=ST.get(w);
		if(id==null) error("'"+t.toString()+"' undeclared");
		
		
		
		expect(TokenType.ASSIGN);
		if(token.tokenType== TokenType.ASYNC){
			exp=async(ST);
		}
		else
			if(token.tokenType==TokenType.DASYNC){exp=dAsync(ST);}
		else
			exp=logicalOr(ST);
		if(id instanceof FunctionDeligate || id.type== DataType.Function){
			
		//if(! (exp instanceof Func)) error("type error: assigning "+exp.type+" to "+ id.type);
	
		if(!((exp instanceof FuncCall) &&(exp.type instanceof FunctionReturnType)) &&
				!(exp instanceof FunctionDeligate)){
			error("type error: assigning "+exp.type+" to "+ id.type);
		}
		
			List<Id> paramTypeList=new LinkedList<Id>();
			if(exp instanceof FuncCall){
				for(DataType dt:((FunctionReturnType)exp.type).paramTypeList){
					paramTypeList.add(new Id(new Word("", TokenType.ID), dt));
				}
				
				((FunctionDeligate) id).setType(((FunctionReturnType)exp.type).returnType);
				((FunctionDeligate) id).setParams(paramTypeList);
				
			}else{
				paramTypeList=((FunctionDeligate)exp).paramList;
				((FunctionDeligate) id).setType(exp.type);
				((FunctionDeligate) id).setParams(paramTypeList);
				}
			
		}
		
		stmt=new Assign(id,exp);
		
		expect(TokenType.SEMICOLON);
		return stmt;
	}
	
	
	
	/*
	 * 
	 * ASYNC_BLOCK = "async" "{" "return" LOGICAL_OR_EXPR  "}"
	 * 	  
	 * */
	private AsynchExpr async(SymbolTable ST) throws Exception{
		AsynchExpr exp;
		expect(TokenType.ASYNC);
		expect(TokenType.LEFT_CURL);
		expect(TokenType.RETURN);
		
		exp=new AsynchExpr(logicalOr(ST));
		expect(TokenType.RIGHT_CURL);
		return exp;
	}
	
	private DAsync dAsync(SymbolTable ST) throws Exception{
		DAsync exp = null;
		expect(TokenType.DASYNC);
		expect(TokenType.LEFT_CURL);
		Token tok=token;
		expect(TokenType.STRING);
		String url=((StringLiteral)tok).getValue();
		expect(TokenType.COMMA);
		 tok=token;
		expect(TokenType.ID);
			Expr funcName=ST.get((Word)tok);
			if((funcName instanceof Func)){
			
		exp=new DAsync(url,functionCall(ST,(Func)funcName));
		}else{
			error("function call expected in dAsync statement");
		}
		expect(TokenType.RIGHT_CURL);
		return exp;
		
	}
	


	/*<logicalORexpr> := <logicalORexpr> '||' <logicalANDexpr> 
	 | <logicalANDexpr>  */
	private Expr logicalOr(SymbolTable ST) throws Exception{
		Expr exp=null;
					 exp=logicalANDexpr(ST);
		while(token.tokenType==TokenType.OR){
			Token t=token;
			nextToken();
			exp=new Or(t,exp,logicalANDexpr(ST));
		}
		
		return exp;
		
	}
	
	/*<logicalANDexpr> := <logicalANDexpr> '&&' <equalityExpr> 
	  | <equalityExpr>*/
	private Expr logicalANDexpr(SymbolTable ST) throws Exception{
		Expr exp=equality(ST);
		while(token.tokenType == TokenType.AND){
			Token t=token;
			nextToken();
			exp=new And(t,exp,equality(ST));
		}
		return exp;
	}
	
	/* <equalityExpr> := <equalityExpr> '==' <relExpr>
		| <equalityExpr> '!=' <relExpr> 
		| <relExpr> */
	private Expr equality(SymbolTable ST) throws Exception{
		Expr exp=relational(ST);
		while(token.tokenType==TokenType.EQ ||token.tokenType==TokenType.NE){
			Token t=token;
			nextToken();
			exp=new Rel(t,exp,relational(ST));
		}
		return exp;	
	}

	/*<relExpr> := <relExpr> '>' <additiveExpr> 
	   | <relExpr> '<' <additiveExpr>
	   | <relExpr> '>=' <additiveExpr>
	   | <relExpr> '<=' <additiveExpr> */
	private Expr relational(SymbolTable ST) throws Exception{
		Expr exp=additiveExpr(ST);
		
		if(token.tokenType==TokenType.LE 
				|| token.tokenType==TokenType.LT 
				|| token.tokenType == TokenType.GE 
				||token.tokenType==TokenType.GT){
			Token t=token;
			nextToken();
			return new Rel(t,exp,additiveExpr(ST));
		}
		return exp;
	}
	
	/*<additiveExpr> := <additiveExpr> '+' <multiplicativeExpr> 
		| <additiveExpr> '-' <multiplicativeExpr> */
	private Expr additiveExpr(SymbolTable ST) throws Exception{
		Expr exp=multiplicativeExpr(ST);
		while(token.tokenType==TokenType.PLUS||token.tokenType==TokenType.MINUS){
			Token t=token;
			nextToken();
			exp=new Arith(t,exp,multiplicativeExpr(ST));
		}
		return exp;
	}
	
	/* <multiplicativeExpr> := <multiplicativeExpr> '*' <unaryExpr> 
			|<multiplicativeExpr> '/' <unaryExpr> */
	private Expr multiplicativeExpr(SymbolTable ST) throws Exception{
		Expr exp=unary(ST);
		while(token.tokenType==TokenType.TIMES || token.tokenType==TokenType.DIVIDED){
			Token t=token;
			nextToken();
			exp= new Arith(t,exp,unary(ST));
		}
		return exp;
	}
	
	/* <unaryExpr> := '!' <unaryExpr>
	      |	 <unaryExpr>
	      | <factor> */
	private Expr unary(SymbolTable ST) throws Exception{
//		if(token.tokenType==TokenType.MINUS){
//			nextToken();
//			return new Unary(new Token(TokenType.MINUS),unary(ST));
//		}else
			if(token.tokenType==TokenType.NOT){
			Token t=token;
			nextToken();
			return new Not(t,unary(ST));
		}else{
			return factor(ST);
		}
	}
	
	/*<factor> := '(' <logicalORexpr> ')' 
	  | <identifier> 
	  |<intConstant>
	  |<doubleConstant>
	  |<booleanConstant> 
	  |<function_call>
	  */
	private Expr factor(SymbolTable ST) throws Exception{
		Expr exp=null;
		switch(token.tokenType)
		{
		case LEFT_PAREN:
			nextToken();
			exp=logicalOr(ST);		
			expect(TokenType.RIGHT_PAREN);
			return exp;
		case ID:
			exp=ST.get((Word)token);
			if(exp==null)error(token.toString()+" undeclared");
			nextToken();
			if(exp instanceof Func && token.tokenType==TokenType.LEFT_PAREN)//Functioncall
			{
				
			return functionCall(ST,(Func)exp);
				
				//return new FuncCall(exp.op,exp.type,expList);
			}
			//nextToken();
			
			return exp;
		case INT:
			exp=new Constant(token,DataType.Int);
			nextToken();
			return exp;
		case DOUBLE:
			exp=new Constant(token,DataType.Double);
			nextToken();
			return exp;
		case TRUE:
			exp=new Constant(token,DataType.Bool);
			nextToken();
			return exp;
		case FALSE:
			exp=new Constant(token,DataType.Bool);
			nextToken();
			return exp;
		case FUN:
			return anonymousFunction(ST);
		//case ASYNC:
		//	??
			
		default: error("Syntax error: an  expression expected before " + token.tokenType );
		}
		return exp; 
				
	}
	/*
	 * FUNCTION_CALL = IDENTIFIER "(" [ ARG_LIST] ")" ";"
	 * */
	private FuncCall functionCall(SymbolTable ST, Func f) throws Exception{
		//nextToken();
		expect(TokenType.LEFT_PAREN);
		List<Expr> expList=argList(ST);
		expect(TokenType.RIGHT_PAREN);
		//Func f=(Func)exp;
		Iterator<Expr> it=expList.iterator();
		int paramCount=1;
		//check argument type
		for(Id id: f.paramList){
			
		
			if(it.next().type!=id.type)
				error("type mismatch function "+f.op+" expects "+id.type+" as "+(paramCount++) +"th arguement ");
		}
		
		
		if(f instanceof FunctionDeligate){
			return new DeligateCall(f.op,f.type,expList);
		}
		
		return new FuncCall(f.op,f.type,expList);

		}
	
	/*
	 * ARG_LIST = LOGICAL_OR_EXPR "," ARG_LIST 
	      |LOGICAL_OR_EXPR
	 * */
	private List<Expr> argList(SymbolTable ST) throws Exception { //should return a Function_call object or a typelist
		List<Expr> argumentList=new LinkedList<Expr>();
		while(token.tokenType!=TokenType.RIGHT_PAREN){
			Expr exp=logicalOr(ST);
			argumentList.add(exp);
			if(token.tokenType==TokenType.COMMA) nextToken();//what if there is ',)'
		}
		return argumentList;
	}

/*
 * ANONYMOUS_FUNCTION = "fun" "(" [PARAMLIST] ")" TYPESPECIFIER BLOCK
 * */
	private Func anonymousFunction(SymbolTable ST) throws Exception{//???
		DataType p=null;
		expect(TokenType.FUN);
		expect(TokenType.LEFT_PAREN);
		List<Id> paramList=parameterList();
		for(Id id :paramList){
			ST.put((Word)id.op, id);
		}
		expect(TokenType.RIGHT_PAREN);
		if(token.tokenType==TokenType.BASICTYPE)
			p = dataType();  
		else p=DataType.Void;
		return	new AnonymousFunc(new Id(new Word("AnonymousFunc"+(++funcNum),TokenType.BASICTYPE),p),block(ST),new SymbolTable(ST),paramList);
	}

	


	// display the error message as an exception
	private void error(String s)throws SyntaxException{
		throw new SyntaxException("near line "+lexer.getLine()+" : "+s);
	}
	private void expect(TokenType t) throws Exception{
		if(token.tokenType==t)nextToken();
		else error("Syntax Error,"+t.toString()+" Expected, before "+token.tokenType);
					
	}
	
	private void nextToken(){
		token=lexer.nextToken();
	}
	
}
