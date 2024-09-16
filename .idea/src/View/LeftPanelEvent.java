package View;

import java.util.Date;
import java.util.EventObject;
import java.util.List;

public class LeftPanelEvent extends EventObject {

    private String name;
    private String category;
    private String paidBy;
    private Date date;
    private List<String> users;

    public LeftPanelEvent(Object source, String name, String category, String paidBy, Date date, List<String> users) {
        super(source);
        this.name = name;
        this.category = category;
        this.paidBy = paidBy;
        this.date = date;
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

    public List<String> getUsers() {
        return users;
    }
}
