package amharc.expr.lexer;

public class TimesToken extends Token {
	private final static TimesToken instance = new TimesToken();
	
	private TimesToken() { }
	
	public static TimesToken getInstance() {
		return instance;
	}
}
