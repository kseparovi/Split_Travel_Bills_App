package Model;

import java.util.Date;

public class TicketExpenses extends Expense {
    private String transportType;

    public TicketExpenses(String name, double amount, Date date, String transportType) {
        super(name, amount, date);
        this.transportType = transportType;
    }

    @Override
    public String getCategory() {
        return "Karte";
    }

    @Override
    public String toString() {
        return String.format("Ticket: %s, %s", getName(), transportType);
    }
}