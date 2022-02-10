package utils;

// A Java program to evaluate a given expression where tokens are separated by space.
import java.util.Stack;

public class EvaluateString {
	public static Double evaluate(String expression) {
		char[] tokens = expression.toCharArray();

		// Stack for numbers: 'values'
		Stack<Double> values = new Stack<Double>();

		// Stack for Operators: 'ops'
		Stack<Character> ops = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {
			
			//blank space
			if (tokens[i] == ' ')
				continue;

			//En caso de tratarse de un número
			if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'){
				StringBuffer sbuf = new StringBuffer();

				//capturar todos los dígitos
				while ((i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') || (i < tokens.length && tokens[i] == '.'))
					sbuf.append(tokens[i++]);
				
				//se forma un nuevo stackeo con el número
				values.push(Double.parseDouble(sbuf.toString()));
				i--;
			}

			//se abre paréntesis
			else if (tokens[i] == '(')
				ops.push(tokens[i]);

			//cierre paréntesis
			else if (tokens[i] == ')') {
				while (ops.peek() != '(')
					values.push((double) applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
			}

			// Se trata de un operador
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				
				//hasprecedence determina el orden de precedencia / orden de importancia de ecuación
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
					values.push((double) applyOp(ops.pop(), values.pop(), values.pop()));

				// Nuevo stackeo de operador
				ops.push(tokens[i]);
			}
		}

		// Entire expression has been
		// parsed at this point, apply remaining
		// ops to remaining values
		while (!ops.empty())
			values.push((double) applyOp(ops.pop(), values.pop(), values.pop()));

		// Top of 'values' contains
		// result, return it
		return values.pop();
	}

	// Returns true if 'op2' has higher
	// or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	// A utility method to apply an
	// operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static double applyOp(char op, double b, double a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}

	// Driver method to test above methods
	public static void main(String[] args) {
		System.out.println(EvaluateString.evaluate("10 + 2 * 6"));
		System.out.println(EvaluateString.evaluate("100 * 2 + 12"));
		System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 )"));
		System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 ) / 14"));
	}
}
