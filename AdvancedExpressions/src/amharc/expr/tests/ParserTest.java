package amharc.expr.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.parser.Parser;

public class ParserTest {
	private double calc(String str) throws IllegalExpressionException {
		return Double.parseDouble(Parser.parse(str).toString());
	}
	
	@Test
	public void testFull() throws IllegalExpressionException {
		assertEquals("4.0", Parser.parse("2 +    2").toString());
		assertEquals("7.0", Parser.parse("1 + 2 * 3").toString());
		assertEquals("x*x+x", Parser.parse("x*x+x").toString());
		assertEquals("0.0", Parser.parse("0 * x").toString());
		assertEquals("x+x*x", Parser.parse("1 * (x * 1 + x * 1 * x)").toString());
		assertEquals("-x+x", Parser.parse("-x + (1 * x + (42.5 - 42.5))").toString());
		assertEquals("exp(x)", Parser.parse("(exp(1 * x * (2 * 0.5)))' + 0").toString());
		assertEquals("x+x", Parser.parse("(x * x)'").toString());
		assertEquals("(-x)*(-x)", Parser.parse("(-x + 0) * (0 - x)").toString());
		assertEquals("3.0", Parser.parse("12/4").toString());
		assertEquals("x/2.0", Parser.parse("x/2").toString());
		assertEquals("42.0", Parser.parse("x * 4 + 2 $ 10").toString());
		assertEquals("x+(-1.0)", Parser.parse("x + (-1)").toString());
		assertEquals("x-(-1.0)", Parser.parse("x - (-1)").toString());
		assertEquals("sin(x)", Parser.parse("sin(x)").toString());	
		assertEquals("x-x", Parser.parse("x-x").toString());
		assertEquals("-(x+x)", Parser.parse("-(x+x)").toString());
		assertEquals("1.0-x", Parser.parse("1.000-x").toString());
		assertEquals("x*x+x/x+(x+x)+(x-x)", Parser.parse("x*x+x/x+(x+x)+(x-x)").toString());
		assertEquals("x*x*(x/x)*(x+x)*(x-x)", Parser.parse("(x*x)*(x/x)*(x+x)*(x-x)").toString());
	}
	
	@Test
	public void integralTest() throws IllegalExpressionException {
		assertEquals(1.0/3.0, Parser.parse("x * x").integral(0.0, 1.0, 100000), 1e-5);
	}
	
	@Test
	public void testAdditiveSimplifications() throws IllegalExpressionException {
		assertEquals("0 + x", "x", Parser.parse("0 + x").toString());
		assertEquals("x + 0", "x", Parser.parse("x + 0").toString());
		assertEquals("x - 0", "x", Parser.parse("x - 0").toString());
		assertEquals("0 - x", "-x", Parser.parse("0 - x").toString());
		
		assertEquals("4 + 2", "6.0", Parser.parse("4 + 2").toString());
		assertEquals("6 + 0", "6.0", Parser.parse("6 + 0").toString());
		assertEquals("0 + 6", "6.0", Parser.parse("0 + 6").toString());
		assertEquals("6 - 0", "6.0", Parser.parse("6 - 0").toString());
		assertEquals("0 - (-6)", "6.0", Parser.parse("0 - (-6)").toString());
		assertEquals("4 - 2", "2.0", Parser.parse("4 - 2").toString());
		assertEquals("4 + (-4)", "0.0", Parser.parse("4 + (-4)").toString());
		assertEquals("5 + (-4)", "1.0", Parser.parse("5 + (-4)").toString());
	}
	
	@Test
	public void testDoubleUnaryMinus() throws IllegalExpressionException {
		assertEquals("x", Parser.parse("-(-x)").toString());
		assertEquals("0.0", Parser.parse("-(-0)").toString());
		assertEquals("1.0", Parser.parse("-(-1)").toString());
		assertEquals("2.0", Parser.parse("-(-2)").toString());
	}
	
	@Test
	public void testMultiplicativeSimplifications() throws IllegalExpressionException {
		assertEquals("0 * x", "0.0", Parser.parse("0 * x").toString());
		assertEquals("x * 0", "0.0", Parser.parse("x * 0").toString());
		assertEquals("1 * x", "x", Parser.parse("1 * x").toString());
		assertEquals("x * 1", "x", Parser.parse("x * 1").toString());
		
		assertEquals("0 / x", "0.0", Parser.parse("0 / x").toString());
		assertEquals("x / 1", "x", Parser.parse("x / 1").toString());
		
		assertEquals("3 * 2", "6.0", Parser.parse("3 * 2").toString());
		assertEquals("42.0 * 0", "0.0", Parser.parse("42.0 * 0").toString());
		assertEquals("0.5 * 2", "1.0", Parser.parse("0.5 * 2").toString());
		
		assertEquals("8 * 1", "8.0", Parser.parse("8 * 1").toString());
		assertEquals("9 / 1", "9.0", Parser.parse("9 / 1").toString());
		assertEquals("1 / 10", "0.1", Parser.parse("1 / 10").toString());
		assertEquals("0 / 10", "0.0", Parser.parse("0 / 10").toString());
		assertEquals("0 / 1", "0.0", Parser.parse("0 / 1").toString());
		
		assertEquals("100 / 5", "20.0", Parser.parse("100 / 5").toString());
	}
	
	@Test
	public void testEval() throws IllegalExpressionException {
		assertEquals("x - 2 * x $ 5", -5.0, calc("x - 2 * x $ 5"), 1e-5);
		assertEquals("x + 2 * x $ 5", 15.0, calc("x + 2 * x $ 5"), 1e-5);
		assertEquals("x * 2 * x $ 5", 50.0, calc("x * 2 * x $ 5"), 1e-5);
		assertEquals("x / (2 * x) $ 5", 0.5, calc("x / (2 * x) $ 5"), 1e-5);
	}
	
	@Test
	public void testDerivative() throws IllegalExpressionException {
		assertEquals("(x^2)'", "x+x", Parser.parse("(x * x)'").toString());
		assertEquals("(1/x)'", "(-1.0)/(x*x)", Parser.parse("(1 / x)'").toString());
		assertEquals("(x + sin (2 * x))'", "1.0+2.0*cos(2.0*x)", Parser.parse("(x + sin (2 * x))'").toString());
		assertEquals("(exp (x*x))'", "(x+x)*exp(x*x)", Parser.parse("(exp(x*x))'").toString());
		assertEquals("(cos(x) * (-x))'", "(-sin(x))*(-x)+cos(x)*(-1.0)", Parser.parse("(cos(x) * (-x))'").toString());
		assertEquals("(exp(x) - exp(2x))'", "exp(x)-2.0*exp(2.0*x)", Parser.parse("(exp(x) - exp(2*x))'").toString());
	}
	
	@Test
	public void testTrigonometricIdentities() throws IllegalExpressionException {
		assertEquals("sin^2 + cos^2 = 1 on -18.48", 1.0, calc("(cos(x) * cos(x) + sin(x) * sin(x)) $ -18.48"), 1e-5);
		assertEquals("sin^2 + cos^2 = 1 on 12e10", 1.0, calc("(cos(x) * cos(x) + sin(x) * sin(x)) $ 12E10"), 1e-5);
	}
	
	@Test
	public void testConstantFunctions() throws IllegalExpressionException {
		double values[] = {12, 4.3, -19.2, Math.PI, 0.0, 1.0, -1.0};
		for(double v : values) {
			assertEquals("exp(" + v + ")", Math.exp(v), calc("exp(" + v + ")"), 1e-5);
			assertEquals("sin(" + v + ")", Math.sin(v), calc("sin(" + v + ")"), 1e-5);
			assertEquals("cos(" + v + ")", Math.cos(v), calc("cos(" + v + ")"), 1e-5);
			assertEquals("-(" + v + ")",   -v,          calc("-(" + v + ")"),   1e-5);
			
			assertEquals("exp(x) $ " + v, Math.exp(v), calc("exp(x) $ " + v), 1e-5);
			assertEquals("sin(x) $ " + v, Math.sin(v), calc("sin(x) $ " + v), 1e-5);
			assertEquals("cos(x) $ " + v, Math.cos(v), calc("cos(x) $ " + v), 1e-5);
			assertEquals("-(x) $ " + v,   -v,          calc("-(x) $ " + v),   1e-5);
		}
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testSingleParen() throws IllegalExpressionException {
		Parser.parse("(");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testWrongParen() throws IllegalExpressionException {
		Parser.parse("(x(");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testNoDoubleParen() throws IllegalExpressionException {
		Parser.parse("((x");
	}
	
	
	@Test (expected=IllegalExpressionException.class)
	public void testNoClosingParen() throws IllegalExpressionException {
		Parser.parse("(x");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testEmpty() throws IllegalExpressionException {
		Parser.parse("");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testWrongFunction() throws IllegalExpressionException {
		Parser.parse("exr(x)");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testWrongVariable() throws IllegalExpressionException {
		Parser.parse("y + 2");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testUnfinishedSum() throws IllegalExpressionException {
		Parser.parse("x + ");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testUnfinishedProduct() throws IllegalExpressionException {
		Parser.parse("x * ");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testUnfinishedUnaryMinus() throws IllegalExpressionException {
		Parser.parse("-");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testIllegalDoubleUnaryMinus() throws IllegalExpressionException {
		Parser.parse("--x");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testIllegalDoubleUnaryMinusNumber() throws IllegalExpressionException {
		Parser.parse("--1");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testUnfinishedExp() throws IllegalExpressionException {
		Parser.parse("exp");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testWrongSideExp() throws IllegalExpressionException {
		Parser.parse("x exp");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testWrongSideDerivative() throws IllegalExpressionException {
		Parser.parse("'x");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testIllegalCharInDouble() throws IllegalExpressionException {
		Parser.parse("14d");
	}
	
	@Test (expected=IllegalExpressionException.class)
	public void testIllegalEvaluate() throws IllegalExpressionException {
		Parser.parse("x $ x");
	}
}
