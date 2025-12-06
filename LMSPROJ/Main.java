package LibraryManagementSystem;

import LibraryManagementSystem.gui.HomeGUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
         SwingUtilities.invokeLater(HomeGUI::new);
    }
}
