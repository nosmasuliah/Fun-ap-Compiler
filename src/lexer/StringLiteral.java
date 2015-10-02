package lexer;



public class StringLiteral extends Token{
	private String value;
	public StringLiteral(String v) {
		super(TokenType.STRING);
		value=v;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value+"";
	}

}
