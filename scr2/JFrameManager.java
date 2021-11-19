import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JFrameManager {

    JFrame frame = new JFrame();

    public JFrameManager(BufferedImage image) {
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }

    public void update() {
        frame.repaint();
    }

    public void destroy() {
        frame.dispose();
    }
}
