package histogram;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorkSpace extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    BufferedImage image = null;
    int x = 0;
    int y = 0;

    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage img) {
        image = img;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
}

