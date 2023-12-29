
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.IOException;

public class GameOverState implements GameStateBase {
    private BufferedImage mn[] = new BufferedImage[20];

    GamePanel gamepanel;

    public GameOverState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();

    }

    public void init() {
        try {
            mn[0] = ImageIO.read(getClass().getResourceAsStream("/assets/TimeUp.png"));
 

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


    }

    public void update() {

    }

}
