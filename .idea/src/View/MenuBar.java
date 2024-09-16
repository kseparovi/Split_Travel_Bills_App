package View;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private MenuBarListener menuBarListener;
    private JMenu fileMenu = new JMenu("Menu");
    private JMenuItem exportDataItem = new JMenuItem("Export Expenses");
    private JMenuItem importDataItem = new JMenuItem("Export Balance");
    private JMenuItem exitItem = new JMenuItem("Exit");


    public MenuBar() {

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        add(fileMenu);

        exportDataItem.addActionListener(e -> {
            if (menuBarListener != null) {
                menuBarListener.menuBarEventOccurred(new MenuBarEvent("Export Expenses"));
            }
        });

        importDataItem.addActionListener(e -> {
            if (menuBarListener != null) {
                menuBarListener.menuBarEventOccurred(new MenuBarEvent("Export Balance"));
            }
        });

        exitItem.addActionListener(e -> {
            if (menuBarListener != null) {
                menuBarListener.menuBarEventOccurred(new MenuBarEvent("Exit"));
            }
        });
    }

    public void setMenuBarListener(MenuBarListener menuBarListener) {
        this.menuBarListener = menuBarListener;
    }
}
