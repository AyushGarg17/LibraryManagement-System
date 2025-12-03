package LibraryManagementSystem.src;

import LibraryManagementSystem.src.gui.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginChoiceGUI();
        });
    }
}
