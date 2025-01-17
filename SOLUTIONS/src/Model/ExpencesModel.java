package Model;

import java.util.ArrayList;
import java.util.List;

public class ExpencesModel implements ExpencesObservable {
    private List<Expense> expenses;
    private List<ExpencesObserver> observers;

    public ExpencesModel() {
        this.expenses = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        notifyObservers();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double calculateTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    @Override
    public void addObserver(ExpencesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ExpencesObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ExpencesObserver observer : observers) {
            observer.updateExpenses();
        }
    }
}