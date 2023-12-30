import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class StartState implements GameStateBase {
    private BufferedImage mn[] = new BufferedImage[10];

    GamePanel gamepanel;

    public StartState(GamePanel gamepanel) {
        SoundEffect.playBGM(0);
        this.gamepanel = gamepanel;
        init();
    }

    public void init() {
        try {
            mn[0] = ImageIO.read(new File("../assets/start.png"));

        } catch (IOException e) {
            System.err.println("Error loading start image from file: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        g.drawImage(mn[0], 0, 0, null);

    }

    public void mouse_move(int mx, int my) {

    }

    public void mouse_click(int mx, int my) {
        // System.out.println(mx+" "+my);
        if (new Rectangle(100, 169, 350, 350).contains(mx, my)) {
            // System.exit(0);
            // SoundEffect.play(2);
            gamepanel.getGameStateManager().setState(1);
        }
    }

    public void update() {
    }

}
