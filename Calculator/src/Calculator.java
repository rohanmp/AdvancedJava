import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Calculator {
	ArrayList <String> operators;
	ArrayList <Integer> numbers;
	Map <String, Integer> variables;
	
	public Calculator() {
		operators = new ArrayList <String> ();
		numbers = new ArrayList <Integer> ();
		variables = new HashMap <String, Integer> ();
	}
	
	public int calculate(String expr) {
		numbers.clear();
		operators.clear();
		expr = expr.replaceAll("\\s+", "");
		System.out.println(expr);
		String[] parts = expr.split("((?=(\\+|\\-|\\*|\\/|\\(|\\)))|(?<=(\\+|\\-|\\*|\\/|\\(|\\))))");	
		
		for (String part : parts) {
			System.out.print("[" + part + "]");
		}
		for (String part : parts) {
			if (part.length() == 0) {
				continue;
			}
			put(part);
		}
		while (operators.size() > 0) {
			reduce();
		}
		return numbers.get(0);
		
	}
	
	public void put(String elem) {
		if (elem.equals("(")) {
			operators.add(elem);
		}
		else if (elem.equals(")")) {
			while(! operators.get(operators.size() - 1).equals("(")) {
				reduce();
			}
			operators.remove(operators.size() - 1);
		}
		else if (elem.matches("\\d+")) {
			putNumber(elem);
		}
		else {
			putOperator(elem);
		}
		
	}
	
	public void reduce() {
		int num2 = numbers.remove(numbers.size() - 1);
		int num1 = numbers.remove(numbers.size() - 1);
		String op = operators.remove(operators.size() - 1);
		if (op.equals("+")) {
			numbers.add(num1 + num2);
		}
		if (op.equals("-")) {
			numbers.add(num1 - num2);
		}
		if (op.equals("*")) {
			numbers.add(num1 * num2);
		}
		if (op.equals("/")) {
			numbers.add(num1 / num2);
		}
	}
	
	public void putNumber(String num) {
		int value = Integer.parseInt(num);
		numbers.add(value);
	
	}
	
	public void putOperator(String op) {
			while (operators.size() > 0 && precedence(operators.get(operators.size() - 1)) >= precedence(op) ) {
				reduce();
			}
		operators.add(op);
		
	}
	
	public int precedence(String op) {
		if (op.equals("+") || op.equals("-")) {
			return 1;
		}
		if (op.equals("*") || op.equals("/")) {
			return 2;
		}
		return 0;
    }
	
}
