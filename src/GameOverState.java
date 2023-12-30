
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class GameOverState implements GameStateBase {
    private BufferedImage mn[] = new BufferedImage[20];
    private Font font;
    GamePanel gamepanel;
    private int score;

    public GameOverState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();

    }

    public void init() {
        try {
            mn[0] = ImageIO.read(new File("../assets/TimeUp.png"));

            File fontFile = new File("../assets/digital-7.regular.ttf");

            // Copy the font file to a temporary file
            Path tempFontFile = Files.createTempFile("Lobster-Regular.ttf", ".ttf");
            Files.copy(fontFile.toPath(), tempFontFile, StandardCopyOption.REPLACE_EXISTING);

            // Load the font from the temporary file
            font = Font.createFont(Font.TRUETYPE_FONT, tempFontFile.toFile()).deriveFont(140f);

            fontFile = new File("../assets/Lobster-Regular.ttf");
        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }

    }

    public void draw(Graphics g) {

        g.setColor(new Color(0, 0, 0, 150));

        g.drawImage(mn[0], 0, 0, null);

        g.setFont(font);
        g.drawString("Score: "+Integer.toString(score), 290, 460);

    }

    public void mouse_move(int mx, int my) {

    }

    public void mouse_click(int mx, int my) {

    }

    public void update() {

    }

    public void setScore(int score) {
        this.score = score;
    }
}
