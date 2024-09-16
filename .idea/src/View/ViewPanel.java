package View;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public ViewPanel() {
        setLayout(new BorderLayout());

        // Kreiramo lijevi i desni panel
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();

        // Kreiramo JSplitPane za podjelu prozora
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(400); // Postavi razdjelnik

        // Dodajemo SplitPane na glavni panel
        add(splitPane, BorderLayout.CENTER);
    }
}
