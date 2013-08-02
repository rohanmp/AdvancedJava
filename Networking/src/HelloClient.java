import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class HelloClient {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the server's IP address: ");
		String ip = scan.nextLine();
		
		try {
			Socket sock = new Socket(ip, 9090);
			BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			String in = input.readLine();
			System.out.println("Got: " + in);
			
		} 
		catch (UnknownHostException e) {
			System.out.println("No host with that IP found.");
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("Got an eye-yo exception.");
			e.printStackTrace();
		}
	}
}
