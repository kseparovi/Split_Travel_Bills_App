package Model;

import java.util.Date;

public class DrinkExpenses extends Expense {
    private String type;

    public DrinkExpenses(String name, double amount, Date date, String type) {
        super(name, amount, date);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Drink: %s of type %s", getName(), type);
    }
}