

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.parser.Parser;

public class Runner {
	public static void main(String[] args) {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		try {
			while((line = bw.readLine()) != null) {
				try {
					System.out.println(Parser.parse(line));
				} catch(IllegalExpressionException e) {
					System.out.println("Illegal expression");
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
