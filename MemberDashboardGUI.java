package LibraryManagementSystem.src.gui;

import LibraryManagementSystem.src.model.*;
import javax.swing.*;
import java.awt.*;

public class MemberDashboardGUI extends JFrame {

    public MemberDashboardGUI(Member member) {
        setTitle("Member Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Welcome label
        JLabel label = new JLabel("Welcome Member: " + member.getUsername(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        // Optional: You can add more components like buttons for viewing books, borrowing, etc.
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
