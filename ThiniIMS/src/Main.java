import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Welcome");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 300);
			frame.setLocationRelativeTo(null);
			frame.setContentPane(new loginFormPanel()); // Load the panel with buttons
			frame.setVisible(true);
		});
	}
}
