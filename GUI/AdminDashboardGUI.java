package LibraryManagementSystem.src.gui;


import LibraryManagementSystem.src.model.*;
import javax.swing.*;
import java.awt.*;

public class AdminDashboardGUI extends JFrame {

    public AdminDashboardGUI(Admin admin) {
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Welcome label
        JLabel label = new JLabel("Welcome Admin: " + admin.getUsername(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        // Optional: You can add more components like buttons to add/edit/delete books
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
