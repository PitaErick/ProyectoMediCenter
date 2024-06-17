package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;

public class panelBienvenida extends JFrame {

    public panelBienvenida() {
        setUndecorated(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(getClass().getResource("/Visual/imgFondoBienvenida.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        setLayout(new BorderLayout());
        add(backgroundPanel);
        JLabel titNombre = new JLabel("", SwingConstants.CENTER);
        titNombre.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundPanel.add(titNombre, BorderLayout.CENTER);
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
                SwingUtilities.invokeLater(() -> {
                    Principal mainFrame = new Principal();
                    mainFrame.setVisible(true);
                });
            }
        });
        timer.setRepeats(false); // Only trigger the timer once
        timer.start(); // Start the timer
    }

}
