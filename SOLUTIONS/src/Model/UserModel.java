package Model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private List<User> users;

    public UserModel() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }
}