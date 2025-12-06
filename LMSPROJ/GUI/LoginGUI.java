package LibraryManagementSystem.gui;

import LibraryManagementSystem.model.*;
import LibraryManagementSystem.system.LibrarySystem;
import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    public LoginGUI(String type){
        setTitle(type + " Login");
        setSize(400,250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.setBackground(new Color(235, 207, 178));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        panel.add(new JLabel("Username")); panel.add(userField);
        panel.add(new JLabel("Password")); panel.add(passField);
        panel.add(new JLabel()); panel.add(loginBtn);

        loginBtn.addActionListener(e -> {
            User u = LibrarySystem.login(userField.getText(), new String(passField.getPassword()), type);

            if(u instanceof Admin){
                new AdminDashboardGUI();
                dispose();
            } else if(u instanceof Member){
                new MemberDashboardGUI((Member) u);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });

        add(panel);
        setVisible(true);
    }
}
