
import java.awt.Graphics;
import java.io.PrintWriter;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    private boolean IsRun = true;
    private Thread thread;
    private static int FPS = 60;
    private MouseHandle ms = new MouseHandle(this);

    private GameStateManager gameStates;
    private WindowHandle wh;

    public GamePanel(WindowHandle wh) {
        super();
        this.wh = wh;
        this.addMouseListener(ms);
        this.setFocusable(true);
        thread = new Thread(this);

        gameStates = new GameStateManager(this);

        // call run method
        thread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / FPS; // 1 giây/ 60
        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;

        while (true) {

            IsPause();
            IsWindowDeactivated();

            // call paintcomponent
            repaint();
            if (IsRun)
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

                if (timer >= 1000000000) {
                    // System.out.println("FPS: "+count);
                    timer = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void IsPause() {
        // if (key.isKeyEsc() == true && gameState.getCurrentState() == 2) {
        // IsRun = !IsRun;
        // key.setKeyEsc(false);
        // }
    }

    public void IsWindowDeactivated() {
        if (wh.IsWindowDeactivated) {
            if (gameStates.getCurrentState() == 1) {
                GamePlay.getInstance(this).pauseScreen();
                SoundEffect.StopBGM();
                SoundEffect.playBGM(4);
                gameStates.setState(3);
            }
            wh.IsWindowDeactivated = false;

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // to ensure that any necessary pre-painting operations are performed
        super.paintComponent(g);
        gameStates.draw(g);
        // map.draw(g);

    }

    public void mouse_click(int mx, int my) {
        // map.mouse_click(mx, my);
        gameStates.mouse_click(mx, my);
    }

    public void update() {
        // map.update();
        gameStates.update();
        
    }



    public GameStateManager getGameStateManager() {
        return gameStates;
    }

    public WindowHandle getWindowHandle() {
        return wh;
    }
}
