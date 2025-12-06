package LibraryManagementSystem.gui;

import javax.swing.*;
import java.awt.*;

public class HomeGUI extends JFrame {

    public HomeGUI() {
        setTitle("Library Management System");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Colors
        Color bgColor = Color.decode("#9bc1bc");      // light teal
        Color textColor = Color.decode("#e6ebe0");    // cream/off-white

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgColor);
        add(mainPanel);

        // ------------------- Top Panel (Login Buttons) -------------------
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        topPanel.setBackground(bgColor);

        JButton adminBtn = new JButton("Admin Login");
        JButton memberBtn = new JButton("Member Login");

        adminBtn.addActionListener(e -> {
            new LoginGUI("ADMIN");
            dispose();
        });

        memberBtn.addActionListener(e -> {
            new LoginGUI("MEMBER");
            dispose();
        });

        topPanel.add(adminBtn);
        topPanel.add(memberBtn);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // ------------------- Center Panel (Welcome Message) -------------------
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(bgColor);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to the Library Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(textColor);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel = new JLabel("<html><div style='text-align:center;'>"
                + "Explore books, issue or return them, and track your reading history.<br>"
                + "Please login to continue.<br/>"+"~ Library at your fingertips!"
                + "</div></html>", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setForeground(textColor);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(welcomeLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(infoLabel);
        centerPanel.add(Box.createVerticalGlue());

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

}
