package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import Controller.Controller;

public class MenuBar extends JMenuBar {

    private Controller controller;

    public MenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportItem = new JMenuItem("Export");
        JMenuItem importItem = new JMenuItem("Import");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportItem);
        fileMenu.add(importItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        add(fileMenu);

        exitItem.addActionListener(e -> {
            if (controller != null) {
                controller.handleExit();
            }
        });

        exportItem.addActionListener(e -> {
            if (controller != null) {
                exportData();
            }
        });

        importItem.addActionListener(e -> {
            if (controller != null) {
                controller.handleImport();
            }
        });
    }

    public void setController(Controller controller) { // Setter method for Controller
        this.controller = controller;
    }

    public void setMenuBarListener(ActionListener listener) {
        for (var component : getComponents()) {
            if (component instanceof JMenu) {
                for (var item : ((JMenu) component).getMenuComponents()) {
                    if (item instanceof JMenuItem) {
                        ((JMenuItem) item).addActionListener(listener);
                    }
                }
            }
        }
    }

    private void exportData() {
        // Ensure the DATA directory exists
        File dataDir = new File("DATA");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        // Create the file in the DATA directory
        File file = new File(dataDir, "exported_data.txt");

        try (FileWriter writer = new FileWriter(file)) {
            // Assuming controller has a method to get the data to be exported
            String data = controller.getExportData();
            writer.write(data);
            JOptionPane.showMessageDialog(this, "Data exported successfully.", "Export", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error exporting data: " + ex.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}