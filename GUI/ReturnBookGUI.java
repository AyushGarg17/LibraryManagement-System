package LibraryManagementSystem.src.gui;

import LibraryManagementSystem.src.system.LibraryService;

import javax.swing.*;
import java.awt.*;

public class ReturnBookGUI extends JFrame {

    private JTextField loanIdField;

    public ReturnBookGUI() {
        setTitle("Return Book");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel loanIdLabel = new JLabel("Loan ID:");
        loanIdField = new JTextField();

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> returnBook());

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(loanIdLabel);
        panel.add(loanIdField);
        panel.add(new JLabel());
        panel.add(returnButton);

        add(panel);
    }

    private void returnBook() {
        String loanId = loanIdField.getText().trim();
        if (loanId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Loan ID");
            return;
        }

        int fine = LibraryService.getInstance().returnBook(loanId);
        if (fine == -1) {
            JOptionPane.showMessageDialog(this, "Invalid Loan ID or already returned");
        } else if (fine == 0) {
            JOptionPane.showMessageDialog(this, "Book returned. No fine.");
        } else {
            JOptionPane.showMessageDialog(this, "Book returned.\nFine: ₹" + fine);
        }
        dispose();
    }
}
