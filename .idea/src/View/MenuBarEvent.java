package View;

public class MenuBarEvent {
    private String actionCommand;

    public MenuBarEvent(String actionCommand) {
        this.actionCommand = actionCommand;
    }

    public String getActionCommand() {
        return actionCommand;
    }
}