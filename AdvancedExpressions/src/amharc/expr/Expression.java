package amharc.expr;

/**
 * Class representing abstract expressions
 * 
 * @author Krzysztof Pszeniczny
 */
/**
 * @author amharc
 *
 */
public abstract class Expression {
	/**
	 * Returns priority of an expression
	 * 
	 * @return Priority of expression
	 */
	abstract int getPriority();
	
	/**
	 * Evaluate expression at x
	 * @param x
	 * @return Value of expression at x
	 */
	public abstract double evaluate(double x);
	
	/**
	 * Accept an visitor
	 * 
	 * @param visitor which should visit the expression
	 * @return {@link visitor}-dependent value
	 */
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
	
	/**
	 * Compute definite integral
	 * 
	 * @param begin Left endpoint of the interval
	 * @param end Right endpoint of the interval
	 * @param steps Number of intervals
	 * @return Approximation to the integral
	 */
	public double integral(double begin, double end, int steps) {
		double res = 0;
		double delta = (end - begin) / steps;
		for(int i = 0; i < steps; ++i)
			res += delta * evaluate(begin + i * delta);
		return res;
	}
	
	/**
	 * Compute exponential function
	 * 
	 * @return exp(this)
	 */
	public Expression exp() {
		return new ExponentialFunction(this);
	}
	
	/**
	 * Compute sine function
	 * 
	 * @return sin(this)
	 */
	public Expression sin() {
		return new SineFunction(this);
	}
	
	/**
	 * Compute cosine function
	 * 
	 * @return cos(this)
	 */
	public Expression cos() {
		return new CosineFunction(this);
	}
	
	/**
	 * Get negated this
	 * 
	 * @return -this
	 */
	public Expression negate() {
		return new UnaryMinus(this);
	}
	
	
	/**
	 * Get the sum of two expressions
	 * 
	 * @param that The other expression
	 * @return this + that
	 */
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
	
	/**
	 * Get the difference between two expressions
	 * 
	 * @param that The other expression
	 * @return this - that
	 */
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
	
	/**
	 * Get the product of two expressions
	 * 
	 * @param that The other expression
	 * @return this * that
	 */
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
	
	/**
	 * Get the quotient of two expressions
	 * 
	 * @param that The other expression
	 * @return this / that
	 */
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
	
	/**
	 * Get the derivative
	 * 
	 * @return d(this)/dx
	 */
	abstract public Expression derivative();
	

	/**
	 * Get a constant expression
	 * 
	 * @param value
	 * @return Expression representing {@link value}
	 */
	public static Expression getConstant(double value) {
		if(value == 0)
			return ZeroConstant.getInstance();
		else if(value == 1)
			return OneConstant.getInstance();
		else
			return new Constant(value);
	}
	
	/**
	 * Get a variable
	 * 
	 * @return Expression representing x
	 */
	public static Expression getVariable() {
		return Variable.getInstance();
	}
}
