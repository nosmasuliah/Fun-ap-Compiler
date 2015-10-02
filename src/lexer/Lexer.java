package lexer;


import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Hashtable;




public class Lexer {
	private StreamTokenizer input;
	Hashtable<String,Word> reservedWords=new Hashtable<String,Word>();
	
	public Lexer(Reader r) {
		input=new StreamTokenizer(r);
		input.resetSyntax();
		input.quoteChar('\"');
		input.slashSlashComments(true);
		input.slashStarComments(true);
		input.eolIsSignificant(false);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('_', '_');
		input.ordinaryChar('+');
		input.ordinaryChar('*');
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.whitespaceChars('\u0000', ' ');
		input.quoteChar('\"');
		input.parseNumbers();
		
		reserve(new Word("if",TokenType.IF));
		reserve(new Word("else",TokenType.ELSE));
		reserve(new Word("while",TokenType.WHILE));
		reserve(new Word("return",TokenType.RETURN));
		reserve(new Word("break",TokenType.BREAK));
		reserve(new Word("continue",TokenType.CONTINUE));
		reserve(new Word("fun",TokenType.FUN));
		reserve(new Word("var",TokenType.VAR));
		reserve(new Word("return",TokenType.RETURN));
		reserve(new Word("async",TokenType.ASYNC));
		reserve(new Word("dasync",TokenType.DASYNC));
		reserve(new Word("main",TokenType.MAIN));
		reserve(new Word("println",TokenType.PRINTLN));
		reserve(Word.True); 
		reserve(Word.False);
		reserve(DataType.Int);
		reserve(DataType.STring);
		reserve(DataType.Bool);
		reserve(DataType.Double);
		reserve(DataType.Void);
		
	}
	
	void reserve(Word w){
		reservedWords.put(w.lexeme, w);
	}
	
	public int getLine(){
		return input.lineno();
	}
	
	
	//look ahead
	public boolean lookahead(char c) throws IOException{
		if(input.nextToken()==c)
			return true;
		else{
			input.pushBack();
			return false;
		}
	}
	
	public Token nextToken(){
		try {
			switch(input.nextToken()){

			case StreamTokenizer.TT_WORD:
				Word w=reservedWords.get(input.sval);
				if(w!=null) return w;
				w=new Word(input.sval,TokenType.ID);
				return w;
			
			case '&':
				if(lookahead('&')) return Word.and; else return new Token(TokenType.INVALID_CHAR);
			case '|':
				if(lookahead('|')) return Word.or;else return new Token(TokenType.INVALID_CHAR);
			case '>':
				if(lookahead('=')) return new Token(TokenType.GE); else return new Token(TokenType.GT);
			case '<':
				if(lookahead('=')) return new Token(TokenType.LE); else return new Token(TokenType.LT);
			case '=':
				if(lookahead('=')) return new Token(TokenType.EQ); else return new Token(TokenType.ASSIGN);
			case '!':
				if(lookahead('=')) return new Token(TokenType.NE); else return new Token(TokenType.NOT);
				
			case StreamTokenizer.TT_NUMBER:
				if(Math.floor(input.nval)==input.nval) return new Int((int)input.nval);
				else return new Doubl(input.nval);
			
			case '+':
				return new Token(TokenType.PLUS);
			case '-':
				return new Token(TokenType.MINUS);
			case '*':
				return new Token(TokenType.TIMES);
			case '(':
				return new Token(TokenType.LEFT_PAREN);
			case ')':
				return new Token(TokenType.RIGHT_PAREN);
			case '{':
				return new Token(TokenType.LEFT_CURL);
			case '}':
				return new Token(TokenType.RIGHT_CURL);
			case ';':
				return new Token(TokenType.SEMICOLON);
			case ',': return new Token(TokenType.COMMA);
			case '\"':
				return new StringLiteral(input.sval);
			default:
				return new Token(TokenType.EOF);
		
			}
		} catch (IOException e) {
			return new Token(TokenType.EOF);
		}
	}

}
