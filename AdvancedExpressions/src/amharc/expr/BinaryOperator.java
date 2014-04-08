package amharc.expr;

public abstract class BinaryOperator extends Expression {
	protected final Expression lhs, rhs;
	
	public BinaryOperator(Expression lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public abstract String operatorString();
	
	public Expression getLhs() {
		return lhs;
	}
	
	public Expression getRhs() {
		return rhs;
	}
	
	public String toString() {
		String str = "";
		
		if(lhs.getPriority() < this.getPriority())
			str += "(" + lhs + ")";
		else
			str += lhs;
		
		str += operatorString();
		
		if(rhs.getPriority() <= this.getPriority())
			str += "(" + rhs + ")";
		else
			str += rhs;
		
		return str;
	}
}
