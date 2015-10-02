package lexer;



public class Int extends Token {
	private int value;
	public Int(int v) {
		super(TokenType.INT);
		value=v;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return value+"";
	}

}
