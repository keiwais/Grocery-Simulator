import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;

public class ProductSelectionFrame extends JFrame {

    private JTextArea selectedArea;
    private JList<product> productList;
    private JButton startBtn, backBtn;
    private BackgroundPanel bg;

    private static final int BOX_WIDTH = 360;
    private static final int BOX_HEIGHT = 540;
    private static final Color BOX_COLOR = new Color(220, 235, 240);

    private PriorityQueue<product> selectedHeap = new PriorityQueue<>(Comparator.comparingInt(product::getPrice).reversed());
    private HashSet<product> selectedSet = new HashSet<>();
    private ArrayList<product> productSelected = new ArrayList<>();
    boolean maxHeap;
    String heap;
    BufferedImage start, startHover, back, backHover;

    public ProductSelectionFrame(){}

    public ProductSelectionFrame(boolean mH, String h) {
        maxHeap = mH;
        heap = h;
        setTitle("Product Selection");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        bg = new BackgroundPanel("background.png");
        bg.setLayout(null);
        setContentPane(bg);

        selectedArea = new JTextArea();
        selectedArea.setEditable(false);
        selectedArea.setFont(new Font("Press Start 2P", Font.BOLD, 13));
        selectedArea.setBackground(BOX_COLOR);
        selectedArea.setLineWrap(true);
        selectedArea.setWrapStyleWord(true);
        selectedArea.setBorder(new LineBorder(Color.BLACK, 2));
        selectedArea.setBounds(80, 90, BOX_WIDTH, BOX_HEIGHT);
        bg.add(selectedArea);

        DefaultListModel<product> model = new DefaultListModel<>();
        model.addElement(new product("Pancit Canton", 15, 200, 200, 7));
        model.addElement(new product("Piattos", 10, 100, 100, 8));
        model.addElement(new product("Lucky Me Noodles", 15, 150, 150, 9));
        model.addElement(new product("Corned Beef", 20, 100, 100, 16));
        model.addElement(new product("Gardenia Bread", 100, 100, 100, 1));
        model.addElement(new product("Meat Loaf", 25, 300, 300, 27));
        model.addElement(new product("C2 Tea", 30, 50, 50, 10));

        productList = new JList<>(model);
        productList.setFont(new Font("Press Start 2P", Font.BOLD, 13));
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setFixedCellHeight(60);
        productList.setBackground(BOX_COLOR);
        productList.setOpaque(true);

        productList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JTextArea area = new JTextArea(value.toString());
            area.setFont(new Font("Press Start 2P", Font.BOLD, 13));
            area.setWrapStyleWord(true);
            area.setLineWrap(true);
            area.setEditable(false);
            area.setOpaque(true);

            if (selectedSet.contains(value)) {
                area.setBackground(new Color(180, 215, 230));
                area.setBorder(new LineBorder(Color.BLACK, 2));
            } else {
                area.setBackground(BOX_COLOR);
                area.setBorder(null);
            }
            return area;
        });

        productList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = productList.locationToIndex(e.getPoint());
                if (index != -1) {
                    product p = model.getElementAt(index);
                    if (selectedSet.contains(p)) {
                        selectedSet.remove(p);
                        selectedHeap.remove(p);
                        productSelected.remove(p);
                    } else {
                        selectedSet.add(p);
                        selectedHeap.add(p);
                        productSelected.add(p);
                    }
                    updateSelectedArea();
                    productList.repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setBounds(480, 90, BOX_WIDTH, BOX_HEIGHT);
        scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().setBackground(BOX_COLOR);
        bg.add(scrollPane);

        try {
            start = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\START.png"));
            startHover = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\STARTHOVER.png"));
            back = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\BUTTON_MENU-removebg-preview.png"));
            backHover = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\BUTTON_MENU_HOVER.png"));
        } catch (IOException e) { System.out.println("Cannot load image"); }

        Image startScale = start.getScaledInstance(550, 300, Image.SCALE_SMOOTH);
        Image startHoverScale = startHover.getScaledInstance(550, 300, Image.SCALE_SMOOTH);
        Image backScale = back.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        Image backHoverScale = backHover.getScaledInstance(300, 150, Image.SCALE_SMOOTH);

        startBtn = new JButton(new ImageIcon(startScale));
        startBtn.setLayout(null);
        startBtn.setOpaque(false);
        startBtn.setBorderPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setFocusPainted(false);
        startBtn.setSize(230, 120);
        startBtn.setLocation(1200, 530);
        bg.add(startBtn);

        backBtn = new JButton(new ImageIcon(backScale));
        backBtn.setLayout(null);
        backBtn.setOpaque(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.setSize(230, 120);
        backBtn.setLocation(1200, 650);
        bg.add(backBtn);

        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(productSelected.size()<2){
                    JOptionPane.showMessageDialog(null, "Please select at least 2 products");
                } else {
                    dispose();
                    new CashierTapped(productSelected, maxHeap, heap, 5000);
                }
            }

            public void mouseEntered(MouseEvent e) {
                startBtn.setIcon(new ImageIcon(startHoverScale));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startBtn.setIcon(new ImageIcon(startScale));
            }
        });

        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Grocery().heapSelectionFrame();
            }

            public void mouseEntered(MouseEvent e) {
                backBtn.setIcon(new ImageIcon(backHoverScale));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBtn.setIcon(new ImageIcon(backScale));
            }
        });

    }

    private void updateSelectedArea() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<product> temp = new PriorityQueue<>(selectedHeap);
        while (!temp.isEmpty()) {
            sb.append(temp.poll().toString()).append("\n\n");
        }
        selectedArea.setText(sb.toString());
    }

    class BackgroundPanel extends JPanel {
        private Image bgImage;
        BackgroundPanel(String path) {
            bgImage = new ImageIcon(getClass().getResource(path)).getImage();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}