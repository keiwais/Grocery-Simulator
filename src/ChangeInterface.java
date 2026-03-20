import javax.swing.*;
import java.awt.*;

public class ChangeInterface extends JPanel {

    double received;
    double total;
    double change;
    double giving = 0;

    JLabel label2;
    JLabel label4;
    JLabel label5;
    JLabel label3;

    public ChangeInterface(double totalAmount,  double receivedAmount) {
        this.total = totalAmount;
        this.received = receivedAmount;
        this.change = received - total;

        setSize(225, 150);
        setLayout(null);
        setLocation(505, 443);
        setBackground(Color.BLACK);

        JLabel label1 = new JLabel("CASH PAYMENT");
        label1.setBounds(70, 10, 200, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 12));
        label1.setForeground(Color.WHITE);
        add(label1);

        label2 = new JLabel("RECEIVED: " + "  ₱ " + "0.00");
        label2.setBounds(20, 40, 200, 30);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label2.setForeground(Color.WHITE);
        add(label2);

        label3 = new JLabel("TOTAL: " + "  ₱ " + String.format("%.2f", total));
        label3.setBounds(20, 55, 200, 30);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label3.setForeground(Color.WHITE);
        add(label3);

        //nanundog ko nimo francis bwhahaha
        label4 = new JLabel("<html><font color='white'>CHANGE: </font><u><font color='yellow'>₱" + " " + "0.00" + "</font></u></html>");
        label4.setBounds(20, 80, 200, 30);
        label4.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(label4);

        label5 = new JLabel("<html><font color='white'>GIVING: </font><u><font color='green'>₱" +  " " + "0.00" + "</font></u></html>");
        label5.setBounds(20, 110, 200, 30);
        label5.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(label5);

        setVisible(true);
    }
    public void showMoney() {
        label2.setText("RECEIVED:  ₱ " + String.format("%.2f", received));
        label4.setText("<html><font color='white'>CHANGE: </font><u><font color='yellow'>₱" + " " + String.format("%.2f", change));
    }
    public boolean addGiving(double amount) {
        giving += amount;
        label5.setText("<html><font color='white'>GIVING: </font><u><font color='green'>₱" + String.format("%.2f", giving));

        return giving >= change;
    }

    public void setTransaction(double total, double received) {
        this.total = total;
        this.received = received;
        this.change = received - total;
        this.giving = 0;

        label3.setText("TOTAL:  ₱ " + String.format("%.2f", total));
        label2.setText("RECEIVED:  ₱ " + String.format("%.2f", received));
        label4.setText("<html><font color='white'>CHANGE: </font><u><font color='yellow'>₱ "
                + String.format("%.2f", change) + "</font></u></html>");
        label5.setText("<html><font color='white'>GIVING: </font><u><font color='green'>₱" + String.format("%.2f", giving));
    }


}
