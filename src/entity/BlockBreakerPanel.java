package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class BlockBreakerPanel extends JPanel implements KeyListener {
    private List<Block> blocks;
    private Block ball;
    private Block paddle;

    private JFrame mainFrame;
    private JFrame startScreen;

    private Thread thread;

    void reset() {
        blocks = new ArrayList<>();
        
        ball = new Block(237, 435, 35, 25, "img/ball.png");
        paddle = new Block(175, 480, 150, 25, "img/ball-holder.png");
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60+2),0,60,25, "img/red.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60+2),25,60,25, "img/green.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60+2),50,60,25, "img/orange.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i*60+2),75,60,25, "img/yellow.png"));
        }

        addKeyListener(this);
        setFocusable(true);
    }

    public BlockBreakerPanel(JFrame frame, JFrame startScreen) {
        this.mainFrame = frame;
        this.startScreen = startScreen;

        reset();

        thread = new Thread(() -> {
            while (true) {
                update();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        });
        thread.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        blocks.forEach(block -> block.draw(g, this));
        ball.draw(g, this);
        paddle.draw(g, this);
    }

    public void update() {
        ball.x += ball.movX;

        if(ball.x > (getWidth() - 25) || ball.x < 0)
            ball.movX *= -1;

        if(ball.y < 0 || ball.intersects(paddle))
            ball.movY *= -1;

        ball.y += ball.movY;

        if(ball.y > getHeight()) {
            thread = null;
            reset();
            mainFrame.setVisible(false);
            startScreen.setVisible(true);
        }

        blocks.forEach(block -> {
            if(ball.intersects(block) && !block.isDestroyed()) {
                ball.movY *=-1;
                block.setDestroyed(true);
            }
        });
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            paddle.x += 1;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
