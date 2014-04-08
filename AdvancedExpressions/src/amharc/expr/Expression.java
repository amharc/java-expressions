package amharc.expr;

public abstract class Expression {
	public abstract int getPriority();
	
	public abstract double eval(double x);
	
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
	
	public double integral(double begin, double end, int steps) {
		double res = 0;
		double delta = (end - begin) / steps;
		for(int i = 0; i < steps; ++i)
			res += delta * eval(begin + i * delta);
		return res;
	}
	
	public Expression exp() {
		return new ExponentialFunction(this);
	}
	
	public Expression sin() {
		return new SineFunction(this);
	}
	
	public Expression cos() {
		return new CosineFunction(this);
	}
	
	public Expression negate() {
		return new UnaryMinus(this);
	}
	
	public Expression add(Expression that) {
		final Expression origin = this;
		
		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Plus(origin, e);
			}
			
			@Override
			public Object visit(ZeroConstant e) {
				return origin;
			}
		});
	}
	
	public Expression subtract(Expression that) {
		final Expression origin = this;
		
		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Minus(origin, e);
			}
			
			@Override
			public Object visit(ZeroConstant e) {
				return origin;
			}
		});
	}
	
	public Expression multiply(Expression that) {
		final Expression origin = this;
		
		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Times(origin, e);
			}
			
			@Override
			public Object visit(ZeroConstant e) {
				return ZeroConstant.getInstance();
			}
			
			@Override
			public Object visit(OneConstant e) {
				return origin;
			}
		});
	}
	
	
	public Expression divide(Expression that) {
		final Expression origin = this;
		
		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Div(origin, e);
			}
		
			@Override
			public Object visit(OneConstant e) {
				return origin;
			}
		});
	}
	
	abstract public Expression derivative();

	public static Expression getConstant(double i) {
		if(i == 0)
			return ZeroConstant.getInstance();
		else if(i == 1)
			return OneConstant.getInstance();
		else
			return new Constant(i);
	}
	
	public static Expression getVariable() {
		return Variable.getInstance();
	}
}
