import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//--------------------------------PRODUCT CLASS--------------------------------------------------

public class Grocery {
    // VARIABLES
    ArrayList<product> productArray;
    int money;
    Point[] heapPositions;
    ArrayList<JButton> heapButtons = new ArrayList<>();
    boolean animating = false;
    boolean maxHeap;
    BufferedImage bg, back, backHover, max, maxHover, min, minHover;
    JPanel inventoryPanel;
    String hp;

    public Grocery(){}

    public Grocery(ArrayList<product> p, boolean mH, String h, int mon){
        productArray = p;
        maxHeap = mH;
        hp = h;
        money = mon;
    }

    public void heapSelectionFrame(){
        JFrame hsFrame = new JFrame();
        hsFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        hsFrame.setResizable(false);
        hsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hsFrame.setLocationRelativeTo(null);
        hsFrame.setLayout(null);

        // Background image
        try { bg = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\MENU.png")); }
        catch (IOException e) { System.out.println("Cannot load image"); }

        JPanel bgPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            }
        };
        bgPanel.setLayout(null);
        hsFrame.setContentPane(bgPanel);

        hsFrame.setVisible(true);

        try {
            max = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\MAX-removebg-preview.png"));
            maxHover = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\MAXHOVER-removebg-preview.png"));
            min = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\MIN-removebg-preview.png"));
            minHover = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\MINHOVVER-removebg-preview.png"));
        } catch (IOException e) { System.out.println("Cannot load image"); }

        Image maxScale = max.getScaledInstance(600, 350, Image.SCALE_SMOOTH);
        Image maxHoverScale = maxHover.getScaledInstance(600, 350, Image.SCALE_SMOOTH);
        Image minScale = min.getScaledInstance(600, 350, Image.SCALE_SMOOTH);
        Image minHoverScale = minHover.getScaledInstance(600, 350, Image.SCALE_SMOOTH);

        JLabel maxButton = new JLabel(new ImageIcon(maxScale));
        maxButton.setLayout(null);
        maxButton.setOpaque(false);
        maxButton.setSize(460, 280);
        maxButton.setLocation(250, 350);
        hsFrame.add(maxButton);

        maxButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maxButton.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                hsFrame.dispose();
                new ProductSelectionFrame(true, "Max").setVisible(true);
            }
            @Override public void mouseEntered(MouseEvent e) { maxButton.setIcon(new ImageIcon(maxHoverScale)); maxButton.repaint(); }
            @Override public void mouseExited(MouseEvent e) { maxButton.setIcon(new ImageIcon(maxScale)); maxButton.repaint(); }
        });

        JLabel minButton = new JLabel(new ImageIcon(minScale));
        minButton.setLayout(null);
        minButton.setOpaque(false);
        minButton.setSize(460, 280);
        minButton.setLocation(850, 350);
        hsFrame.add(minButton);

        minButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minButton.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                hsFrame.dispose();
                new ProductSelectionFrame(false, "Min").setVisible(true);
            }
            @Override public void mouseEntered(MouseEvent e) { minButton.setIcon(new ImageIcon(minHoverScale)); minButton.repaint(); }
            @Override public void mouseExited(MouseEvent e) { minButton.setIcon(new ImageIcon(minScale)); minButton.repaint(); }
        });

        try {
            back = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\BUTTON_MENU-removebg-preview.png"));
            backHover = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\BUTTON_MENU_HOVER.png"));
        } catch (IOException e) { System.out.println("Cannot load image"); }

        Image scaleBack = back.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        Image scaleBackHover = backHover.getScaledInstance(250, 150, Image.SCALE_SMOOTH);

        JLabel backButton = new JLabel(new ImageIcon(scaleBack));
        backButton.setLayout(null);
        backButton.setOpaque(false);
        backButton.setSize(200, 100);
        backButton.setLocation(hsFrame.getWidth() - 250, hsFrame.getHeight() - 150);
        hsFrame.add(backButton);

        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { new HomeFrame1(); hsFrame.dispose();}
            @Override public void mouseEntered(MouseEvent e) { backButton.setIcon(new ImageIcon(scaleBackHover)); backButton.repaint(); }
            @Override public void mouseExited(MouseEvent e) { backButton.setIcon(new ImageIcon(scaleBack)); backButton.repaint(); }
        });

        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        JLabel title = new JLabel("HEAP SELECTION");
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setSize(hsFrame.getWidth(), 110);
        title.setFont(new Font("Press Start 2P", Font.PLAIN, 70));
        title.setBorder(blackBorder);
        title.setLocation(0, 100);
        hsFrame.add(title);

        hsFrame.repaint();
    }

    //--------------------------------INVENTORY FRAME--------------------------------------------------
    public void InventoryFrame() {
        // Reduce initial stock
        for (product p : productArray) {
            switch (p.productName) {
                case "Pancit Canton" -> p.productQuantity -= 120;
                case "C2 Tea" -> p.productQuantity -= 5;
                case "Meat Loaf" -> p.productQuantity -= 15;
                case "Corned Beef" -> p.productQuantity -= 20;
                case "Gardenia Bread" -> p.productQuantity -= 20;
                case "Piattos" -> p.productQuantity -= 70;
                case "Lucky Me Noodles" -> p.productQuantity -= 75;
            }
        }

        JFrame inventory = new JFrame();
        inventory.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inventory.setResizable(false);
        inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventory.setLocationRelativeTo(null);
        inventory.setLayout(null);
        inventory.setVisible(true);

        // Background image
        try { bg = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\MENU.png")); }
        catch (IOException e) { System.out.println("Cannot load image"); }

        JPanel bgPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            }
        };
        bgPanel.setLayout(null);
        inventory.setContentPane(bgPanel);

        // Load button images
        try {
            back = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\BUTTON_MENU-removebg-preview.png"));
            backHover = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\BUTTON_MENU_HOVER.png"));
        } catch (IOException e) { System.out.println("Cannot load image"); }

        Image scaleBack = back.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        Image scaleBackHover = backHover.getScaledInstance(250, 150, Image.SCALE_SMOOTH);

        JLabel backButton = new JLabel(new ImageIcon(scaleBack));
        backButton.setLayout(null);
        backButton.setOpaque(false);
        backButton.setSize(200, 100);
        backButton.setLocation(inventory.getWidth() - 250, inventory.getHeight() - 150);
        inventory.add(backButton);

        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { new CashierTapped(productArray, maxHeap, hp, money); inventory.dispose();}
            @Override public void mouseEntered(MouseEvent e) { backButton.setIcon(new ImageIcon(scaleBackHover)); backButton.repaint(); }
            @Override public void mouseExited(MouseEvent e) { backButton.setIcon(new ImageIcon(scaleBack)); backButton.repaint(); }
        });

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(null);
        inventoryPanel.setBackground(Color.WHITE);
        bgPanel.add(inventoryPanel);

        inventoryPanel.setSize(inventory.getWidth() - 100, inventory.getHeight() - 200);
        inventoryPanel.setLocation(((inventory.getWidth() - inventoryPanel.getWidth()) / 2),
                ((inventory.getHeight() - inventoryPanel.getHeight()) / 2) - 70);

        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        JLabel title = new JLabel("INVENTORY");
        title.setOpaque(true);
        title.setBackground(new Color(223, 237, 245));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setSize(inventoryPanel.getWidth(), 70);
        title.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        title.setBorder(blackBorder);
        inventoryPanel.add(title);

        JLabel heap_money = new JLabel("<html>Mode: "+ hp +" Heap<br><br>Money: $" + money + "</html>");
        heap_money.setLocation(0, title.getHeight() - 1);
        heap_money.setBorder(BorderFactory.createCompoundBorder(blackBorder, BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        heap_money.setSize(inventoryPanel.getWidth(), 70);
        heap_money.setFont(new Font("Press Start 2P", Font.PLAIN, 12));
        inventoryPanel.add(heap_money);

        // PRODUCT BUTTONS
        for (int i = 0; i < productArray.size(); i++){
            product p = productArray.get(i);
            JButton btn = new JButton(p.printInfo());
            heapButtons.add(btn);
        }
        // HEAP PANEL
        JPanel heapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics gr) {
                super.paintComponent(gr);
                Graphics2D g2 = (Graphics2D) gr;
                g2.setStroke(new BasicStroke(3));
                for (int i = 0; i < heapPositions.length; i++) {
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    if (left < heapPositions.length) drawLine(g2, heapPositions[i], heapPositions[left]);
                    if (right < heapPositions.length) drawLine(g2, heapPositions[i], heapPositions[right]);
                }
            }
            private void drawLine(Graphics2D g2, Point parent, Point child) {
                g2.drawLine(parent.x + 100, parent.y + 100, child.x + 100, child.y);
            }


        };
        heapPanel.setLayout(null);
        heapPanel.setSize(inventoryPanel.getWidth(), (inventoryPanel.getHeight() - (title.getHeight() + heap_money.getHeight()) + 2));
        heapPanel.setLocation(0, (title.getHeight() + heap_money.getHeight()) - 2);
        heapPanel.setBorder(blackBorder);
        inventoryPanel.add(heapPanel);

        // -------------------- DYNAMIC HEAP POSITIONS --------------------
        heapPositions = generateHeapPositions(productArray.size(), heapPanel.getWidth());

        // PLACE BUTTONS INITIALLY
        for (int i = 0; i < heapButtons.size(); i++) {
            JButton btn = heapButtons.get(i);
            btn.setSize(250, 120);
            btn.setFont(new Font("Press Start 2P", Font.PLAIN, 12));
            if (i < heapPositions.length) btn.setLocation(heapPositions[i]);
            heapPanel.add(btn);
        }
        heapPanel.revalidate();
        heapPanel.repaint();

        // -------------------- HEAP LOGIC --------------------
        class HeapMethods {

            void swap(int i, int j) {
                product tempProduct = productArray.get(i);
                productArray.set(i, productArray.get(j));
                productArray.set(j, tempProduct);

                JButton tempButton = heapButtons.get(i);
                heapButtons.set(i, heapButtons.get(j));
                heapButtons.set(j, tempButton);
            }

            void heapify(int n, int i) {
                int target = i; // target will be largest for maxHeap, smallest for minHeap
                int left = 2 * i + 1;
                int right = 2 * i + 2;

                if (maxHeap) {
                    if (maxHeap) {

                        if (left < n) {
                            if (productArray.get(left).productQuantity > productArray.get(target).productQuantity
                                    || (productArray.get(left).productQuantity == productArray.get(target).productQuantity &&
                                    productArray.get(left).expireDate < productArray.get(target).expireDate))
                            {
                                target = left;
                            }
                        }

                        if (right < n) {
                            if (productArray.get(right).productQuantity > productArray.get(target).productQuantity
                                    || (productArray.get(right).productQuantity == productArray.get(target).productQuantity &&
                                    productArray.get(right).expireDate < productArray.get(target).expireDate))
                            {
                                target = right;
                            }
                        }
                    }
                } else {
                    // Min Heap: smallest quantity goes on top
                    if (left < n) {
                        if (productArray.get(left).productQuantity < productArray.get(target).productQuantity
                                || (productArray.get(left).productQuantity == productArray.get(target).productQuantity &&
                                productArray.get(left).expireDate < productArray.get(target).expireDate))
                        {
                            target = left;
                        }
                    }

                    if (right < n) {
                        if (productArray.get(right).productQuantity < productArray.get(target).productQuantity
                                || (productArray.get(right).productQuantity == productArray.get(target).productQuantity &&
                                productArray.get(right).expireDate < productArray.get(target).expireDate))
                        {
                            target = right;
                        }
                    }
                }

                if (target != i) {
                    swap(i, target);
                    heapify(n, target);
                }
            }

            void buildHeap() {
                for (int i = productArray.size() / 2 - 1; i >= 0; i--)
                    heapify(productArray.size(), i);
            }
        }

        HeapMethods heapMethods = new HeapMethods();

        // Animate heap buttons
        Runnable animateHeap = () -> {
            Timer timer = new Timer(10, null);
            timer.addActionListener(e -> {
                boolean done = true;
                for (int i = 0; i < heapButtons.size() && i < heapPositions.length; i++) {
                    JButton btn = heapButtons.get(i);
                    Point target = heapPositions[i];
                    int dx = (target.x - btn.getX()) / 3;
                    int dy = (target.y - btn.getY()) / 3;
                    if (dx != 0 || dy != 0) done = false;
                    btn.setLocation(btn.getX() + dx, btn.getY() + dy);
                }
                heapPanel.repaint();
                if (done) ((Timer) e.getSource()).stop();
            });
            timer.start();
        };

        // BUTTON ACTIONS
        ActionListener refillAction = e -> {
            if (animating) return;
            JButton btn = (JButton) e.getSource();
            int index = heapButtons.indexOf(btn);
            product p = productArray.get(index);
            int need = p.productQuantityMax - p.productQuantity;
            int moneyNeed = need * (p.productPrice - 5);
            if (moneyNeed <= money) {
                p.productQuantity += need;
                money -= moneyNeed;
                btn.setText(p.printInfo());
                btn.setFont(new Font("Press Start 2P", Font.PLAIN, 12));
                heap_money.setText("<html>Mode: "+ hp +" Heap<br><br>Money: $" + money + "</html>");
                heapMethods.buildHeap();
                animateHeap.run();
            } else
                JOptionPane.showMessageDialog(null, "Insufficient Money");
        };

        for (JButton b : heapButtons) b.addActionListener(refillAction);

        // Build initial heap
        heapMethods.buildHeap();
        animateHeap.run();
    }
    //----------------------INVENTORY FRAME-------------------------------------

    // -------------------- DYNAMIC HEAP POSITION GENERATOR --------------------
    private Point[] generateHeapPositions(int buttonCount, int panelWidth) {
        ArrayList<Point> positions = new ArrayList<>();
        int level = 0, index = 0;
        while (index < buttonCount) {
            int nodesInLevel = (int) Math.pow(2, level);
            int y = 10 + level * 200;
            for (int i = 0; i < nodesInLevel && index < buttonCount; i++) {
                int x = panelWidth / (nodesInLevel + 1) * (i + 1) - 100;
                positions.add(new Point(x, y));
                index++;
            }
            level++;
        }
        return positions.toArray(new Point[0]);
    }
}

// RUN
class run {
    public static void main(String[] args) {
        new HomeFrame1();
    }
}
