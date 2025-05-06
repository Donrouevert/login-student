import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class adminDashboard extends JFrame {

    public adminDashboard() {
        super("Admin Dashboard");
        setLayout(null);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Header panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1200, 90);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);

        JLabel title = new JLabel("Admin Panel");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(20, 20, 300, 40);
        panel.add(title);

        // Main panels
        JPanel main = new JPanel();
        main.setBounds(280, 90, 920, 200);
        main.setBackground(Color.decode("#afafaf"));
        main.setLayout(null);
        main.setVisible(false);

        JPanel View = new JPanel();
        View.setBounds(280, 90, 920, 670);
        View.setBackground(Color.decode("#fefefe"));
        View.setVisible(false);
        View.setLayout(null);

        int itemCount = 5;
        JLabel vitems = new JLabel("ITEMS (" + itemCount + ")");
        vitems.setBounds(50, 20, 200, 30);
        vitems.setFont(new Font("Arial", Font.BOLD, 24));
        View.add(vitems);

        JTextField vinputField = new JTextField();
        vinputField.setBounds(50, 60, 200, 30);
        View.add(vinputField);

        JButton vani = new JButton("Add New Item");
        vani.setBounds(270, 60, 150, 30);
        View.add(vani);

        JScrollPane[] tableScroll = new JScrollPane[1]; // for reference clearing

        vinputField.addActionListener(e -> {
            if (tableScroll[0] != null) {
                View.remove(tableScroll[0]);
            }

            DefaultTableModel model = new DefaultTableModel(new Object[]{"Item Name", "Item ID", "Category", "Condition"}, 0);
            for (int i = 0; i < 5; i++) {
                model.addRow(new Object[]{"Item " + i, i, "Category " + i, i % 2 == 0 ? "WORKING" : "BROKEN"});
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 110, 800, 200);
            View.add(scrollPane);
            tableScroll[0] = scrollPane;
            View.revalidate();
            View.repaint();
        });

        vani.addActionListener(e -> {
            JFrame newFrame = new JFrame("Add New Item");
            newFrame.setSize(300, 200);
            newFrame.setLayout(new FlowLayout());

            JTextField itemField = new JTextField(15);
            JButton ssave = new JButton("SAVE");
            ssave.addActionListener(ex -> {
                JOptionPane.showMessageDialog(null, "Item added (simulated)");
                newFrame.dispose();
            });

            newFrame.add(new JLabel("Item Name:"));
            newFrame.add(itemField);
            newFrame.add(ssave);
            newFrame.setVisible(true);
        });

        // Other panels
        JPanel borrow = new JPanel();
        borrow.setBounds(280, 90, 920, 670);
        borrow.setBackground(Color.decode("#ffffff"));
        borrow.setVisible(false);

        JPanel Maintenance = new JPanel();
        Maintenance.setBounds(280, 90, 920, 670);
        Maintenance.setBackground(Color.decode("#fefefe"));
        Maintenance.setVisible(false);

        JPanel Manage = new JPanel();
        Manage.setBounds(280, 90, 920, 670);
        Manage.setBackground(Color.decode("#fefefe"));
        Manage.setVisible(false);
        Manage.setLayout(new BorderLayout());

        DefaultTableModel adminModel = new DefaultTableModel(new Object[]{"Username", "Admin Name", "ID", "Level"}, 0);
        adminModel.addRow(new Object[]{"admin1", "John Doe", 1, "Admin"});
        adminModel.addRow(new Object[]{"admin2", "Jane Smith", 2, "Student Admin"});
        JTable adminTable = new JTable(adminModel);
        Manage.add(new JScrollPane(adminTable), BorderLayout.CENTER);

        // Navigation panel
        JPanel nav = new JPanel();
        nav.setLayout(null);
        nav.setBounds(0, 90, 280, 670);
        nav.setBackground(Color.decode("#e3e3e3"));

        JButton viewButton = new JButton("View Inventory");
        viewButton.setBounds(50, 30, 180, 30);
        nav.add(viewButton);

        JButton borrowButton = new JButton("Borrow Records");
        borrowButton.setBounds(50, 70, 180, 30);
        nav.add(borrowButton);

        JButton maintenanceButton = new JButton("Maintenance");
        maintenanceButton.setBounds(50, 110, 180, 30);
        nav.add(maintenanceButton);

        JButton manageButton = new JButton("Manage Admins");
        manageButton.setBounds(50, 150, 180, 30);
        nav.add(manageButton);

        // Button actions
        viewButton.addActionListener(e -> {
            View.setVisible(true);
            borrow.setVisible(false);
            Maintenance.setVisible(false);
            Manage.setVisible(false);
        });

        borrowButton.addActionListener(e -> {
            View.setVisible(false);
            borrow.setVisible(true);
            Maintenance.setVisible(false);
            Manage.setVisible(false);
        });

        maintenanceButton.addActionListener(e -> {
            View.setVisible(false);
            borrow.setVisible(false);
            Maintenance.setVisible(true);
            Manage.setVisible(false);
        });

        manageButton.addActionListener(e -> {
            View.setVisible(false);
            borrow.setVisible(false);
            Maintenance.setVisible(false);
            Manage.setVisible(true);
        });

        // Add everything to the frame
        add(panel);
        add(nav);
        add(View);
        add(borrow);
        add(Maintenance);
        add(Manage);
    }

    // Optional: test this class by running it directly
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            adminDashboard dashboard = new adminDashboard();
            dashboard.setVisible(true);
        });
    }
}
