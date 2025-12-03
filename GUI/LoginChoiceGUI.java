package LibraryManagementSystem.src.gui;

import javax.swing.*;
import java.awt.*;

public class LoginChoiceGUI extends JFrame {

    public LoginChoiceGUI() {
        setTitle("Library Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Select Login Type", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

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

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(label);
        panel.add(adminBtn);
        panel.add(memberBtn);

        add(panel);
        setVisible(true);
    }
}

