package View;

import java.util.Date;
import java.util.List;
import java.util.EventObject;

public class LeftPanelEvent extends EventObject {

    private String name;
    private String category;
    private String paidBy;
    private Date date;
    private double amount; // New field for amount
    private List<String> users;

    public LeftPanelEvent(Object source, String name, String category, String paidBy, Date date, double amount, List<String> users) {
        super(source);
        this.name = name;
        this.category = category;
        this.paidBy = paidBy;
        this.date = date;
        this.amount = amount;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() { // Getter for amount
        return amount;
    }

    public List<String> getUsers() {
        return users;
    }
}

