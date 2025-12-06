package LibraryManagementSystem.gui;

import javax.swing.*;
import java.awt.*;

public class LoginChoiceGUI extends JFrame {
    public LoginChoiceGUI() {
        setTitle("Library App");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(155, 193, 188));
        panel.setLayout(new GridLayout(3,1,15,15));

        JLabel title = new JLabel("Library Management", JLabel.CENTER);
        title.setForeground(Color.WHITE);

        JButton adminBtn = new JButton("Admin Login");
        JButton memberBtn = new JButton("Member Login");

        adminBtn.addActionListener(e -> { new LoginGUI("ADMIN"); dispose(); });
        memberBtn.addActionListener(e -> { new LoginGUI("MEMBER"); dispose(); });

        panel.add(title);
        panel.add(adminBtn);
        panel.add(memberBtn);

        add(panel);
        setVisible(true);
    }
}
