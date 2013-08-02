import java.util.Scanner;


public class Test {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.print(" > ");
			String input = scan.nextLine();
			System.out.println(" > " + calc.calculate(input));
		}
	}
}
