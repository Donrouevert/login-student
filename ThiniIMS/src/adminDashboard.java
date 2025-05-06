import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class adminDashboard extends JFrame {

    // Table models
    private DefaultTableModel inventoryModel;
    private DefaultTableModel adminModel;
    private DefaultTableModel activeBorrowsModel;
    private DefaultTableModel borrowHistoryModel;
    private DefaultTableModel maintenanceModel;

    public adminDashboard () {
        super("Admin Dashboard");
        setLayout(null);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1200, 90);
        panel.setBackground(new Color(173, 216, 230));
        panel.setLayout(null);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/olfu.png")); // Update the path to your actual logo
        Image logoImage = logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Increased size
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(20, 15, 60, 60); // Repositioned to upper left
        panel.add(logoLabel); // Added to panel

        // Add title next to logo
        JLabel titleLabel = new JLabel("Equipment Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setBounds(100, 30, 350, 30);
        panel.add(titleLabel);

        // Add decorative line
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBounds(450, 15, 2, 60);
        separator.setForeground(new Color(0, 51, 102));
        separator.setBackground(new Color(0, 51, 102));
        panel.add(separator);

        // Add system date display
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        JLabel dateLabel = new JLabel(dateFormat.format(new Date()));
        dateLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        dateLabel.setBounds(480, 30, 200, 30);
        panel.add(dateLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(1080, 30, 100, 30);
        logoutButton.setBackground(new Color(255, 99, 71));
        logoutButton.setForeground(Color.WHITE);
        panel.add(logoutButton);

        // Navigation panel
        JPanel nav = new JPanel();
        nav.setLayout(null);
        nav.setBounds(0, 90, 280, 670);
        nav.setBackground(new Color(173, 216, 230));

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        JButton viewButton = new JButton("View Inventory");
        viewButton.setBounds(50, 30, 180, 30);
        viewButton.setBackground(new Color(100, 149, 237));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFont(buttonFont);
        nav.add(viewButton);

        JButton borrowButton = new JButton("Borrow Records");
        borrowButton.setBounds(50, 70, 180, 30);
        borrowButton.setBackground(new Color(60, 179, 113));
        borrowButton.setForeground(Color.WHITE);
        borrowButton.setFont(buttonFont);
        nav.add(borrowButton);

        JButton maintenanceButton = new JButton("Maintenance");
        maintenanceButton.setBounds(50, 110, 180, 30);
        maintenanceButton.setBackground(new Color(70, 130, 180));
        maintenanceButton.setForeground(Color.WHITE);
        maintenanceButton.setFont(buttonFont);
        nav.add(maintenanceButton);

        JButton manageButton = new JButton("Manage Admins");
        manageButton.setBounds(50, 150, 180, 30);
        manageButton.setBackground(new Color(65, 105, 225));
        manageButton.setForeground(Color.WHITE);
        manageButton.setFont(buttonFont);
        nav.add(manageButton);

        JLabel adminInfoLabel = new JLabel("Logged in as:");
        adminInfoLabel.setBounds(50, 600, 180, 20);
        adminInfoLabel.setFont(labelFont);
        nav.add(adminInfoLabel);

        JLabel adminIdLabel = new JLabel("Administrator");
        adminIdLabel.setBounds(50, 620, 180, 20);
        adminIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nav.add(adminIdLabel);

        // View Inventory Panel
        JPanel viewInventoryPanel = new JPanel();
        viewInventoryPanel.setBounds(280, 90, 920, 670);
        viewInventoryPanel.setBackground(new Color(230, 255, 230));
        viewInventoryPanel.setLayout(null);
        viewInventoryPanel.setVisible(true);

        JLabel inventoryTitle = new JLabel("Inventory Management");
        inventoryTitle.setFont(new Font("Arial", Font.BOLD, 16));
        inventoryTitle.setBounds(30, 20, 200, 25);
        viewInventoryPanel.add(inventoryTitle);

        JTextField searchField = new JTextField();
        searchField.setBounds(30, 60, 200, 30);
        viewInventoryPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(240, 60, 100, 30);
        searchButton.setBackground(new Color(100, 149, 237));
        searchButton.setForeground(Color.WHITE);
        viewInventoryPanel.add(searchButton);

        JButton addNewItemButton = new JButton("Add New Item");
        addNewItemButton.setBounds(350, 60, 150, 30);
        addNewItemButton.setBackground(new Color(46, 139, 87));
        addNewItemButton.setForeground(Color.WHITE);
        viewInventoryPanel.add(addNewItemButton);

        String[] columnNames = {"Item Name", "Item ID", "Category", "Condition"};
        Object[][] data = {
            {"Mouse", "001", "Input Device", "WORKING"},
            {"Keyboard", "002", "Input Device", "WORKING"},
            {"Webcam", "003", "Video", "BROKEN"},
            {"Headset", "004", "Audio", "WORKING"},
            {"Monitor", "005", "Display", "WORKING"}
        };

        inventoryModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable inventoryTable = new JTable(inventoryModel);
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        tableScrollPane.setBounds(30, 100, 850, 200);
        viewInventoryPanel.add(tableScrollPane);

        JLabel itemCountLabel = new JLabel("ITEMS (" + inventoryModel.getRowCount() + ")");
        itemCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        itemCountLabel.setBounds(30, 310, 200, 25);
        viewInventoryPanel.add(itemCountLabel);

        JButton editItemButton = new JButton("Edit Selected Item");
        editItemButton.setBounds(30, 340, 180, 30);
        editItemButton.setBackground(new Color(70, 130, 180));
        editItemButton.setForeground(Color.WHITE);
        viewInventoryPanel.add(editItemButton);

        JButton deleteItemButton = new JButton("Delete Selected Item");
        deleteItemButton.setBounds(220, 340, 180, 30);
        deleteItemButton.setBackground(new Color(255, 99, 71));
        deleteItemButton.setForeground(Color.WHITE);
        viewInventoryPanel.add(deleteItemButton);

        // Borrow Records Panel
        JPanel borrowRecordsPanel = new JPanel();
        borrowRecordsPanel.setBounds(280, 90, 920, 670);
        borrowRecordsPanel.setBackground(new Color(255, 245, 238));
        borrowRecordsPanel.setLayout(null);
        borrowRecordsPanel.setVisible(false);

        JLabel borrowTitle = new JLabel("Borrow Records");
        borrowTitle.setFont(new Font("Arial", Font.BOLD, 16));
        borrowTitle.setBounds(30, 20, 200, 25);
        borrowRecordsPanel.add(borrowTitle);

        JTextField studentSearchField = new JTextField("Enter Student ID");
        studentSearchField.setBounds(30, 60, 200, 30);
        studentSearchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studentSearchField.getText().equals("Enter Student ID")) {
                    studentSearchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (studentSearchField.getText().isEmpty()) {
                    studentSearchField.setText("Enter Student ID");
                }
            }
        });
        borrowRecordsPanel.add(studentSearchField);

        JButton findRecordsButton = new JButton("Find Records");
        findRecordsButton.setBounds(240, 60, 150, 30);
        findRecordsButton.setBackground(new Color(70, 130, 180));
        findRecordsButton.setForeground(Color.WHITE);
        borrowRecordsPanel.add(findRecordsButton);

        String[] borrowColumns = {"Student ID", "Item Name", "Item ID", "Borrow Date", "Due Date"};
        Object[][] borrowData = {
            {"S12345", "Mouse", "001", "2025-04-30", "2025-05-07"},
            {"S67890", "Headset", "004", "2025-05-01", "2025-05-08"},
            {"S12345", "Monitor", "005", "2025-05-01", "2025-05-08"}
        };

        activeBorrowsModel = new DefaultTableModel(borrowData, borrowColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable activeBorrowsTable = new JTable(activeBorrowsModel);
        JScrollPane borrowsScrollPane = new JScrollPane(activeBorrowsTable);
        borrowsScrollPane.setBounds(30, 120, 850, 200);
        borrowRecordsPanel.add(borrowsScrollPane);

        JLabel activeBorrowsLabel = new JLabel("Active Borrows");
        activeBorrowsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        activeBorrowsLabel.setBounds(30, 95, 200, 25);
        borrowRecordsPanel.add(activeBorrowsLabel);

        String[] historyColumns = {"Student ID", "Item Name", "Item ID", "Borrow Date", "Return Date"};
        Object[][] historyData = {
            {"S23456", "Keyboard", "002", "2025-04-15", "2025-04-22"},
            {"S34567", "Webcam", "003", "2025-04-20", "2025-04-27"}
        };

        borrowHistoryModel = new DefaultTableModel(historyData, historyColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable borrowHistoryTable = new JTable(borrowHistoryModel);
        JScrollPane historyScrollPane = new JScrollPane(borrowHistoryTable);
        historyScrollPane.setBounds(30, 360, 850, 200);
        borrowRecordsPanel.add(historyScrollPane);

        JLabel borrowHistoryLabel = new JLabel("Borrow History");
        borrowHistoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        borrowHistoryLabel.setBounds(30, 335, 200, 25);
        borrowRecordsPanel.add(borrowHistoryLabel);

        // Maintenance Panel
        JPanel maintenancePanel = new JPanel();
        maintenancePanel.setBounds(280, 90, 920, 670);
        maintenancePanel.setBackground(new Color(240, 248, 255));
        maintenancePanel.setLayout(null);
        maintenancePanel.setVisible(false);

        JLabel maintenanceTitle = new JLabel("Equipment Maintenance");
        maintenanceTitle.setFont(new Font("Arial", Font.BOLD, 16));
        maintenanceTitle.setBounds(30, 20, 250, 25);
        maintenancePanel.add(maintenanceTitle);

        String[] maintenanceColumns = {"Item Name", "Item ID", "Issue", "Status", "Reported Date"};
        Object[][] maintenanceData = {
            {"Webcam", "003", "Not working", "Under Repair", "2025-05-01"},
            {"Keyboard", "006", "Missing keys", "Pending", "2025-05-05"}
        };

        maintenanceModel = new DefaultTableModel(maintenanceData, maintenanceColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable maintenanceTable = new JTable(maintenanceModel);
        JScrollPane maintenanceScrollPane = new JScrollPane(maintenanceTable);
        maintenanceScrollPane.setBounds(30, 60, 850, 200);
        maintenancePanel.add(maintenanceScrollPane);

        JButton updateStatusButton = new JButton("Update Status");
        updateStatusButton.setBounds(30, 270, 150, 30);
        updateStatusButton.setBackground(new Color(65, 105, 225));
        updateStatusButton.setForeground(Color.WHITE);
        maintenancePanel.add(updateStatusButton);

        JButton reportIssueButton = new JButton("Report New Issue");
        reportIssueButton.setBounds(190, 270, 150, 30);
        reportIssueButton.setBackground(new Color(46, 139, 87));
        reportIssueButton.setForeground(Color.WHITE);
        maintenancePanel.add(reportIssueButton);

        // Manage Admins Panel
        JPanel manageAdminsPanel = new JPanel();
        manageAdminsPanel.setBounds(280, 90, 920, 670);
        manageAdminsPanel.setBackground(new Color(245, 245, 245));
        manageAdminsPanel.setLayout(null);
        manageAdminsPanel.setVisible(false);

        JLabel manageTitle = new JLabel("Manage Administrators");
        manageTitle.setFont(new Font("Arial", Font.BOLD, 16));
        manageTitle.setBounds(30, 20, 250, 25);
        manageAdminsPanel.add(manageTitle);

        String[] adminColumns = {"Username", "Admin Name", "ID", "Level"};
        Object[][] adminData = {
            {"admin1", "John Doe", "A001", "Admin"},
            {"admin2", "Jane Smith", "A002", "Student Admin"}
        };

        adminModel = new DefaultTableModel(adminData, adminColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable adminTable = new JTable(adminModel);
        JScrollPane adminScrollPane = new JScrollPane(adminTable);
        adminScrollPane.setBounds(30, 60, 850, 200);
        manageAdminsPanel.add(adminScrollPane);

        JButton addAdminButton = new JButton("Add Administrator");
        addAdminButton.setBounds(30, 270, 180, 30);
        addAdminButton.setBackground(new Color(46, 139, 87));
        addAdminButton.setForeground(Color.WHITE);
        manageAdminsPanel.add(addAdminButton);

        JButton editAdminButton = new JButton("Edit Administrator");
        editAdminButton.setBounds(220, 270, 180, 30);
        editAdminButton.setBackground(new Color(70, 130, 180));
        editAdminButton.setForeground(Color.WHITE);
        manageAdminsPanel.add(editAdminButton);

        JButton deleteAdminButton = new JButton("Delete Administrator");
        deleteAdminButton.setBounds(410, 270, 180, 30);
        deleteAdminButton.setBackground(new Color(255, 99, 71));
        deleteAdminButton.setForeground(Color.WHITE);
        manageAdminsPanel.add(deleteAdminButton);

        // Button functionalities
        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().toLowerCase().trim();
            if (searchTerm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a search term", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultTableModel tempModel = new DefaultTableModel(columnNames, 0);
            boolean found = false;

            for (int i = 0; i < inventoryModel.getRowCount(); i++) {
                String itemName = inventoryModel.getValueAt(i, 0).toString().toLowerCase();
                String itemId = inventoryModel.getValueAt(i, 1).toString().toLowerCase();
                String category = inventoryModel.getValueAt(i, 2).toString().toLowerCase();

                if (itemName.contains(searchTerm) || itemId.contains(searchTerm) || category.contains(searchTerm)) {
                    Object[] row = new Object[4];
                    for (int j = 0; j < 4; j++) {
                        row[j] = inventoryModel.getValueAt(i, j);
                    }
                    tempModel.addRow(row);
                    found = true;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No items found matching '" + searchTerm + "'", "No Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                inventoryTable.setModel(tempModel);
                itemCountLabel.setText("SEARCH RESULTS (" + tempModel.getRowCount() + ")");

                JButton resetButton = new JButton("Show All Items");
                resetButton.setBounds(510, 60, 150, 30);
                resetButton.setBackground(new Color(255, 165, 0));
                resetButton.setForeground(Color.WHITE);
                viewInventoryPanel.add(resetButton);

                resetButton.addActionListener(event -> {
                    inventoryTable.setModel(inventoryModel);
                    itemCountLabel.setText("ITEMS (" + inventoryModel.getRowCount() + ")");
                    viewInventoryPanel.remove(resetButton);
                    viewInventoryPanel.repaint();
                });

                viewInventoryPanel.repaint();
            }
        });

        editItemButton.addActionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to edit", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String itemName = inventoryTable.getValueAt(selectedRow, 0).toString();
            String itemId = inventoryTable.getValueAt(selectedRow, 1).toString();
            String category = inventoryTable.getValueAt(selectedRow, 2).toString();
            String condition = inventoryTable.getValueAt(selectedRow, 3).toString();

            JFrame editFrame = new JFrame("Edit Item");
            editFrame.setSize(400, 300);
            editFrame.setLayout(null);
            editFrame.setLocationRelativeTo(null);

            JLabel itemNameLabel = new JLabel("Item Name:");
            itemNameLabel.setBounds(30, 30, 100, 25);
            editFrame.add(itemNameLabel);

            JTextField itemNameField = new JTextField(itemName);
            itemNameField.setBounds(140, 30, 200, 25);
            editFrame.add(itemNameField);

            JLabel itemIdLabel = new JLabel("Item ID:");
            itemIdLabel.setBounds(30, 70, 100, 25);
            editFrame.add(itemIdLabel);

            JTextField itemIdField = new JTextField(itemId);
            itemIdField.setBounds(140, 70, 200, 25);
            editFrame.add(itemIdField);

            JLabel categoryLabel = new JLabel("Category:");
            categoryLabel.setBounds(30, 110, 100, 25);
            editFrame.add(categoryLabel);

            String[] categories = {"Input Device", "Video", "Audio", "Display", "Other"};
            JComboBox<String> categoryComboBox = new JComboBox<>(categories);
            categoryComboBox.setSelectedItem(category);
            categoryComboBox.setBounds(140, 110, 200, 25);
            editFrame.add(categoryComboBox);

            JLabel conditionLabel = new JLabel("Condition:");
            conditionLabel.setBounds(30, 150, 100, 25);
            editFrame.add(conditionLabel);

            String[] conditions = {"WORKING", "BROKEN"};
            JComboBox<String> conditionComboBox = new JComboBox<>(conditions);
            conditionComboBox.setSelectedItem(condition);
            conditionComboBox.setBounds(140, 150, 200, 25);
            editFrame.add(conditionComboBox);

            JButton updateButton = new JButton("Update");
            updateButton.setBounds(140, 200, 100, 30);
            updateButton.setBackground(new Color(70, 130, 180));
            updateButton.setForeground(Color.WHITE);
            editFrame.add(updateButton);

            updateButton.addActionListener(event -> {
                String newItemName = itemNameField.getText();
                String newItemId = itemIdField.getText();
                String newCategory = categoryComboBox.getSelectedItem().toString();
                String newCondition = conditionComboBox.getSelectedItem().toString();

                if (newItemName.isEmpty() || newItemId.isEmpty()) {
                    JOptionPane.showMessageDialog(editFrame, "Please fill in all fields", "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
                model.setValueAt(newItemName, selectedRow, 0);
                model.setValueAt(newItemId, selectedRow, 1);
                model.setValueAt(newCategory, selectedRow, 2);
                model.setValueAt(newCondition, selectedRow, 3);

                JOptionPane.showMessageDialog(editFrame, "Item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                editFrame.dispose();
            });

            editFrame.setVisible(true);
        });

        deleteItemButton.addActionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to delete", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String itemName = inventoryTable.getValueAt(selectedRow, 0).toString();
            String itemId = inventoryTable.getValueAt(selectedRow, 1).toString();

            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete item: " + itemName + " (ID: " + itemId + ")?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
                model.removeRow(selectedRow);
                itemCountLabel.setText("ITEMS (" + model.getRowCount() + ")");
                JOptionPane.showMessageDialog(this, "Item deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        findRecordsButton.addActionListener(e -> {
            String studentId = studentSearchField.getText();
            if (studentId.equals("Enter Student ID") || studentId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Student ID", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultTableModel tempActiveBorrowsModel = new DefaultTableModel(borrowColumns, 0);
            boolean foundActive = false;

            for (int i = 0; i < activeBorrowsModel.getRowCount(); i++) {
                String currentStudentId = activeBorrowsModel.getValueAt(i, 0).toString();
                if (currentStudentId.equals(studentId)) {
                    Object[] row = new Object[5];
                    for (int j = 0; j < 5; j++) {
                        row[j] = activeBorrowsModel.getValueAt(i, j);
                    }
                    tempActiveBorrowsModel.addRow(row);
                    foundActive = true;
                }
            }

            DefaultTableModel tempHistoryModel = new DefaultTableModel(historyColumns, 0);
            boolean foundHistory = false;

            for (int i = 0; i < borrowHistoryModel.getRowCount(); i++) {
                String currentStudentId = borrowHistoryModel.getValueAt(i, 0).toString();
                if (currentStudentId.equals(studentId)) {
                    Object[] row = new Object[5];
                    for (int j = 0; j < 5; j++) {
                        row[j] = borrowHistoryModel.getValueAt(i, j);
                    }
                    tempHistoryModel.addRow(row);
                    foundHistory = true;
                }
            }

            if (!foundActive && !foundHistory) {
                JOptionPane.showMessageDialog(this, "No records found for Student ID: " + studentId, "No Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                activeBorrowsTable.setModel(tempActiveBorrowsModel);
                borrowHistoryTable.setModel(tempHistoryModel);
                activeBorrowsLabel.setText("Active Borrows for Student: " + studentId);
                borrowHistoryLabel.setText("Borrow History for Student: " + studentId);

                JButton resetRecordsButton = new JButton("Show All Records");
                resetRecordsButton.setBounds(400, 60, 180, 30);
                resetRecordsButton.setBackground(new Color(255, 165, 0));
                resetRecordsButton.setForeground(Color.WHITE);
                borrowRecordsPanel.add(resetRecordsButton);

                resetRecordsButton.addActionListener(event -> {
                    activeBorrowsTable.setModel(activeBorrowsModel);
                    borrowHistoryTable.setModel(borrowHistoryModel);
                    activeBorrowsLabel.setText("Active Borrows");
                    borrowHistoryLabel.setText("Borrow History");
                    borrowRecordsPanel.remove(resetRecordsButton);
                    borrowRecordsPanel.repaint();
                });

                borrowRecordsPanel.repaint();
            }
        });

        updateStatusButton.addActionListener(e -> {
            int selectedRow = maintenanceTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to update", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String itemName = maintenanceTable.getValueAt(selectedRow, 0).toString();
            String currentStatus = maintenanceTable.getValueAt(selectedRow, 3).toString();

            JFrame statusFrame = new JFrame("Update Maintenance Status");
            statusFrame.setSize(350, 200);
            statusFrame.setLayout(null);
            statusFrame.setLocationRelativeTo(null);

            JLabel statusLabel = new JLabel("Status for " + itemName + ":");
            statusLabel.setBounds(30, 30, 250, 25);
            statusFrame.add(statusLabel);

            String[] statuses = {"Pending", "Under Repair", "Repaired", "Replaced", "Unrepairable"};
            JComboBox<String> statusComboBox = new JComboBox<>(statuses);
            statusComboBox.setSelectedItem(currentStatus);
            statusComboBox.setBounds(30, 60, 200, 25);
            statusFrame.add(statusComboBox);

            JButton saveStatusButton = new JButton("Save");
            saveStatusButton.setBounds(100, 100, 100, 30);
            saveStatusButton.setBackground(new Color(46, 139, 87));
            saveStatusButton.setForeground(Color.WHITE);
            statusFrame.add(saveStatusButton);

            saveStatusButton.addActionListener(event -> {
                String newStatus = statusComboBox.getSelectedItem().toString();
                maintenanceModel.setValueAt(newStatus, selectedRow, 3);
                JOptionPane.showMessageDialog(statusFrame, "Status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                statusFrame.dispose();
            });

            statusFrame.setVisible(true);
        });

        reportIssueButton.addActionListener(e -> {
            JFrame reportFrame = new JFrame("Report New Issue");
            reportFrame.setSize(400, 350);
            reportFrame.setLayout(null);
            reportFrame.setLocationRelativeTo(null);

            JLabel selectItemLabel = new JLabel("Select Item:");
            selectItemLabel.setBounds(30, 30, 100, 25);
            reportFrame.add(selectItemLabel);

            DefaultComboBoxModel<String> itemModel = new DefaultComboBoxModel<>();
            for (int i = 0; i < inventoryModel.getRowCount(); i++) {
                String itemName = inventoryModel.getValueAt(i, 0).toString();
                String itemId = inventoryModel.getValueAt(i, 1).toString();
                itemModel.addElement(itemName + " (ID: " + itemId + ")");
            }

            JComboBox<String> itemComboBox = new JComboBox<>(itemModel);
            itemComboBox.setBounds(140, 30, 200, 25);
            reportFrame.add(itemComboBox);

            JLabel issueLabel = new JLabel("Issue Description:");
            issueLabel.setBounds(30, 70, 150, 25);
            reportFrame.add(issueLabel);

            JTextArea issueTextArea = new JTextArea();
            issueTextArea.setLineWrap(true);
            JScrollPane issueScrollPane = new JScrollPane(issueTextArea);
            issueScrollPane.setBounds(30, 100, 310, 100);
            reportFrame.add(issueScrollPane);

            JLabel statusLabel = new JLabel("Initial Status:");
            statusLabel.setBounds(30, 210, 100, 25);
            reportFrame.add(statusLabel);

            String[] statuses = {"Pending", "Under Repair"};
            JComboBox<String> statusComboBox = new JComboBox<>(statuses);
            statusComboBox.setBounds(140, 210, 200, 25);
            reportFrame.add(statusComboBox);

            JButton reportButton = new JButton("Report Issue");
            reportButton.setBounds(120, 260, 150, 30);
            reportButton.setBackground(new Color(70, 130, 180));
            reportButton.setForeground(Color.WHITE);
            reportFrame.add(reportButton);

            reportButton.addActionListener(event -> {
                String selectedItem = itemComboBox.getSelectedItem().toString();
                String issue = issueTextArea.getText();
                String status = statusComboBox.getSelectedItem().toString();

                if (issue.isEmpty()) {
                    JOptionPane.showMessageDialog(reportFrame, "Please enter an issue description", "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String itemName = selectedItem.substring(0, selectedItem.indexOf(" (ID:"));
                String itemId = selectedItem.substring(selectedItem.indexOf("ID: ") + 4, selectedItem.length() - 1);

                LocalDate currentDate = LocalDate.now();
                String reportDate = currentDate.toString();

                maintenanceModel.addRow(new Object[]{itemName, itemId, issue, status, reportDate});

                JOptionPane.showMessageDialog(reportFrame, "Issue reported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                reportFrame.dispose();
            });

            reportFrame.setVisible(true);
        });

        addAdminButton.addActionListener(e -> {
            JFrame addAdminFrame = new JFrame("Add Administrator");
            addAdminFrame.setSize(400, 300);
            addAdminFrame.setLayout(null);
            addAdminFrame.setLocationRelativeTo(null);

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(30, 30, 100, 25);
            addAdminFrame.add(usernameLabel);

            JTextField usernameField = new JTextField();
            usernameField.setBounds(140, 30, 200, 25);
            addAdminFrame.add(usernameField);

            JLabel nameLabel = new JLabel("Admin Name:");
            nameLabel.setBounds(30, 70, 100, 25);
            addAdminFrame.add(nameLabel);

            JTextField nameField = new JTextField();
            nameField.setBounds(140, 70, 200, 25);
            addAdminFrame.add(nameField);

            JLabel idLabel = new JLabel("Admin ID:");
            idLabel.setBounds(30, 110, 100, 25);
            addAdminFrame.add(idLabel);

            JTextField idField = new JTextField();
            idField.setBounds(140, 110, 200, 25);
            addAdminFrame.add(idField);

            JLabel levelLabel = new JLabel("Level:");
            levelLabel.setBounds(30, 150, 100, 25);
            addAdminFrame.add(levelLabel);

            String[] levels = {"Admin", "Student Admin", "Inventory Admin", "Read Only"};
            JComboBox<String> levelComboBox = new JComboBox<>(levels);
            levelComboBox.setBounds(140, 150, 200, 25);
            addAdminFrame.add(levelComboBox);

            JButton saveAdminButton = new JButton("Add Admin");
            saveAdminButton.setBounds(140, 200, 100, 30);
            saveAdminButton.setBackground(new Color(46, 139, 87));
            saveAdminButton.setForeground(Color.WHITE);
            addAdminFrame.add(saveAdminButton);

            saveAdminButton.addActionListener(event -> {
                String username = usernameField.getText();
                String name = nameField.getText();
                String id = idField.getText();
                String level = levelComboBox.getSelectedItem().toString();

                if (username.isEmpty() || name.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(addAdminFrame, "Please fill in all fields", "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                adminModel.addRow(new Object[]{username, name, id, level});
                JOptionPane.showMessageDialog(addAdminFrame, "Administrator added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                addAdminFrame.dispose();
            });

            addAdminFrame.setVisible(true);
        });

        editAdminButton.addActionListener(e -> {
            int selectedRow = adminTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an administrator to edit", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String username = adminTable.getValueAt(selectedRow, 0).toString();
            String name = adminTable.getValueAt(selectedRow, 1).toString();
            String id = adminTable.getValueAt(selectedRow, 2).toString();
            String level = adminTable.getValueAt(selectedRow, 3).toString();

            JFrame editAdminFrame = new JFrame("Edit Administrator");
            editAdminFrame.setSize(400, 300);
            editAdminFrame.setLayout(null);
            editAdminFrame.setLocationRelativeTo(null);

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(30, 30, 100, 25);
            editAdminFrame.add(usernameLabel);

            JTextField usernameField = new JTextField(username);
            usernameField.setBounds(140, 30, 200, 25);
            editAdminFrame.add(usernameField);

            JLabel nameLabel = new JLabel("Admin Name:");
            nameLabel.setBounds(30, 70, 100, 25);
            editAdminFrame.add(nameLabel);

            JTextField nameField = new JTextField(name);
            nameField.setBounds(140, 70, 200, 25);
            editAdminFrame.add(nameField);

            JLabel idLabel = new JLabel("Admin ID:");
            idLabel.setBounds(30, 110, 100, 25);
            editAdminFrame.add(idLabel);

            JTextField idField = new JTextField(id);
            idField.setBounds(140, 110, 200, 25);
            editAdminFrame.add(idField);

            JLabel levelLabel = new JLabel("Level:");
            levelLabel.setBounds(30, 150, 100, 25);
            editAdminFrame.add(levelLabel);

            JComboBox<String> levelComboBox = new JComboBox<>(new String[]{"Admin", "Student Admin", "Inventory Admin", "Read Only"});
            levelComboBox.setSelectedItem(level);
            levelComboBox.setBounds(140, 150, 200, 25);
            editAdminFrame.add(levelComboBox);

            JButton updateAdminButton = new JButton("Update");
            updateAdminButton.setBounds(140, 200, 100, 30);
            updateAdminButton.setBackground(new Color(70, 130, 180));
            updateAdminButton.setForeground(Color.WHITE);
            editAdminFrame.add(updateAdminButton);

            updateAdminButton.addActionListener(event -> {
                String newUsername = usernameField.getText();
                String newName = nameField.getText();
                String newId = idField.getText();
                String newLevel = levelComboBox.getSelectedItem().toString();

                if (newUsername.isEmpty() || newName.isEmpty() || newId.isEmpty()) {
                    JOptionPane.showMessageDialog(editAdminFrame, "Please fill in all fields", "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                adminModel.setValueAt(newUsername, selectedRow, 0);
                adminModel.setValueAt(newName, selectedRow, 1);
                adminModel.setValueAt(newId, selectedRow, 2);
                adminModel.setValueAt(newLevel, selectedRow, 3);

                JOptionPane.showMessageDialog(editAdminFrame, "Administrator updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                editAdminFrame.dispose();
            });

            editAdminFrame.setVisible(true);
        });

        deleteAdminButton.addActionListener(e -> {
            int selectedRow = adminTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an administrator to delete", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String username = adminTable.getValueAt(selectedRow, 0).toString();
            String name = adminTable.getValueAt(selectedRow, 1).toString();

            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete administrator: " + name + " (" + username + ")?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                adminModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Administrator deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addNewItemButton.addActionListener(e -> {
            JFrame newItemFrame = new JFrame("Add New Item");
            newItemFrame.setSize(400, 300);
            newItemFrame.setLayout(null);
            newItemFrame.setLocationRelativeTo(null);

            JLabel itemNameLabel = new JLabel("Item Name:");
            itemNameLabel.setBounds(30, 30, 100, 25);
            newItemFrame.add(itemNameLabel);

            JTextField itemNameField = new JTextField();
            itemNameField.setBounds(140, 30, 200, 25);
            newItemFrame.add(itemNameField);

            JLabel itemIdLabel = new JLabel("Item ID:");
            itemIdLabel.setBounds(30, 70, 100, 25);
            newItemFrame.add(itemIdLabel);

            JTextField itemIdField = new JTextField();
            itemIdField.setBounds(140, 70, 200, 25);
            newItemFrame.add(itemIdField);

            JLabel categoryLabel = new JLabel("Category:");
            categoryLabel.setBounds(30, 110, 100, 25);
            newItemFrame.add(categoryLabel);

            String[] categories = {"Input Device", "Video", "Audio", "Display", "Other"};
            JComboBox<String> categoryComboBox = new JComboBox<>(categories);
            categoryComboBox.setBounds(140, 110, 200, 25);
            newItemFrame.add(categoryComboBox);

            JLabel conditionLabel = new JLabel("Condition:");
            conditionLabel.setBounds(30, 150, 100, 25);
            newItemFrame.add(conditionLabel);

            String[] conditions = {"WORKING", "BROKEN"};
            JComboBox<String> conditionComboBox = new JComboBox<>(conditions);
            conditionComboBox.setBounds(140, 150, 200, 25);
            newItemFrame.add(conditionComboBox);

            JButton saveButton = new JButton("Save");
            saveButton.setBounds(140, 200, 100, 30);
            saveButton.setBackground(new Color(46, 139, 87));
            saveButton.setForeground(Color.WHITE);
            newItemFrame.add(saveButton);

            saveButton.addActionListener(event -> {
                String itemName = itemNameField.getText();
                String itemId = itemIdField.getText();
                String category = categoryComboBox.getSelectedItem().toString();
                String condition = conditionComboBox.getSelectedItem().toString();

                if (itemName.isEmpty() || itemId.isEmpty()) {
                    JOptionPane.showMessageDialog(newItemFrame, "Please fill in all fields", "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                inventoryModel.addRow(new Object[]{itemName, itemId, category, condition});
                itemCountLabel.setText("ITEMS (" + inventoryModel.getRowCount() + ")");
                JOptionPane.showMessageDialog(newItemFrame, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                newItemFrame.dispose();
            });

            newItemFrame.setVisible(true);
        });

        // Navigation button actions
        viewButton.addActionListener(e -> {
            viewInventoryPanel.setVisible(true);
            borrowRecordsPanel.setVisible(false);
            maintenancePanel.setVisible(false);
            manageAdminsPanel.setVisible(false);
        });

        borrowButton.addActionListener(e -> {
            viewInventoryPanel.setVisible(false);
            borrowRecordsPanel.setVisible(true);
            maintenancePanel.setVisible(false);
            manageAdminsPanel.setVisible(false);
        });

        maintenanceButton.addActionListener(e -> {
            viewInventoryPanel.setVisible(false);
            borrowRecordsPanel.setVisible(false);
            maintenancePanel.setVisible(true);
            manageAdminsPanel.setVisible(false);
        });

        manageButton.addActionListener(e -> {
            viewInventoryPanel.setVisible(false);
            borrowRecordsPanel.setVisible(false);
            maintenancePanel.setVisible(false);
            manageAdminsPanel.setVisible(true);
        });

        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        add(panel);
        add(nav);
        add(viewInventoryPanel);
        add(borrowRecordsPanel);
        add(maintenancePanel);
        add(manageAdminsPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	adminDashboard dashboard = new adminDashboard();
            dashboard.setVisible(true);
        });
    }
}