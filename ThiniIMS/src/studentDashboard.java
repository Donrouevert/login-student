import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class studentDashboard extends JFrame {
    
    // For storing the student ID who is logged in
    private String loggedInStudentId = "";
    
    // Table models
    private DefaultTableModel availableItemsModel;
    private DefaultTableModel borrowedItemsModel;
    private DefaultTableModel returnHistoryModel;

    public studentDashboard() {
        super("Student Dashboard");
        setLayout(null);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1200, 90);
        panel.setBackground(new Color(173, 216, 230));
        panel.setLayout(null);

        JLabel title = new JLabel("EQUIPMENT BORROW/RETURN");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(20, 20, 500, 40);
        panel.add(title);

        // Add logout button to header
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(1080, 30, 100, 30);
        logoutButton.setBackground(new Color(255, 99, 71));
        logoutButton.setForeground(Color.WHITE);
        panel.add(logoutButton);

        // Navigation panel
        JPanel nav = new JPanel();
        nav.setLayout(null);
        nav.setBounds(0, 90, 280, 670);
        nav.setBackground(Color.decode("#e3e3e3"));

        JButton borrowButton = new JButton("Borrow Peripheral");
        borrowButton.setBounds(50, 30, 180, 30);
        borrowButton.setBackground(new Color(100, 149, 237));
        borrowButton.setForeground(Color.WHITE);
        nav.add(borrowButton);

        JButton returnButton = new JButton("Return Peripheral");
        returnButton.setBounds(50, 70, 180, 30);
        returnButton.setBackground(new Color(60, 179, 113));
        returnButton.setForeground(Color.WHITE);
        nav.add(returnButton);

        // Student info display
        JLabel studentInfoLabel = new JLabel("Logged in as: ");
        studentInfoLabel.setBounds(50, 600, 180, 20);
        nav.add(studentInfoLabel);
        
        JLabel studentIdLabel = new JLabel(loggedInStudentId.isEmpty() ? "Guest" : loggedInStudentId);
        studentIdLabel.setBounds(50, 620, 180, 20);
        nav.add(studentIdLabel);

        // Borrow Equipment Panel
        JPanel borrowEquipmentPanel = new JPanel(null);
        borrowEquipmentPanel.setBounds(280, 90, 920, 670);
        borrowEquipmentPanel.setBackground(new Color(230, 255, 230));
        borrowEquipmentPanel.setVisible(false);

        JLabel fillBorrowForm = new JLabel("Fill Out Borrowing Form");
        fillBorrowForm.setFont(new Font("Arial", Font.BOLD, 16));
        fillBorrowForm.setBounds(30, 20, 250, 25);
        borrowEquipmentPanel.add(fillBorrowForm);

        JTextField studentIdField = new JTextField("Student ID");
        studentIdField.setBounds(30, 50, 200, 25);
        // Add placeholder text behavior
        studentIdField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studentIdField.getText().equals("Student ID")) {
                    studentIdField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (studentIdField.getText().isEmpty()) {
                    studentIdField.setText("Student ID");
                }
            }
        });
        borrowEquipmentPanel.add(studentIdField);

        JButton issueEquipment = new JButton("Issue Equipment");
        issueEquipment.setBounds(250, 50, 180, 25);
        issueEquipment.setBackground(new Color(100, 149, 237));
        issueEquipment.setForeground(Color.WHITE);
        borrowEquipmentPanel.add(issueEquipment);

        // Table with available items
        String[] columnNames = {"Item Name", "Item ID", "Category", "Status"};
        Object[][] data = {
            {"Mouse", "001", "Input Device", "Available"},
            {"Keyboard", "002", "Input Device", "In Use"},
            {"Webcam", "003", "Video", "Available"},
            {"Headset", "004", "Audio", "Available"},
            {"Monitor", "005", "Display", "In Use"}
        };

        availableItemsModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable itemTable = new JTable(availableItemsModel);
        JScrollPane tableScrollPane = new JScrollPane(itemTable);
        tableScrollPane.setBounds(30, 100, 850, 200);
        borrowEquipmentPanel.add(tableScrollPane);
        
        JLabel availableItemsLabel = new JLabel("Available Equipment");
        availableItemsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        availableItemsLabel.setBounds(30, 75, 200, 25);
        borrowEquipmentPanel.add(availableItemsLabel);
        
        JButton borrowSelectedButton = new JButton("Borrow Selected Item");
        borrowSelectedButton.setBounds(30, 310, 180, 25);
        borrowSelectedButton.setBackground(new Color(65, 105, 225));
        borrowSelectedButton.setForeground(Color.WHITE);
        borrowEquipmentPanel.add(borrowSelectedButton);

        // Return Equipment Panel
        JPanel returnEquipmentPanel = new JPanel(null);
        returnEquipmentPanel.setBounds(280, 90, 920, 670);
        returnEquipmentPanel.setBackground(new Color(255, 245, 238));
        returnEquipmentPanel.setVisible(false);

        JLabel returnTitle = new JLabel("Return Equipment");
        returnTitle.setFont(new Font("Arial", Font.BOLD, 16));
        returnTitle.setBounds(30, 20, 200, 25);
        returnEquipmentPanel.add(returnTitle);

        JTextField returnStudentIdField = new JTextField("Student ID");
        returnStudentIdField.setBounds(30, 60, 200, 25);
        // Add placeholder text behavior
        returnStudentIdField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (returnStudentIdField.getText().equals("Student ID")) {
                    returnStudentIdField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (returnStudentIdField.getText().isEmpty()) {
                    returnStudentIdField.setText("Student ID");
                }
            }
        });
        returnEquipmentPanel.add(returnStudentIdField);

        JButton searchBorrowedItems = new JButton("Search Borrowed Items");
        searchBorrowedItems.setBounds(250, 60, 200, 25);
        searchBorrowedItems.setBackground(new Color(70, 130, 180));
        searchBorrowedItems.setForeground(Color.WHITE);
        returnEquipmentPanel.add(searchBorrowedItems);

        // Borrowed items table
        String[] borrowedColumns = {"Item Name", "Item ID", "Borrow Date", "Due Date"};
        Object[][] borrowedData = {
            {"Mouse", "001", "2025-04-30", "2025-05-07"},
            {"Headset", "004", "2025-05-01", "2025-05-08"}
        };
        
        borrowedItemsModel = new DefaultTableModel(borrowedData, borrowedColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable borrowedItemsTable = new JTable(borrowedItemsModel);
        JScrollPane borrowedItemsScrollPane = new JScrollPane(borrowedItemsTable);
        borrowedItemsScrollPane.setBounds(30, 120, 850, 150);
        returnEquipmentPanel.add(borrowedItemsScrollPane);
        
        JLabel borrowedItemsLabel = new JLabel("Your Borrowed Equipment");
        borrowedItemsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        borrowedItemsLabel.setBounds(30, 95, 200, 25);
        returnEquipmentPanel.add(borrowedItemsLabel);

        // Return form section
        JPanel returnFormPanel = new JPanel();
        returnFormPanel.setLayout(null);
        returnFormPanel.setBounds(30, 290, 850, 180);
        returnFormPanel.setBorder(BorderFactory.createTitledBorder("Return Form"));
        returnFormPanel.setBackground(new Color(255, 245, 238));
        returnEquipmentPanel.add(returnFormPanel);
        
        JLabel itemToReturnLabel = new JLabel("Selected Item:");
        itemToReturnLabel.setBounds(20, 30, 100, 25);
        returnFormPanel.add(itemToReturnLabel);
        
        JLabel selectedItemLabel = new JLabel("None selected");
        selectedItemLabel.setBounds(120, 30, 200, 25);
        selectedItemLabel.setFont(new Font("Arial", Font.BOLD, 12));
        returnFormPanel.add(selectedItemLabel);
        
        JLabel conditionLabel = new JLabel("Condition:");
        conditionLabel.setBounds(20, 60, 100, 25);
        returnFormPanel.add(conditionLabel);
        
        String[] conditions = {"Good", "Minor Damage", "Major Damage", "Not Working"};
        JComboBox<String> conditionComboBox = new JComboBox<>(conditions);
        conditionComboBox.setBounds(120, 60, 150, 25);
        returnFormPanel.add(conditionComboBox);
        
        JLabel notesLabel = new JLabel("Notes:");
        notesLabel.setBounds(20, 90, 100, 25);
        returnFormPanel.add(notesLabel);
        
        JTextArea notesTextArea = new JTextArea();
        notesTextArea.setLineWrap(true);
        JScrollPane notesScrollPane = new JScrollPane(notesTextArea);
        notesScrollPane.setBounds(120, 90, 300, 70);
        returnFormPanel.add(notesScrollPane);
        
        JButton returnEquipmentButton = new JButton("Submit Return");
        returnEquipmentButton.setBounds(450, 115, 150, 30);
        returnEquipmentButton.setBackground(new Color(46, 139, 87));
        returnEquipmentButton.setForeground(Color.WHITE);
        returnFormPanel.add(returnEquipmentButton);
        
        // Return history section
        JLabel returnHistoryLabel = new JLabel("Return History");
        returnHistoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        returnHistoryLabel.setBounds(30, 480, 200, 25);
        returnEquipmentPanel.add(returnHistoryLabel);
        
        String[] historyColumns = {"Item Name", "Item ID", "Return Date", "Condition"};
        Object[][] historyData = {};
        
        returnHistoryModel = new DefaultTableModel(historyData, historyColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable returnHistoryTable = new JTable(returnHistoryModel);
        JScrollPane historyScrollPane = new JScrollPane(returnHistoryTable);
        historyScrollPane.setBounds(30, 505, 850, 150);
        returnEquipmentPanel.add(historyScrollPane);

        // Button actions
        borrowButton.addActionListener(e -> {
            borrowEquipmentPanel.setVisible(true);
            returnEquipmentPanel.setVisible(false);
        });

        returnButton.addActionListener(e -> {
            borrowEquipmentPanel.setVisible(false);
            returnEquipmentPanel.setVisible(true);
        });
        
        // Search for borrowed items action
        searchBorrowedItems.addActionListener(e -> {
            String studentId = returnStudentIdField.getText();
            if (studentId.equals("Student ID") || studentId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a Student ID", 
                    "Input Required", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // In a real app, this would search a database
            // For now, we'll just update the loggedInStudentId
            loggedInStudentId = studentId;
            studentIdLabel.setText(loggedInStudentId);
            
            JOptionPane.showMessageDialog(this, 
                "Found borrowed items for Student ID: " + studentId,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Action for selecting an item from borrowed items table
        borrowedItemsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && borrowedItemsTable.getSelectedRow() != -1) {
                int row = borrowedItemsTable.getSelectedRow();
                String itemName = borrowedItemsTable.getValueAt(row, 0).toString();
                String itemId = borrowedItemsTable.getValueAt(row, 1).toString();
                selectedItemLabel.setText(itemName + " (ID: " + itemId + ")");
            }
        });
        
        // Return equipment action
        returnEquipmentButton.addActionListener(e -> {
            if (selectedItemLabel.getText().equals("None selected")) {
                JOptionPane.showMessageDialog(this,
                    "Please select an item to return from the borrowed items table",
                    "Selection Required",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Get the selected item info
            int selectedRow = borrowedItemsTable.getSelectedRow();
            String itemName = borrowedItemsTable.getValueAt(selectedRow, 0).toString();
            String itemId = borrowedItemsTable.getValueAt(selectedRow, 1).toString();
            String condition = conditionComboBox.getSelectedItem().toString();
            String notes = notesTextArea.getText();
            
            // Confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(this,
                "Confirm return of " + itemName + " (ID: " + itemId + ")\n" +
                "Condition: " + condition + "\n" +
                (notes.isEmpty() ? "" : "Notes: " + notes),
                "Confirm Return",
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                // In a real app, this would update a database
                
                // Get current date for return date
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String returnDate = now.format(formatter);
                
                // Add to return history
                returnHistoryModel.addRow(new Object[]{
                    itemName, itemId, returnDate, condition
                });
                
                // Remove from borrowed items
                borrowedItemsModel.removeRow(selectedRow);
                
                // Update available items to show the returned item
                availableItemsModel.addRow(new Object[]{
                    itemName, itemId, getCategoryForItem(itemName), "Available"
                });
                
                // Reset form
                selectedItemLabel.setText("None selected");
                conditionComboBox.setSelectedIndex(0);
                notesTextArea.setText("");
                
                JOptionPane.showMessageDialog(this,
                    "Equipment returned successfully!",
                    "Return Complete",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Logout action
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                // In a real app, this would return to the login screen
            }
        });

        // Add all components
        add(panel);
        add(nav);
        add(borrowEquipmentPanel);
        add(returnEquipmentPanel);
        
        // Initially show borrow panel
        borrowEquipmentPanel.setVisible(true);
    }
    
    // Helper method to get category based on item name
    private String getCategoryForItem(String itemName) {
        switch (itemName.toLowerCase()) {
            case "mouse":
            case "keyboard":
                return "Input Device";
            case "webcam":
                return "Video";
            case "headset":
                return "Audio";
            case "monitor":
                return "Display";
            default:
                return "Other";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            studentDashboard dashboard = new studentDashboard();
            dashboard.setVisible(true);
        });
    }
}