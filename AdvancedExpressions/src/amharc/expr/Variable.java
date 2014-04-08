package amharc.expr;

public class Variable extends Literal {
	private static Variable instance = new Variable();
	
	private Variable() { }
	
	public static Variable getInstance() {
		return instance;
	}
	
	@Override
	public double eval(double x) {
		return x;
	}
	
	@Override
	public String toString() {
		return "x";
	}
	
	@Override
	public Expression derivative() {
		return Expression.getConstant(1);
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
