import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;




public class Mazungumzo extends JFrame implements ActionListener{
	
	ChatClient client;
	JTextArea area;
	JTextField input;

	public static void main(String[] args) {
		new Mazungumzo();
	}
	
	public Mazungumzo() {
		super("Mazungumzo");
		setSize(new Dimension(400,600));
		setLayout(new BorderLayout());
		initializeComponents();
		
		String ip = JOptionPane.showInputDialog("What's the server's IP address?");
		client = new ChatClient(ip);
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new UpdateTask(), 0, 500);
		
		setVisible(true);
	}

	class UpdateTask extends TimerTask {

		public void run() {
			update();
		}
	}
	
	private void update() {
		String text = client.recieve();
		while (text != null) {
			area.setText(area.getText() + "\n" + text);
			text = client.recieve();
		}
	}
	
	private void initializeComponents() {
		area = new JTextArea(33, 20);
		area.setEditable(false);
		area.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(area);
		add(scroll, BorderLayout.NORTH);
		
		input = new JTextField(30);
		input.addActionListener(this);
		add(input, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == input) {
			client.send(input.getText());
			input.setText("");
		}
	}
}
