package Model;

import java.util.Date;
import java.util.List;

public class FoodExpenses extends Expense {
    private String restaurant;

    public FoodExpenses(String name, double amount, Date date, String restaurant) {
        super(name, amount, date);
        this.restaurant = restaurant;
    }

    public String getRestaurant() {
        return restaurant;
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
        return String.format("Food: %s at %s", getName(), restaurant);
    }
}