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

        // Exit confirmation
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        loginFormPanel.this,
                        "Do you want to exit the program?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Title panel
        titleLabel = new JLabel("WELCOME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(DARK_GREEN);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // CardLayout main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(BACKGROUND_WHITE);

        // Main menu with selection buttons
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(BACKGROUND_WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton adminButton = createStyledButton("Login as Admin", DARK_GREEN, Color.WHITE);
        JButton studentButton = createStyledButton("Login as Student", LIGHT_GREEN, Color.BLACK);

        gbc.gridx = 0; gbc.gridy = 0;
        menuPanel.add(adminButton, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        menuPanel.add(studentButton, gbc);

        // Admin login form
        JPanel adminLoginPanel = createAdminLoginForm("admin", "admin123", () -> {
            new adminDashboard().setVisible(true);
            dispose();
        });

        // Student login form with email only
        JPanel studentLoginPanel = createStudentEmailForm(() -> {
            new studentDashboard().setVisible(true);
            dispose();
        });

        // Button actions
        adminButton.addActionListener(e -> {
            titleLabel.setText("ADMIN LOGIN");
            cardLayout.show(mainPanel, "adminLogin");
        });

        studentButton.addActionListener(e -> {
            titleLabel.setText("STUDENT LOGIN");
            cardLayout.show(mainPanel, "studentLogin");
        });

        // Add to main panel
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(adminLoginPanel, "adminLogin");
        mainPanel.add(studentLoginPanel, "studentLogin");

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARK_GREEN, 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        return button;
    }

    // Admin login form panel
    private JPanel createAdminLoginForm(String correctUser, String correctPass, Runnable onSuccess) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Admin Username:");
        userLabel.setForeground(DARK_GREEN);
        JTextField userField = new JTextField(20);
        styleTextField(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(DARK_GREEN);
        JPasswordField passField = new JPasswordField(20);
        styleTextField(passField);

        JButton loginButton = createStyledButton("Login", DARK_GREEN, Color.WHITE);
        JButton backButton = createStyledButton("Back", new Color(200, 200, 200), Color.BLACK);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(userLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(passLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(passField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(BACKGROUND_WHITE);
        buttonPanel.add(backButton);
        buttonPanel.add(loginButton);
        
        gbc.gridx = 0; gbc.gridy = 2;
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
            titleLabel.setText("WELCOME");
            cardLayout.show(mainPanel, "menu");
            userField.setText("");
            passField.setText("");
        });

        return panel;
    }

    // Student email-only form
    private JPanel createStudentEmailForm(Runnable onSuccess) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel emailLabel = new JLabel("Student Email:");
        emailLabel.setForeground(DARK_GREEN);
        JTextField emailField = new JTextField(20);
        styleTextField(emailField);

        JButton loginButton = createStyledButton("Continue", ACCENT_GREEN, Color.WHITE);
        JButton backButton = createStyledButton("Back", new Color(200, 200, 200), Color.BLACK);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(emailField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(BACKGROUND_WHITE);
        buttonPanel.add(backButton);
        buttonPanel.add(loginButton);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            if (isValidEmail(email)) {
                onSuccess.run();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        backButton.addActionListener(e -> {
            titleLabel.setText("WELCOME");
            cardLayout.show(mainPanel, "menu");
            emailField.setText("");
        });

        return panel;
    }

    private void styleTextField(JTextField field) {
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARK_GREEN, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    // Simple email validation
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains("fatima.edu.ph");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginFormPanel app = new loginFormPanel();
            app.setVisible(true);
        });
    }
}