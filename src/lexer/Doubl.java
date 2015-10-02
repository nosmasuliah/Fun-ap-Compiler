package lexer;



public class Doubl extends Token {
	private double value;
	public Doubl(double v) {
		super(TokenType.DOUBLE);
		value=v;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value+"";
	}

}
