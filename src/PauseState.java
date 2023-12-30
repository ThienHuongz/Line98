
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class PauseState implements GameStateBase {
    private BufferedImage mn[] = new BufferedImage[20];

    GamePanel gamepanel;

    public PauseState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();
    }

    public void init() {
        try {
            mn[0] = ImageIO.read(new File("../assets/pauseScreen.png"));

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }

    }

    public void draw(Graphics g) {

        g.setColor(new Color(0, 0, 0, 150));

        g.drawImage(mn[0], 0, 0, null);

    }

    public void mouse_move(int mx, int my) {

    }

    public void mouse_click(int mx, int my) {
        System.out.println(mx + " " + my);

        // CONTINUE
        if (new Rectangle(300, 270, 400, 100).contains(mx, my)) {
            // System.exit(0);
            SoundEffect.play(6);
            SoundEffect.StopBGM();
            SoundEffect.playBGM(0);
            gamepanel.getGameStateManager().setState(1);
            GamePlay.getInstance(gamepanel).resumeScreen();
        }

        // RESTART
        if (new Rectangle(300, 380, 400, 100).contains(mx, my)) {
            SoundEffect.play(6);
            SoundEffect.StopBGM();
            SoundEffect.playBGM(0);
            GamePlay.getInstance(gamepanel).restart();
            gamepanel.getGameStateManager().setState(1);
            GamePlay.getInstance(gamepanel).startTimer();

        }

        // EXIT
        if (new Rectangle(300, 480, 400, 100).contains(mx, my)) {
            SoundEffect.play(1);
            System.exit(0);
        }
    }

    public void update() {

    }

}
