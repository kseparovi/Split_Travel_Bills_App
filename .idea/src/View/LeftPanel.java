package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeftPanel extends JPanel implements LeftPanelListener {

    private JTextField nameField;
    private JComboBox<String> categoryField;
    private JTextField paidByField;
    private JSpinner dateField;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton addUserBtn;
    private JButton sendDataBtn;
    private Border border;
    Dimension dim = getPreferredSize();

    public LeftPanel() {

        dim.width = 350; // Povećana širina
        dim.height = 400; // Povećana visina
        setPreferredSize(dim);
        decoratePanel();
        initComps();
        layoutComps();
        activatePanel();
    }

    @Override
    public void leftPanelEventOccurred(LeftPanelEvent event) {
        String name = event.getName();
        String category = event.getCategory();
        String paidBy = event.getPaidBy();
        Date date = event.getDate();
        List<String> users = event.getUsers();

        // Privremeni ispis podataka u konzolu
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Paid by: " + paidBy);
        System.out.println("Date: " + date);
        System.out.println("Split between: " + users.toString());
    }

    private void activatePanel() {
        setBackground(Color.LIGHT_GRAY);
        sendDataBtn.addActionListener(e -> {
            // Logika za obradu klika na dugme
            String name = nameField.getText();
            String category = (String) categoryField.getSelectedItem();
            String paidBy = paidByField.getText();
            Date date = (Date) dateField.getValue();

            // Prikupljanje korisničkih podataka iz tabele
            int rowCount = tableModel.getRowCount();
            List<String> users = new ArrayList<>();
            for (int i = 0; i < rowCount; i++) {
                users.add((String) tableModel.getValueAt(i, 0));
            }

            // Kreiranje i slanje događaja
            LeftPanelEvent event = new LeftPanelEvent(this, name, category, paidBy, date, users);
            leftPanelEventOccurred(event);
        });

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume(); // Ignoriranje unosa ako je cifra
                }
            }
        });

        addUserBtn.addActionListener(e -> {
            String userName = JOptionPane.showInputDialog("Enter user name:");
            if (userName != null && !userName.trim().isEmpty()) {
                tableModel.addRow(new Object[]{userName});
            }
        });
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weighty = 0.1;

        JLabel titleLabel = new JLabel("Title of expense ");

        add(titleLabel, gbc);

        gbc.gridy++;
        add(nameField, gbc);

        gbc.gridy++;
        JLabel categoryLabel = new JLabel("Category: ");

        add(categoryLabel, gbc);

        gbc.gridy++;
        add(categoryField, gbc);

        gbc.gridy++;
        JLabel paidByLabel = new JLabel("Paid by: ");

        add(paidByLabel, gbc);

        gbc.gridy++;
        add(paidByField, gbc);

        gbc.gridy++;
        JLabel dateLabel = new JLabel("Date ");

        add(dateLabel, gbc);

        gbc.gridy++;
        add(dateField, gbc);

        gbc.gridy++;
        JLabel splitBetweenLabel = new JLabel("Split between user:  ");

        add(splitBetweenLabel, gbc);

        gbc.gridy++;
        add(new JScrollPane(userTable), gbc);

        gbc.gridy++;
        add(addUserBtn, gbc);

        gbc.weighty = 0.25;
        gbc.gridy++;
        add(sendDataBtn, gbc);
    }

    private void initComps() {
        nameField = new JTextField(20);
        categoryField = new JComboBox<>(new String[]{"Karte", "Smjestaj", "Hrana", "Pice", "Ostalo"});
        categoryField.setPreferredSize(new Dimension(200, 25));
        paidByField = new JTextField(20);
        dateField = new JSpinner(new SpinnerDateModel());
        dateField.setEditor(new JSpinner.DateEditor(dateField, "dd/MM/yyyy"));
        dateField.setPreferredSize(new Dimension(200, 25));

        tableModel = new DefaultTableModel(new Object[]{"User"}, 0);
        userTable = new JTable(tableModel);
        userTable.setPreferredScrollableViewportSize(new Dimension(200, 100));

        addUserBtn = new JButton("Add User");
        sendDataBtn = new JButton("Send data");

    }

    private void decoratePanel() {
        // Postavljanje okvira i pozadine panela
        border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(border);
        // Postavljanje fonta
        Font font = new Font("Verdana", Font.BOLD, 12);
        setFont(font);
    }
}