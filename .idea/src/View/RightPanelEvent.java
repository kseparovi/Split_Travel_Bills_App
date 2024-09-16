package View;

import java.util.EventObject;

public class RightPanelEvent extends EventObject {
    private String user;
    private double balance;

    public RightPanelEvent(Object source, String user, double balance) {
        super(source);
        this.user = user;
        this.balance = balance;
    }

    public String getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }
}