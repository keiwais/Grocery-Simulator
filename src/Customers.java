// DIRI MO PAG ADD OG CUSTOMER

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Customers {

    public JButton sprite;
    private ImageIcon normalIcon;
    private ImageIcon sourIcon;
    Customers activeCustomer;
    ChangeInterface changeUI;
    int customerTotal, customerPaid;
    JComponent container;
    Timer t;

    public void showSour() {
        // Change bounds for sour sprite
        CharacterImages chosen = getCurrentCharacterImages();
        sprite.setBounds(chosen.sourX, chosen.sourY, chosen.sourWidth, chosen.sourHeight);
        sprite.setIcon(sourIcon);
        sprite.repaint();
    }

    public void showNormal() {
        // Change bounds for normal sprite
        CharacterImages chosen = getCurrentCharacterImages();
        sprite.setBounds(chosen.normalX, chosen.normalY, chosen.normalWidth, chosen.normalHeight);
        sprite.setIcon(normalIcon);
        sprite.repaint();
    }

    // Helper to get the current character's data
    private CharacterImages getCurrentCharacterImages() {
        for (CharacterImages c : characterList) {
            if (sprite.getIcon() == normalIcon || sprite.getIcon() == sourIcon) {
                return c;
            }
        }
        return characterList.get(0); // fallback
    }

    private static class CharacterImages {
        String normalPath;
        int normalX, normalY, normalWidth, normalHeight;

        String sourPath;
        int sourX, sourY, sourWidth, sourHeight;

        public CharacterImages(
                String normalPath, int normalX, int normalY, int normalWidth, int normalHeight,
                String sourPath, int sourX, int sourY, int sourWidth, int sourHeight
        ) {
            this.normalPath = normalPath;
            this.normalX = normalX;
            this.normalY = normalY;
            this.normalWidth = normalWidth;
            this.normalHeight = normalHeight;

            this.sourPath = sourPath;
            this.sourX = sourX;
            this.sourY = sourY;
            this.sourWidth = sourWidth;
            this.sourHeight = sourHeight;
        }
    }

    private static final List<CharacterImages> characterList = new ArrayList<>();

    static {
        // Example: 5 characters (use same image as placeholder for now)
        characterList.add(new CharacterImages(
                "ManNormal.png", 760, 110, 590, 600,
                "ManSour.png", 765, 115, 600, 610
        ));
        characterList.add(new CharacterImages(
                "EmoGirlNormal.png", 780, 125, 550, 580,
                "EmoGirlSour.png", 785, 125, 560, 590
        ));
        characterList.add(new CharacterImages(
                "GranNormal.png", 740, 200, 500, 510,
                "GranSour.png", 745, 200, 610, 620
        ));
    }

    public Customers(JComponent con, ChangeInterface ci) {
        changeUI = ci;
        container = con;
        ImageIcon money = new ImageIcon(getClass().getResource("money.png"));
        Image money1 = money.getImage();
        Image money2 = money1.getScaledInstance(180, 120, Image.SCALE_SMOOTH);
        JButton moneyBtn = new JButton(new ImageIcon(money2));
        moneyBtn.setBounds(750, 685, 239, 159);
        moneyBtn.setBorderPainted(false);
        moneyBtn.setContentAreaFilled(false);
        moneyBtn.setFocusPainted(false);
        moneyBtn.setOpaque(false);
        moneyBtn.setVisible(false);

        ImageIcon cashreg = new ImageIcon(getClass().getResource("image-removebg-preview (2).png"));
        Image cashreg1 = cashreg.getImage();
        Image cashreg2 = cashreg1.getScaledInstance(800, 350, Image.SCALE_SMOOTH);
        JLabel cashregLabel = new JLabel(new ImageIcon(cashreg2));
        cashregLabel.setBounds(-450, 240, 1920, 1080);
        cashregLabel.setVisible(false);

        JButton monbtn1 = new JButton();
        monbtn1.setBounds(330, 720, 60, 60);
        monbtn1.setContentAreaFilled(false);
        monbtn1.setBorderPainted(false);
        monbtn1.setEnabled(false);

        JButton monbtn2 = new JButton();
        monbtn2.setBounds(404, 720, 60, 60);
        monbtn2.setContentAreaFilled(false);
        monbtn2.setBorderPainted(false);
        monbtn2.setEnabled(false);

        JButton monbtn3 = new JButton();
        monbtn3.setBounds(480, 720, 60, 60);
        monbtn3.setOpaque(false);
        monbtn3.setContentAreaFilled(false);
        monbtn3.setBorderPainted(false);
        monbtn3.setEnabled(false);

        JButton monbtn4 = new JButton();
        monbtn4.setBounds(558, 720, 60, 60);
        monbtn4.setOpaque(false);
        monbtn4.setContentAreaFilled(false);
        monbtn4.setBorderPainted(false);
        monbtn4.setEnabled(false);

        JButton monbtn5 = new JButton();
        monbtn5.setBounds(630, 720, 60, 60);
        monbtn5.setOpaque(false);
        monbtn5.setContentAreaFilled(false);
        monbtn5.setBorderPainted(false);
        monbtn5.setEnabled(false);

        JButton monbtn6 = new JButton();
        monbtn6.setBounds(330, 785, 60, 55);
        monbtn6.setOpaque(false);
        monbtn6.setContentAreaFilled(false);
        monbtn6.setBorderPainted(false);
        monbtn6.setEnabled(false);

        JButton monbtn7 = new JButton();
        monbtn7.setBounds(404, 785, 60, 55);
        monbtn7.setOpaque(false);
        monbtn7.setContentAreaFilled(false);
        monbtn7.setBorderPainted(false);
        monbtn7.setEnabled(false);

        JButton monbtn8 = new JButton();
        monbtn8.setBounds(480, 785, 60, 55);
        monbtn8.setOpaque(false);
        monbtn8.setContentAreaFilled(false);
        monbtn8.setBorderPainted(false);
        monbtn8.setEnabled(false);

        JButton monbtn9 = new JButton();
        monbtn9.setBounds(558, 785, 60, 55);
        monbtn9.setOpaque(false);
        monbtn9.setContentAreaFilled(false);
        monbtn9.setBorderPainted(false);
        monbtn9.setEnabled(false);

        JButton monbtn10 = new JButton();
        monbtn10.setBounds(630, 785, 60, 55);
        monbtn10.setOpaque(false);
        monbtn10.setContentAreaFilled(false);
        monbtn10.setBorderPainted(false);
        monbtn10.setEnabled(false);

        JButton[] cashMoneyButtons = {
                monbtn1, monbtn2, monbtn3, monbtn4, monbtn5,
                monbtn6, monbtn7, monbtn8, monbtn9, monbtn10
        };

        double[] moneyValues = {
                1000, // monbtn1
                500, // monbtn2
                200, // monbtn3
                100, // monbtn4
                50,  // monbtn5
                .5,   // monbtn6
                1,   // monbtn7
                5,   // monbtn8
                10,  // monbtn9
                20   // monbtn10
        };


        moneyBtn.addActionListener(e -> {
            moneyBtn.setVisible(false);
            cashregLabel.setVisible(true);

            for (JButton btn : cashMoneyButtons) {
                btn.setEnabled(true);
            }

            if (changeUI != null) {
                changeUI.showMoney();
            }
        });

            Random rand = new Random();
            CharacterImages chosen = characterList.get(rand.nextInt(characterList.size()));

            // NORMAL sprite
            ImageIcon normalOriginal = new ImageIcon(getClass().getResource(chosen.normalPath));
            Image normalScaled = normalOriginal.getImage().getScaledInstance(
                    chosen.normalWidth, chosen.normalHeight, Image.SCALE_SMOOTH);
            normalIcon = new ImageIcon(normalScaled);

            // SOUR sprite
            ImageIcon sourOriginal = new ImageIcon(getClass().getResource(chosen.sourPath));
            Image sourScaled = sourOriginal.getImage().getScaledInstance(
                    chosen.sourWidth, chosen.sourHeight, Image.SCALE_SMOOTH);
            sourIcon = new ImageIcon(sourScaled);

            // CREATE BUTTON (start with normal sprite)
            sprite = new JButton(normalIcon);
            sprite.setBounds(chosen.normalX, chosen.normalY, chosen.normalWidth, chosen.normalHeight);
            sprite.setBorderPainted(false);
            sprite.setContentAreaFilled(false);
            sprite.setFocusPainted(false);
            sprite.setOpaque(false);
            sprite.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moneyBtn.setVisible(true);
                    customerTotal = 100 + (int) (Math.random() * 500);
                    customerPaid = customerTotal + (int) (Math.random() * 1000);
                    changeUI.setTransaction(customerTotal, customerPaid);
                }
            });

        t = new Timer(15000, e -> {
            activeCustomer.showSour();
        });
        t.setRepeats(false);
        t.start();




        container.add(sprite);
        container.add(moneyBtn);
        container.add(monbtn1);
        container.add(monbtn2);
        container.add(monbtn3);
        container.add(monbtn4);
        container.add(monbtn5);
        container.add(monbtn6);
        container.add(monbtn7);
        container.add(monbtn8);
        container.add(monbtn9);
        container.add(monbtn10);
        container.add(cashregLabel);

        for (int i = 0; i < cashMoneyButtons.length; i++) {
            int index = i;

            cashMoneyButtons[i].addActionListener(e -> {

                if (changeUI == null) return;

                boolean done = changeUI.addGiving(moneyValues[index]);

                if (done) {
                    container.remove(sprite);
                    container.remove(moneyBtn);
                    for (JButton btn : cashMoneyButtons) {
                        container.remove(btn);
                    }
                    container.remove(cashregLabel);
                    container.repaint();

                    cashregLabel.setVisible(false);

                    for (JButton btn : cashMoneyButtons) {
                        btn.setEnabled(false);
                    }

                    changeUI.setTransaction(0,0);
                    nextCustomer();
                }
            });
        }

        container.repaint();
    }

    // Method to generate a new random customer
    private void nextCustomer() {
        activeCustomer = new Customers(container, changeUI);
        activeCustomer.activeCustomer = activeCustomer;
    }
}
