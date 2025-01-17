package View;

import Controller.Controller;
import Model.ExpencesModel;
import Model.UserModel;

import javax.swing.*;

public class MainFrame extends JFrame {
    private ViewPanel viewPanel;
    private MenuBar menuBar;
    private Controller controller;

    public MainFrame() {
        UserModel userModel = new UserModel();
        ExpencesModel expencesModel = new ExpencesModel();
        viewPanel = new ViewPanel();
        controller = new Controller(userModel, expencesModel, viewPanel.getLeftPanel(), viewPanel.getRightPanel());

        setTitle("Expense Tracker");
        setSize(800, 600);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();
    }

    private void createComponents() {
        add(viewPanel);
        menuBar = new MenuBar();
        setJMenuBar(menuBar);
        menuBar.setMenuBarListener(event -> {
            if (event.getActionCommand().equals("Exit")) {
                System.exit(0);
            }
        });
        viewPanel.setController(controller);
        menuBar.setController(controller);
    }
}