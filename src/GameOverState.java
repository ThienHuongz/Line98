
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        loadUserSavedGame();
    }

    public void init() {
        try {
            mn[0] = ImageIO.read(new File("../assets/TimeUp.png"));

            File fontFile = new File("../assets/digital-7.regular.ttf");

            // Copy the font file to a temporary file
            Path tempFontFile = Files.createTempFile("Lobster-Regular.ttf", ".ttf");
            Files.copy(fontFile.toPath(), tempFontFile, StandardCopyOption.REPLACE_EXISTING);

            // Load the font from the temporary file
            font = Font.createFont(Font.TRUETYPE_FONT, tempFontFile.toFile()).deriveFont(80f);

            fontFile = new File("../assets/Lobster-Regular.ttf");
        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }

    }

    public void draw(Graphics g) {

        g.setColor(new Color(0, 0, 0, 150));

        g.drawImage(mn[0], 0, 0, null);

        g.setFont(font);
        g.drawString(Integer.toString(score), 540, 480);

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

    public void SaveUserData() {
        try {
            PrintWriter writer = new PrintWriter("assets/UserSavedGame/User1.map", "UTF-8");
            writer.println(score);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteUserData(String address) {
        File myObj = new File(address);
        myObj.delete();
    }

    public void loadUserSavedGame() {
        File myObj = new File("assets/UserSavedGame/User1.map");
        if (myObj.isFile()) {
            try {
                InputStream in = getClass().getResourceAsStream("assets/UserSavedGame/User1.map");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                score = Integer.parseInt(br.readLine());
                // String delims = "\\s++";

                // String line = br.readLine();
                // String[] tokens = line.split(delims);

                br.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}
