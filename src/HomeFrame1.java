import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class HomeFrame1 extends JFrame {

    JLabel displayField;
    BufferedImage image, start, quit;

    public HomeFrame1() {

        setTitle("GROCERY SIMULATOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        try {
            image = ImageIO.read(new File("C:\\Users\\user\\Downloads\\Assets\\BG_NO_BLUR.png"));
            displayField = new JLabel(){
                public void paintComponent(Graphics g){
                    super.paintComponent(g);
                    if (image != null) {
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                    }
                }
            };
            displayField.setLayout(null);
            setContentPane(displayField);

            ImageIcon extraImage = new ImageIcon(getClass().getResource("TITLE.png"));
            JLabel extraLabel = new JLabel(extraImage);
            extraLabel.setBounds(460, 50, extraImage.getIconWidth(), extraImage.getIconHeight());
            displayField.add(extraLabel);

            ImageIcon start = new ImageIcon(getClass().getResource("START.png"));
            JButton Startbutton = new JButton(start);
            Startbutton.setBounds(635, 400, 280, 150);
            Startbutton.setBorderPainted(false);
            Startbutton.setContentAreaFilled(false);
            Startbutton.setFocusPainted(false);
            Startbutton.setOpaque(false);
            displayField.add(Startbutton);
            Startbutton.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { Startbutton.setIcon(new ImageIcon(getClass().getResource("STARTHOVER.png")));}
                @Override public void mouseExited(MouseEvent e) { Startbutton.setIcon(new ImageIcon(getClass().getResource("START.png")));}
            });


            // ACTION LISTENER

            ImageIcon quit = new ImageIcon(getClass().getResource("QUIT.png"));
            JButton QuitButton = new JButton(quit);
            QuitButton.setBounds(635, 550, 280, 150);
            QuitButton.setBorderPainted(false);
            QuitButton.setContentAreaFilled(false);
            QuitButton.setFocusPainted(false);
            QuitButton.setOpaque(false);
            displayField.add(QuitButton);
            QuitButton.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { QuitButton.setIcon(new ImageIcon(getClass().getResource("QUITHOVER.png")));}
                @Override public void mouseExited(MouseEvent e) { QuitButton.setIcon(new ImageIcon(getClass().getResource("QUIT.png")));}
            });

            // ACTION LISTENER
            Startbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Grocery().heapSelectionFrame();
                    dispose();
                }
            });

            QuitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            ImageIcon sound = new ImageIcon(getClass().getResource("SOUND_ON.png"));
            ImageIcon soundOff = new ImageIcon(getClass().getResource("SOUND_OFF.png"));
            JButton SoundButton = new JButton(sound);
            SoundButton.setBounds(1400, 30, 100, 100);
            SoundButton.setBorderPainted(false);
            SoundButton.setContentAreaFilled(false);
            SoundButton.setFocusPainted(false);
            SoundButton.setOpaque(false);
            displayField.add(SoundButton);

            // ACTION LISTENER
            SoundButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(SoundButton.getIcon()==sound) {
                        SoundButton.setIcon(soundOff);
                        SoundButton.setLocation(1400, 10);
                    } else {
                        SoundButton.setIcon(sound);
                        SoundButton.setLocation(1400, 30);
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("Image cannot be found!");
        }

        setVisible(true);

    }
}
