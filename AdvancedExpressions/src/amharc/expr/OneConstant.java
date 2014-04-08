package amharc.expr;

public final class OneConstant extends Constant {
	private static final OneConstant instance = new OneConstant();
	
	private OneConstant() { 
		super(1);
	}
	
	public static OneConstant getInstance() {
		return instance;
	}
	
	@Override
	public Expression multiply(Expression e) {
		return e;
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
