package com.tusharsharma.calculator.apigatewayserver;

import java.util.Stack;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tusharsharma.calculator.apigatewayserver.proxies.AdditionMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.DivisionMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.MultiplicationMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.PercentageMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.SubtractionMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.utils.MyGenericsStack;

@RestController
public class RESTHandlerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdditionMicroserviceProxy additionProxy;
	@Autowired
	private SubtractionMicroserviceProxy subtractionProxy;
	@Autowired
	private MultiplicationMicroserviceProxy multiplicationProxy;
	@Autowired
	private DivisionMicroserviceProxy divisionProxy;
	@Autowired
	private PercentageMicroserviceProxy percentageProxy;

	@PostMapping("api-gateway")
	String performOperation(@RequestBody String expression) {
	
		return Double.toString(RESTHandlerController.evaluate(expression));

	}

	public static Double evaluate(String exp) {

		String expSpacePlus = exp.replaceAll("\\+", " \\+ ");
		String expSpaceMinus = expSpacePlus.replaceAll("-", " - ");
		String expSpaceDivision = expSpaceMinus.replaceAll("/", " / ");
		String expSpaceMulti = expSpaceDivision.replaceAll("\\*", " \\* ");
		String expSpaceBracket = expSpaceMulti.replaceAll("\\(", " \\( ");
		String expression = expSpaceBracket.replaceAll("\\)", " \\) ");
		
		System.out.print(expression);

		char[] tokens = expression.toCharArray();

		// Stack for numbers: 'values'
		Stack<Double> values = new Stack<Double>();

		// Stack for Operators: 'ops'
		Stack<Character> ops = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {
			// Current token is a whitespace, skip it
			if (tokens[i] == ' ')
				continue;

			// Current token is a number, push it to stack for numbers
			if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in number
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')
					sbuf.append(tokens[i++]);
				values.push(Double.parseDouble(sbuf.toString()));
			}

			// Current token is an opening brace, push it to 'ops'
			else if (tokens[i] == '(')
				ops.push(tokens[i]);

			// Closing brace encountered, solve entire brace
			else if (tokens[i] == ')') {
				while (ops.peek() != '(')
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
			}

			// Current token is an operator.
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				// While top of 'ops' has same or greater precedence to current
				// token, which is an operator. Apply operator on top of 'ops'
				// to top two elements in values stack
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));

				// Push current token to 'ops'.
				ops.push(tokens[i]);
			}
		}

		// Entire expression has been parsed at this point, apply remaining
		// ops to remaining values
		while (!ops.empty())
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));

		// Top of 'values' contains result, return it
		return values.pop();
	}

	// Returns true if 'op2' has higher or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static Double applyOp(char op, Double b, Double a) {
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
		return 0.0;
	}
	
	
	
	
	
//	
//	public static int evaluate(String exp) {
//
//		String expSpacePlus = exp.replaceAll("\\+", " \\+ ");
//		String expSpaceMinus = expSpacePlus.replaceAll("-", " - ");
//		String expSpaceDivision = expSpaceMinus.replaceAll("/", " / ");
//		String expSpaceMulti = expSpaceDivision.replaceAll("\\*", " \\* ");
//		String expSpaceBracket = expSpaceMulti.replaceAll("\\(", " \\( ");
//		String expression = expSpaceBracket.replaceAll("\\)", " \\) ");
//
//		char[] tokens = expression.toCharArray();
//
//		// Stack for numbers: 'values'
//		Stack<Integer> values = new Stack<Integer>();
//
//		// Stack for Operators: 'ops'
//		Stack<Character> ops = new Stack<Character>();
//
//		for (int i = 0; i < tokens.length; i++) {
//			// Current token is a whitespace, skip it
//			if (tokens[i] == ' ')
//				continue;
//
//			// Current token is a number, push it to stack for numbers
//			if (tokens[i] >= '0' && tokens[i] <= '9') {
//				StringBuffer sbuf = new StringBuffer();
//				// There may be more than one digits in number
//				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
//					sbuf.append(tokens[i++]);
//				values.push(Integer.parseInt(sbuf.toString()));
//			}
//
//			// Current token is an opening brace, push it to 'ops'
//			else if (tokens[i] == '(')
//				ops.push(tokens[i]);
//
//			// Closing brace encountered, solve entire brace
//			else if (tokens[i] == ')') {
//				while (ops.peek() != '(')
//					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
//				ops.pop();
//			}
//
//			// Current token is an operator.
//			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
//				// While top of 'ops' has same or greater precedence to current
//				// token, which is an operator. Apply operator on top of 'ops'
//				// to top two elements in values stack
//				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
//					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
//
//				// Push current token to 'ops'.
//				ops.push(tokens[i]);
//			}
//		}
//
//		// Entire expression has been parsed at this point, apply remaining
//		// ops to remaining values
//		while (!ops.empty())
//			values.push(applyOp(ops.pop(), values.pop(), values.pop()));
//
//		// Top of 'values' contains result, return it
//		return values.pop();
//	}
//
//	// Returns true if 'op2' has higher or same precedence as 'op1',
//	// otherwise returns false.
//	public static boolean hasPrecedence(char op1, char op2) {
//		if (op2 == '(' || op2 == ')')
//			return false;
//		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
//			return false;
//		else
//			return true;
//	}
//
//	// A utility method to apply an operator 'op' on operands 'a'
//	// and 'b'. Return the result.
//	public static int applyOp(char op, int b, int a) {
//		switch (op) {
//		case '+':
//			return a + b;
//		case '-':
//			return a - b;
//		case '*':
//			return a * b;
//		case '/':
//			if (b == 0)
//				throw new UnsupportedOperationException("Cannot divide by zero");
//			return a / b;
//		}
//		return 0;
//	}

}
