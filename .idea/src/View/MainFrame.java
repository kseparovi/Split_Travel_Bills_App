package View;

import javax.swing.*;

public class MainFrame extends JFrame {

    private ViewPanel viewPanel;
    private MenuBar menuBar;

    public MainFrame() {
        setTitle("Expense Tracker");
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();


    }



    private void createComponents() {
        // Kreiraj ViewPanel koji sadrži lijevi i desni panel
        viewPanel = new ViewPanel();
        add(viewPanel);

        // Kreiraj i postavi MenuBar
        menuBar = new MenuBar();
        setJMenuBar(menuBar);

    }
}
