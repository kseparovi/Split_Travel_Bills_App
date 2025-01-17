package Model;

import java.util.Date;
import java.util.List;

public class AccommodationExpenses extends Expense {
    private String location;
    private int nights;

    public AccommodationExpenses(String name, double amount, Date date, String location, int nights) {
        super(name, amount, date);
        this.location = location;
        this.nights = nights;
    }

    public String getLocation() {
        return location;
    }

    public int getNights() {
        return nights;
    }

    @Override
    public String getPaidBy() {
        return "";
    }

    @Override
    public List<String> getUsers() {
        return List.of();
    }

    @Override
    public String toString() {
        return String.format("Accommodation: %s at %s for %d nights", getName(), location, nights);
    }
}