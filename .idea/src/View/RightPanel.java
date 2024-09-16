package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel implements RightPanelListener {
    private JButton expensesButton;
    private JButton balanceButton;
    private JLabel owningLabel;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    GridBagConstraints gbc = new GridBagConstraints();

    public RightPanel() {
        // Set panel background color to match LeftPanel (light gray)
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);

        // Button for Expenses
        expensesButton = new JButton("Expenses");
        expensesButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        expensesButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(expensesButton, gbc);

        // Button for Balance
        balanceButton = new JButton("Balance");
        balanceButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        balanceButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 1;
        add(balanceButton, gbc);

        // Owning Label
        owningLabel = new JLabel("Owning:", SwingConstants.CENTER);
        owningLabel.setOpaque(true);
        owningLabel.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(owningLabel, gbc);

        // Table model to store user names and balances
        String[] columnNames = {"User", "Balance"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        userTable.setFont(new Font("Verdana", Font.PLAIN, 12));
        userTable.setRowHeight(30);

        // Center align table cells and set table colors
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        userTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        userTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.getTableHeader().setResizingAllowed(false);

        scrollPane = new JScrollPane(userTable);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        gbc.gridy++;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Test data (you can remove this part and add real data later)
        addUserBalance("User1", 50.0);
        addUserBalance("User2", -20.0);

        // Add ActionListener to expensesButton
        expensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExpensesTable();
            }
        });

        // Add ActionListener to balanceButton
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBalanceTable();
            }
        });

        // Trigger the event for demonstration
        triggerRightPanelEvent();
    }

    // Method to add a user and their balance to the table
    public void addUserBalance(String user, double balance) {
        String balanceText = String.format("%.2f", balance);
        tableModel.addRow(new Object[]{user, balanceText});
    }

    // Method to display the expenses table
    private void showExpensesTable() {
        String[] columnNames = {"Expense Name", "Amount", "Date"};
        DefaultTableModel expensesTableModel = new DefaultTableModel(columnNames, 0);
        JTable expensesTable = new JTable(expensesTableModel);

        // Add test data (replace with real data)
        expensesTableModel.addRow(new Object[]{"Expense1", "100.00", "01/01/2023"});
        expensesTableModel.addRow(new Object[]{"Expense2", "50.00", "02/01/2023"});

        scrollPane.setViewportView(expensesTable);
    }

    // Method to display the balance table
    private void showBalanceTable() {
        scrollPane.setViewportView(userTable);
    }

    @Override
    public void rightPanelEventOccurred(RightPanelEvent event) {
        String user = event.getUser();
        double balance = event.getBalance();

        // Privremeni ispis podataka u konzolu
        System.out.println("User: " + user);
        System.out.println("Balance: " + balance);
    }

    // Method to trigger the event
    private void triggerRightPanelEvent() {
        RightPanelEvent event = new RightPanelEvent(this, "User1", 50.0);
        rightPanelEventOccurred(event);
    }
}