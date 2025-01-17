package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public ViewPanel() {
        setLayout(new BorderLayout());
        rightPanel = new RightPanel();
        leftPanel = new LeftPanel(rightPanel);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    public void setController(Controller controller) {
        leftPanel.setController(controller);
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }
}