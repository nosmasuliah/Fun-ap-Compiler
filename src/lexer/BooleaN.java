package lexer;



public class BooleaN extends Token {
	private boolean value;
	public BooleaN(boolean v) {
		super(TokenType.BOOL);
		value=v;
	}
	public boolean getValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value+"";
	}
	
}
