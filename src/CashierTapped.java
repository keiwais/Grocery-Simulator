import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CashierTapped extends JFrame {
    ChangeInterface ci = new ChangeInterface(0,0);
    static JLabel displayField;
    ImageIcon image;

    private CustomerQueueLine queue; // persistent queue
    private Customers customers;     // persistent sprites
    Grocery grocery;
    int money;

    public CashierTapped(ArrayList<product> p, boolean mH, String h, int mon) {
        this.queue = new CustomerQueueLine();
        money = mon;
        setTitle("GROCERY SIMULATOR");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);

        try {
            image = new ImageIcon(getClass().getResource("CASHIER TAPPED RAW LAYOUT  (1).png"));
            displayField = new JLabel(image);
            displayField.setLayout(null);
            setContentPane(displayField);

            displayField.add(ci);

            // MENU BUTTON
            JButton menuBtn = new JButton(new ImageIcon(
                    getClass().getResource("BUTTON_MENU__11_-removebg-preview.png")));
            menuBtn.setBounds(-185, -110,
                    menuBtn.getIcon().getIconWidth(),
                    menuBtn.getIcon().getIconHeight());
            menuBtn.setBorderPainted(false);
            menuBtn.setContentAreaFilled(false);
            menuBtn.setFocusPainted(false);
            menuBtn.setOpaque(false);
            displayField.add(menuBtn);

            menuBtn.addActionListener(e -> {
                dispose();
                new Grocery().heapSelectionFrame();
            });

            JLabel timebg = new JLabel(new ImageIcon(
                    getClass().getResource("BUTTON_MENU__10_-removebg-preview.png")));
            timebg.setBounds(1050, -125,
                    menuBtn.getIcon().getIconWidth(),
                    menuBtn.getIcon().getIconHeight());
            timebg.setOpaque(false);
            displayField.add(timebg);

            // INVENTORY BUTTON
            JButton invBtn = new JButton(new ImageIcon(
                    getClass().getResource("BUTTON_MENU__9_-removebg-preview.png")));
            invBtn.setBounds(0, 725, 305, 100);
            invBtn.setBorderPainted(false);
            invBtn.setContentAreaFilled(false);
            invBtn.setFocusPainted(false);
            invBtn.setOpaque(false);
            displayField.add(invBtn);

            invBtn.addActionListener(e -> {
                dispose();
                grocery = new Grocery(p, mH, h, money);
                grocery.InventoryFrame();
            });

            // TIME DISPLAY
            JLabel timeLabel = new JLabel("", SwingConstants.CENTER);
            timeLabel.setBounds(1240, 0, 300, 120);
            timeLabel.setFont(new Font("Verdana", Font.BOLD, 30));
            timeLabel.setForeground(Color.WHITE);
            displayField.add(timeLabel);
            displayField.setComponentZOrder(timeLabel, 0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            LocalTime[] currentTime = { LocalTime.of(9, 0) };
            LocalTime endTime = LocalTime.of(17, 0);
            timeLabel.setText(currentTime[0].format(formatter));

            new Timer(35000, e -> {
                currentTime[0] = currentTime[0].plusMinutes(35);
                if (!currentTime[0].isAfter(endTime)) {
                    timeLabel.setText(currentTime[0].format(formatter));
                } else {
                    timeLabel.setText("5:00 PM");
                    ((Timer) e.getSource()).stop();
                }
            }).start();

            // MONEY LABEL
            JLabel kwarta = new JLabel("" + money);
            kwarta.setFont(new Font("Verdana", Font.BOLD, 50));
            kwarta.setForeground(Color.GRAY);
            kwarta.setBounds(1325, 750, 300, 100);
            displayField.add(kwarta);

            customers =  new Customers(displayField, ci);
            customers.activeCustomer = customers;

        } catch (Exception e) {
            System.out.println("Image cannot be found!");
        }
        setVisible(true);
    }

    // ================= MAIN =================
}
