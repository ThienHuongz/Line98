
import java.awt.Graphics;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    private boolean IsRun = true;
    private Thread thread;
    private static int FPS = 60;
    private Map map;
    private MouseHandle ms = new MouseHandle(this);

    public GamePanel() {
        super();
        map = new Map();
        this.addMouseListener(ms);
        this.setFocusable(true);
        thread = new Thread(this);

        // call run method
        thread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / FPS; // 1 giây/ 60
        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        int count = 0;

        while (IsRun) {
            // call paintcomponent
            repaint();
            update();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                timer += remainingTime;

                // sleep chạy theo mili giây
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

                count++;
                if (timer >= 1000000000) {
                    // System.out.println("FPS: "+count);
                    timer = 0;
                    count = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        // to ensure that any necessary pre-painting operations are performed
        super.paintComponent(g);
        map.draw(g);

    }

    public void mouse_click(int mx, int my) {
        map.mouse_click(mx, my);
    }

    public void update() {
        map.update();
    }
}