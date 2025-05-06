import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginFormPanel extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private JLabel titleLabel;
	private final Color DARK_GREEN = new Color(0, 102, 51);
	private final Color LIGHT_GREEN = new Color(144, 238, 144);
	private final Color BACKGROUND_WHITE = new Color(255, 255, 255);
	private final Color ACCENT_GREEN = new Color(50, 205, 50);

	public loginFormPanel() {
		setTitle("Login System");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(loginFormPanel.this, "Do you want to exit the program?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setLayout(new BorderLayout());
		try {
			ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/FatimaBackGround.jpg"));
			Image img = backgroundImage.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
			backgroundLabel.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			System.err.println("Background image not found!");
		}

		titleLabel = new ShadowedLabel("WELCOME TO IMS");

		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setPreferredSize(new Dimension(800, 150));
		titlePanel.setLayout(new GridBagLayout());
		GridBagConstraints titleGbc = new GridBagConstraints();
		titleGbc.gridx = 0;
		titleGbc.gridy = 0;
		titleGbc.insets = new Insets(0, 0, 20, 0);
		titlePanel.add(titleLabel, titleGbc);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		mainPanel.setOpaque(false);

		JPanel menuPanel = new JPanel(new GridBagLayout());
		menuPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JButton adminButton = createStyledButton("Login as Admin", DARK_GREEN, Color.WHITE);
		JButton studentButton = createStyledButton("Login as Student", LIGHT_GREEN, Color.BLACK);

		gbc.gridx = 0;
		gbc.gridy = 0;
		menuPanel.add(adminButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		menuPanel.add(studentButton, gbc);

		JPanel adminLoginPanel = createAdminLoginForm("admin", "admin123", () -> {
			new adminDashboard().setVisible(true);
			dispose();
		});

		JPanel studentLoginPanel = createStudentEmailForm(() -> {
			new studentDashboard().setVisible(true);
			dispose();
		});

		adminButton.addActionListener(e -> {
			((ShadowedLabel) titleLabel).setText("ADMIN LOGIN");
			cardLayout.show(mainPanel, "adminLogin");
		});

		studentButton.addActionListener(e -> {
			((ShadowedLabel) titleLabel).setText("STUDENT LOGIN");
			cardLayout.show(mainPanel, "studentLogin");
		});

		mainPanel.add(menuPanel, "menu");
		mainPanel.add(adminLoginPanel, "adminLogin");
		mainPanel.add(studentLoginPanel, "studentLogin");

		JPanel content = new JPanel(new BorderLayout());
		content.setOpaque(false);
		content.add(titlePanel, BorderLayout.NORTH);
		content.add(mainPanel, BorderLayout.CENTER);

		backgroundLabel.add(content);
		setContentPane(backgroundLabel);
	}

	private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(180, 40));
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DARK_GREEN, 1),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
		return button;
	}

	private JPanel createAdminLoginForm(String correctUser, String correctPass, Runnable onSuccess) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel userLabel = new JLabel("Admin Username:");
		userLabel.setForeground(Color.WHITE);
		JTextField userField = new JTextField(20);
		styleTextField(userField);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setForeground(Color.WHITE);
		JPasswordField passField = new JPasswordField(20);
		styleTextField(passField);

		JButton loginButton = createStyledButton("Login", DARK_GREEN, Color.WHITE);
		JButton backButton = createStyledButton("Back", new Color(200, 200, 200), Color.BLACK);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(userLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(userField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(passLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(passField, gbc);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		buttonPanel.setOpaque(false);
		buttonPanel.add(backButton);
		buttonPanel.add(loginButton);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		panel.add(buttonPanel, gbc);

		loginButton.addActionListener(e -> {
			String username = userField.getText();
			String password = new String(passField.getPassword());
			if (username.equals(correctUser) && password.equals(correctPass)) {
				onSuccess.run();
			} else {
				JOptionPane.showMessageDialog(this, "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
			}
		});

		backButton.addActionListener(e -> {
			((ShadowedLabel) titleLabel).setText("WELCOME TO IMS");
			cardLayout.show(mainPanel, "menu");
			userField.setText("");
			passField.setText("");
		});

		return panel;
	}

	private JPanel createStudentEmailForm(Runnable onSuccess) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel emailLabel = new JLabel("Student Email:");
		emailLabel.setForeground(Color.WHITE);
		JTextField emailField = new JTextField(20);
		styleTextField(emailField);

		JButton loginButton = createStyledButton("Continue", ACCENT_GREEN, Color.WHITE);
		JButton backButton = createStyledButton("Back", new Color(200, 200, 200), Color.BLACK);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(emailLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(emailField, gbc);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		buttonPanel.setOpaque(false);
		buttonPanel.add(backButton);
		buttonPanel.add(loginButton);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		panel.add(buttonPanel, gbc);

		loginButton.addActionListener(e -> {
			String email = emailField.getText();
			if (isValidEmail(email)) {
				onSuccess.run();
			} else {
				JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Invalid Email",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		backButton.addActionListener(e -> {
			((ShadowedLabel) titleLabel).setText("WELCOME TO IMS");
			cardLayout.show(mainPanel, "menu");
			emailField.setText("");
		});

		return panel;
	}

	private void styleTextField(JTextField field) {
		field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DARK_GREEN, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		field.setFont(new Font("Arial", Font.PLAIN, 14));
	}

	private boolean isValidEmail(String email) {
		if (email == null || email.isEmpty()) return false;
		return email.contains("@") && email.contains("fatima.edu.ph");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			loginFormPanel app = new loginFormPanel();
			app.setVisible(true);
		});
	}

	// ShadowedLabel class for better contrast
	class ShadowedLabel extends JLabel {
		public ShadowedLabel(String text) {
			super(text);
			setFont(new Font("Serif", Font.BOLD, 36));
			setHorizontalAlignment(SwingConstants.CENTER);
			setVerticalAlignment(SwingConstants.CENTER);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			g2.setColor(Color.WHITE);
			g2.drawString(getText(), getInsets().left + 2, getHeight() / 2 + getFont().getSize() / 2 + 2);
			g2.setColor(Color.BLACK);
			g2.drawString(getText(), getInsets().left, getHeight() / 2 + getFont().getSize() / 2);

			g2.dispose();
		}
	}
}