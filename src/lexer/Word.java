package lexer;





/**
 * @author samy
 *
 */
public class Word extends Token {
	public String lexeme="";
	public Word(String lexm,TokenType type) {
		super(type);
		lexeme=lexm;
	}
	@Override
	public String toString() {
		return lexeme ;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lexeme == null) ? 0 : lexeme.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (lexeme == null) {
			if (other.lexeme != null)
				return false;
		} else if (!lexeme.equals(other.lexeme))
			return false;
		return true;
	}



	public static final Word and= new Word("&&",TokenType.AND),
							 or=new Word("||",TokenType.OR),
							 eq=new Word ("==",TokenType.EQ),
							 ne=new Word("!=",TokenType.NE),
							 le=new Word("<=",TokenType.LE),
							 ge=new Word(">=",TokenType.GE),
							 //minus=new Word("minus",TokenType.MINUS),
							 True=new Word("true",TokenType.TRUE),
							 False=new Word("false",TokenType.FALSE);
	
	

}
