package vue;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.Serial;

/**
 * The type Set back ground image.
 */
public class SetBackGroundImage extends JPanel{
    /**
     * Sets background image.
     *
     * @param img the img
     * @return the background image
     * @throws IOException the io exception
     */
    public static JPanel setBackgroundImage(final File img) throws IOException {
        JPanel panel = new JPanel(null) {
            @Serial
            private static final long serialVersionUID = 1;

            private final BufferedImage buf = ImageIO.read(img);

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(buf, 0, 0, null);
            }
        };

        return panel;
    }
}