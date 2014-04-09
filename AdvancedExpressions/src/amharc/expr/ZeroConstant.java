package amharc.expr;

/**
 * Singleton class representing constant equal to 0.0
 * 
 * @author Krzysztof Pszeniczny
 */
public final class ZeroConstant extends Constant {
	private static final ZeroConstant instance = new ZeroConstant();
	
	private ZeroConstant() { 
		super(0);
	}
	
	public static ZeroConstant getInstance() {
		return instance;
	}
	
	@Override
	public Expression add(Expression e) {
		return e;
	}
	
	@Override
	public Expression subtract(Expression e) {
		return e.negate();
	}
	
	@Override
	public Expression multiply(Expression e) {
		return getInstance();
	}
	
	@Override
	public Expression divide(Expression e) {
		return getInstance();
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
