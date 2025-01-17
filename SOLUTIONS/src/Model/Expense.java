package Model;

import java.util.Date;

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
        return "Other";
    }

    public String getPaidBy() {
        return null;
    }

    @Override
    public abstract String toString();
}