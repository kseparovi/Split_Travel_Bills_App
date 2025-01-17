package Model;

import java.util.Date;
import java.util.List;

public abstract class Expense {
    private String name;
    private double amount;
    private Date date;

    public Expense(String name, double amount, Date date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return "Other"; // Default category, subclasses can override
    }

    public abstract String getPaidBy(); // Force subclasses to implement

    public abstract List<String> getUsers(); // Force subclasses to implement

    @Override
    public abstract String toString();
}
