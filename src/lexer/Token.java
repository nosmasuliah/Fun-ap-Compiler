package lexer;



public class Token {
	public TokenType tokenType;
	public Token(TokenType type) {
		tokenType=type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tokenType == null) ? 0 : tokenType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (tokenType != other.tokenType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		switch(tokenType){
		case INT:
			return " int ";
		case AND:
			return " && ";
		case ASSIGN:
			return " = ";
		case ASYNC:
			return "asynch";
		case BASICTYPE:
			return "??DataType ??";
		case BOOL:
			return " boolean ";
		case BREAK:
			return " break ";
		case COMMA:
			return " , ";
		case CONTINUE:
			return " continue ";
		case DIVIDED:
			return " / ";
		case DOUBLE:
			return " double ";
		case ELSE:
			return "else";
		case EOF:
			return " ";
		case EQ:
			return " == ";
		case FALSE:
			return " false ";
		case FUN:
			return " fun ";
		case GE:
			return " >= ";
		case GT:
			return " > ";
		case ID:
			break;
		case IF:
			return " if ";
		case INVALID_CHAR:
			break;
		case LE:
			return " <= ";
		case LEFT_CURL:
			return " { ";
		case LEFT_PAREN:
			return " ( ";
		case LEFT_SQURE_BRACE:
			return " [ ";
		case LT:
			return " < ";
		case MAIN:
			return "main";
		case MINUS:
			return " - ";
		case NE:
			return "!=";
		case NOT:
			return " ! ";
		case OR:
			return " || ";
		case PLUS:
			return " + ";
		case RETURN:
			return " return ";
		case RIGHT_CURL:
			return "}";
		case RIGHT_PAREN:
			return "(";
		case RIGHT_SQUARE_BRACE:
			return " ] ";
		case SEMICOLON:
			break;
		case STRING:
			break;
		case TIMES:
			return " * ";
		case TRUE:
			return " true ";
		case VAR:
			return " var ";
		case VOID:
			return " void ";
		case WHILE:
			return " while ";
		default:
			return tokenType.toString();
			
		}
		return tokenType.toString();
	}
	
	

}
