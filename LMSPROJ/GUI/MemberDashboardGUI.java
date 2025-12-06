package LibraryManagementSystem.gui;

import LibraryManagementSystem.model.Book;
import LibraryManagementSystem.model.IssuedRecord;
import LibraryManagementSystem.model.Member;
import LibraryManagementSystem.system.LibrarySystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MemberDashboardGUI extends JFrame {

    private DefaultTableModel tableModel;
    private JTable issuedTable;
    private JTextField searchField;

    public MemberDashboardGUI(Member member) {
        setTitle("Member Dashboard - " + member.getUsername());
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Colors
        Color bgColor = new Color(178, 172, 136);
        Color btnColor = new Color(115, 158, 96);
        Color btnTextColor = Color.WHITE;

        // ------------------- Top Panel -------------------
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        topPanel.setBackground(bgColor);

        JButton issueBtn = new JButton("Issue Book");
        JButton returnBtn = new JButton("Return Book");
        JButton refreshBtn = new JButton("Refresh Table");
        JButton logoutBtn = new JButton("Logout");

        // Buttons colors
        for (JButton btn : new JButton[]{issueBtn, returnBtn, refreshBtn, logoutBtn}) {
            btn.setBackground(btnColor);
            btn.setForeground(btnTextColor);
        }

        topPanel.add(issueBtn);
        topPanel.add(returnBtn);
        topPanel.add(refreshBtn);
        topPanel.add(logoutBtn);

        // ------------------- Search Field -------------------
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(bgColor);
        JLabel searchLabel = new JLabel("Search a Book: ");
        searchField = new JTextField(20);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        // ------------------- Book Suggestion List -------------------
        DefaultListModel<Book> listModel = new DefaultListModel<>();
        JList<Book> suggestionList = new JList<>(listModel);
        JScrollPane suggestionScroll = new JScrollPane(suggestionList);
        suggestionScroll.setPreferredSize(new Dimension(300, 150));

        // ------------------- Left Panel -------------------
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(bgColor);
        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(suggestionScroll, BorderLayout.CENTER);
        leftPanel.add(topPanel, BorderLayout.SOUTH);

        // ------------------- Issued Books Table -------------------
        String[] columns = {"Book", "Date", "Time", "Return By", "Status/Fine"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        issuedTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(issuedTable);

        // ------------------- Layout -------------------
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(tableScroll, BorderLayout.CENTER);

        // ------------------- Auto-suggest / Live Search -------------------
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateList() {
                listModel.clear();
                String keyword = searchField.getText().trim();
                if (!keyword.isEmpty()) {
                    List<Book> results = LibrarySystem.searchBooks(keyword);
                    for (Book b : results) listModel.addElement(b);
                }
            }

            @Override public void insertUpdate(DocumentEvent e) { updateList(); }
            @Override public void removeUpdate(DocumentEvent e) { updateList(); }
            @Override public void changedUpdate(DocumentEvent e) { updateList(); }
        });

        // ------------------- Issue Book -------------------
        issueBtn.addActionListener(e -> {
            Book selected = suggestionList.getSelectedValue();
            if (selected != null) {
                LibrarySystem.issueBook(selected.getId(), member);
                JOptionPane.showMessageDialog(this,
                        "Book Issued Successfully!\nBook: " + selected.getTitle() +
                                "\nDate: " + LocalDate.now() +
                                "\nTime: " + LocalTime.now().withNano(0));
                refreshTable(member);
            }
        });

        // ------------------- Return Book -------------------
        returnBtn.addActionListener(e -> {
            Book selected = suggestionList.getSelectedValue();
            if (selected != null) {
                LibrarySystem.returnBook(selected.getId(), member);
                JOptionPane.showMessageDialog(this,
                        "Book Returned Successfully!\nBook: " + selected.getTitle() +
                                "\nDate: " + LocalDate.now() +
                                "\nTime: " + LocalTime.now().withNano(0));
                refreshTable(member);
            }
        });

        // ------------------- Refresh Table -------------------
        refreshBtn.addActionListener(e -> refreshTable(member));
        refreshTable(member); // initial load

        // ------------------- Logout -------------------
        logoutBtn.addActionListener(e -> {
            dispose();
            new HomeGUI();
        });

        setVisible(true);
    }

    // ------------------- Helper Method: refresh issued books table -------------------
    private void refreshTable(Member member) {
        tableModel.setRowCount(0); // clear previous data
        for (IssuedRecord r : LibrarySystem.records) {
            if (r.getUsername().equals(member.getUsername())) {
                String bookName = LibrarySystem.books.stream()
                        .filter(b -> b.getId().equals(r.getBookId()))
                        .findFirst().map(Book::getTitle).orElse(r.getBookId());
                LocalDate issueDate = r.getIssueDate();
                String time = LocalTime.now().withNano(0).toString();
                LocalDate returnBy = r.getIssueDate().plusDays(7);
                String status;
                if (!r.isReturned() && LocalDate.now().isAfter(returnBy)) {
                    long daysLate = java.time.temporal.ChronoUnit.DAYS.between(returnBy, LocalDate.now());
                    status = "Overdue! Fine: $" + (daysLate * 5);
                } else if (r.isReturned()) {
                    status = "Returned. Fine: $" + r.getFine();
                } else {
                    status = "Issued";
                }
                tableModel.addRow(new Object[]{bookName, issueDate, time, returnBy, status});
            }
        }
    }
}
