package LibraryManagementSystem.src.gui;


import LibraryManagementSystem.src.model.*;
import LibraryManagementSystem.src.system.*;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    public LoginGUI(String userType) {
        setTitle(userType + " Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            User user = LibrarySystem.login(username, password, userType);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                if (userType.equals("ADMIN")) {
                    new AdminDashboardGUI((LibraryManagementSystem.src.model.Admin) user);
                } else {
                    new MemberDashboardGUI((LibraryManagementSystem.src.model.Member) user);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel());
        panel.add(loginBtn);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}

