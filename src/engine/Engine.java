package engine;

import entity.BlockBreakerPanel;

import javax.swing.*;
import java.awt.*;

public class Engine implements Runnable {

    @Override
    public void run() {
        JFrame frame = new JFrame("Brick Breaker");

        JFrame startScreen = new JFrame();
        JButton start = new JButton("Start");

        BlockBreakerPanel panel = new BlockBreakerPanel(frame,startScreen);

        start.addActionListener(listener -> {
            startScreen.setVisible(false);
            frame.setVisible(true);
        });

        frame.getContentPane().add(panel);
        frame.getContentPane().setBackground(Color.GRAY);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(false);
        frame.setSize(490, 600);

        frame.setResizable(false);

        startScreen.getContentPane().add(start);

        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startScreen.setVisible(true);
        startScreen.setSize(490, 600);

        startScreen.setResizable(false);
    }
}
