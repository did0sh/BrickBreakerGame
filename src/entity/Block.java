package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Block extends Rectangle {
    private Image pic;
    private boolean destroyed;

    int movX;
    int movY;

    Block(int x, int y, int w, int h, String imagePath) {
        this.x = x;
        this.y = y;

        movX = 3;
        movY = 3;

        this.width = w;
        this.height = h;

        try {
            pic = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, Component c) {
        if (!destroyed)
            g.drawImage(pic, x, y, width, height, c);
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
