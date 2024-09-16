package Controller;

import View.MenuBarEvent;
import View.MenuBarListener;

public class MenuController implements MenuBarListener {

    @Override
    public void menuBarEventOccurred(MenuBarEvent event) {
        String command = event.getActionCommand();
        switch (command) {
            case "Export Expenses":
                handleExportExpenses();
                break;
            case "Export Balance":
                handleExportBalance();
                break;
            case "Exit":
                handleExit();
                break;
        }
    }

    private void handleExportExpenses() {
        // Logic to export expenses
        System.out.println("Exporting expenses...");
    }

    private void handleExportBalance() {
        // Logic to export balance
        System.out.println("Exporting balance...");
    }

    private void handleExit() {
        // Logic to exit the application
        System.out.println("Exiting application...");
        System.exit(0);
    }
}