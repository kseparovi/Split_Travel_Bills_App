package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Controller {
    private UserModel userModel;
    private ExpencesModel expencesModel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public Controller(UserModel userModel, ExpencesModel expencesModel, LeftPanel leftPanel, RightPanel rightPanel) {
        this.userModel = userModel;
        this.expencesModel = expencesModel;
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;

        this.rightPanel.setController(this);
        this.expencesModel.addObserver(this.rightPanel);
    }

    public void processLeftPanelEvent(LeftPanelEvent event) {
        String name = event.getName();
        String category = event.getCategory();
        String paidBy = event.getPaidBy();
        Date date = event.getDate();
        double amount = event.getAmount();
        List<String> users = event.getUsers();

        Expense expense = createExpense(name, category, amount, date);
        expencesModel.addExpense(expense);
        rightPanel.addExpense(name, category, paidBy, date, amount, users);

        // Save to database
        saveExpenseToDatabase(expense, paidBy);
    }

    private Expense createExpense(String name, String category, double amount, Date date) {
        switch (category) {
            case "Karte":
                return new TicketExpenses(name, amount, date, "Bus");
            case "Smjestaj":
                return new AccommodationExpenses(name, amount, date, "Hotel", 2);
            case "Hrana":
                return new FoodExpenses(name, amount, date, "Restaurant");
            case "Pice":
                return new DrinkExpenses(name, amount, date, "Beer");
            default:
                return new Expense(name, amount, date) {
                    @Override
                    public String toString() {
                        return String.format("Other: %s", getName());
                    }
                };
        }
    }

    private void saveExpenseToDatabase(Expense expense, String paidBy) {
        String sql = "INSERT INTO expenses (name, category, paid_by, date, amount) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, expense.getName());
            pstmt.setString(2, expense.getCategory()); // Ensure this method exists
            pstmt.setString(3, paidBy);
            pstmt.setDate(4, new java.sql.Date(expense.getDate().getTime()));
            pstmt.setDouble(5, expense.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle error
        }
    }

    public void handleExit() {
        exitApplication();
    }

    public void handleExport() {
        File dataDir = new File("DATA");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        File file = new File(dataDir, "expenses_export.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Expense expense : expencesModel.getExpenses()) {
                writer.write(expenseToString(expense));
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Data exported successfully.", "Export", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); // Handle error
            JOptionPane.showMessageDialog(null, "Error exporting data: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String expenseToString(Expense expense) {
        return String.format("%s,%s,%s,%s,%.2f",
                expense.getName(),
                expense.getCategory(),
                expense.getPaidBy() == null ? "null" : expense.getPaidBy(),
                new java.sql.Date(expense.getDate().getTime()),
                expense.getAmount());
    }
    public void handleImport() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        String name = data[0];
                        String category = data[1];
                        String paidBy = data[2];
                        Date date = java.sql.Date.valueOf(data[3]);
                        double amount = Double.parseDouble(data[4].replace(",", "."));

                        Expense expense = createExpense(name, category, amount, date);
                        expencesModel.addExpense(expense);
                        rightPanel.addExpense(name, category, paidBy, date, amount, List.of(paidBy)); // Adjust user list as needed
                        saveExpenseToDatabase(expense, paidBy); // Save to database as well
                    }
                }
                JOptionPane.showMessageDialog(null, "Data imported successfully.", "Import", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace(); // Handle error
                JOptionPane.showMessageDialog(null, "Error importing data: " + e.getMessage(), "Import Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void exitApplication() {
        System.exit(0);
    }

    public String getExportData() {
        StringBuilder data = new StringBuilder();
        for (Expense expense : expencesModel.getExpenses()) {
            data.append(expenseToString(expense));
            data.append(System.lineSeparator());
        }
        return data.toString();
    }

    public void importData(String string) {
        String[] lines = string.split(System.lineSeparator());
        for (String line : lines) {
            String[] data = line.split(",");
            if (data.length == 5) {
                String name = data[0];
                String category = data[1];
                String paidBy = data[2];
                Date date = java.sql.Date.valueOf(data[3]);
                double amount = Double.parseDouble(data[4].replace(",", "."));

                Expense expense = createExpense(name, category, amount, date);
                expencesModel.addExpense(expense);
                rightPanel.addExpense(name, category, paidBy, date, amount, List.of(paidBy)); // Adjust user list as needed
                saveExpenseToDatabase(expense, paidBy); // Save to database as well
            }
        }
    }
}