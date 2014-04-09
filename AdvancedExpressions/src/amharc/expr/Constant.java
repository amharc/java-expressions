package amharc.expr;

/**
 * Class representing constant expressions
 * @author Krzysztof Pszeniczny
 */
public class Constant extends Literal {
	private final double value;
	
	/**
	 * Creates a constant.
	 * Should not be used directly, use {@link Expression.getConstant(double)}
	 * 
	 * @see {@link Expression.getConstant(double)}
	 * @param value Value to be held by the constant.
	 */
	Constant(double value) {
		this.value = value;
	}
	
	@Override
	public int getPriority() {
		if(value >= 0)
			return 1000;
		else
			return 0;
	}
	
	@Override
	public double evaluate(double x) {
		return value;
	}
	
	@Override
	public Expression exp() {
		return Expression.getConstant(Math.exp(this.value));
	}
	
	@Override
	public Expression sin() {
		return Expression.getConstant(Math.sin(this.value));
	}
	
	@Override
	public Expression cos() {
		return Expression.getConstant(Math.cos(this.value));
	}
	
	@Override
	public Expression negate() {
		return Expression.getConstant(-this.value);
	}
	
	@Override
	public Expression add(Expression that) {
		final Constant origin = this;
		
		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Plus(origin, e);
			}
			
			@Override
			public Object visit(Constant e) {
				return Expression.getConstant(origin.value + e.value);
			}
			
			@Override
			public Object visit(ZeroConstant e) {
				return origin;
			}
		});
	}
	
	@Override
	public Expression subtract(Expression that) {
		final Constant origin = this;
		
		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Minus(origin, e);
			}
			
			@Override
			public Object visit(Constant e) {
				return Expression.getConstant(origin.value - e.value);
			}
			
			@Override
			public Object visit(ZeroConstant e) {
				return origin;
			}
		});
	}

	@Override
	public Expression multiply(Expression that) {
		final Constant origin = this;

		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Times(origin, e);
			}

			@Override
			public Object visit(Constant e) {
				return Expression.getConstant(origin.value * e.value);
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

	@Override
	public Expression divide(Expression that) {
		final Constant origin = this;

		return (Expression) that.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return new Div(origin, e);
			}

			@Override
			public Object visit(Constant e) {
				return Expression.getConstant(origin.value / e.value);
			}
			
			@Override
			public Object visit(OneConstant e) {
				return origin;
			}
		});
	}
	
	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public Expression derivative() {
		return Expression.getConstant(0);
	}

	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}

	/**
	 * Get the value held by the expression
	 * 
	 * @return Value held by the expression
	 */
	public double getValue() {
		return value;
	}
}