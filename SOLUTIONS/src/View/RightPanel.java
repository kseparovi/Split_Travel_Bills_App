package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

import Controller.Controller;
import Model.ExpencesObserver;

public class RightPanel extends JPanel implements ExpencesObserver, RightPanelListener {
    private JTable expenseTable;
    private DefaultTableModel tableModel;
    private JTextArea totalDebtsArea;
    private Map<String, Double> totalDebts;
    private Controller controller;

    public RightPanel() {
        setLayout(new BorderLayout());
        totalDebts = new HashMap<>();
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        tableModel = new DefaultTableModel(new Object[]{"Name", "Category", "Paid By", "Date", "Amount", "Split Between", "Debts"}, 0);
        expenseTable = new JTable(tableModel);
        expenseTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        totalDebtsArea = new JTextArea();
        totalDebtsArea.setEditable(false);
        totalDebtsArea.setPreferredSize(new Dimension(500, 100));
    }

    private void layoutComponents() {
        add(new JScrollPane(expenseTable), BorderLayout.CENTER);
        add(new JScrollPane(totalDebtsArea), BorderLayout.SOUTH);
    }

    public void addExpense(String name, String category, String paidBy, Date date, double amount, List<String> users) {
        String splitUsers = String.join(", ", users);
        String debts = calculateDebts(paidBy, amount, users);
        tableModel.addRow(new Object[]{name, category, paidBy, date, amount, splitUsers, debts});
        updateTotalDebts(paidBy, amount, users);
        updateTotalDebtsDisplay();
    }

    private String calculateDebts(String paidBy, double amount, List<String> users) {
        int numUsers = users.size();
        double sharePerUser = amount / numUsers;
        StringBuilder debtsInfo = new StringBuilder();
        for (String user : users) {
            if (!user.equals(paidBy)) {
                debtsInfo.append(user).append(" owes ").append(paidBy).append(" ").append(String.format("%.2f", sharePerUser)).append(" units; ");
            }
        }
        return debtsInfo.toString().trim();
    }

    private void updateTotalDebts(String paidBy, double amount, List<String> users) {
        int numUsers = users.size();
        double sharePerUser = amount / numUsers;
        for (String user : users) {
            if (!user.equals(paidBy)) {
                totalDebts.put(user, totalDebts.getOrDefault(user, 0.0) - sharePerUser);
                totalDebts.put(paidBy, totalDebts.getOrDefault(paidBy, 0.0) + sharePerUser);
            }
        }
    }

    public void updateTotalDebtsDisplay() {
        StringBuilder debtsDisplay = new StringBuilder("Total Debts:\n");
        for (Map.Entry<String, Double> entry : totalDebts.entrySet()) {
            debtsDisplay.append(entry.getKey())
                    .append(" balance: ")
                    .append(String.format("%.2f", entry.getValue()))
                    .append("\n");
        }
        totalDebtsArea.setText(debtsDisplay.toString().trim());
    }

    @Override
    public void updateExpenses() {
        updateTotalDebtsDisplay();
    }

    @Override
    public void rightPanelEventOccurred(RightPanelEvent event) {
        // Handle the event (if needed)
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void refreshTable() {
        tableModel.fireTableDataChanged();
    }
}
