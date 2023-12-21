import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;

public class Score {

  Font font, font2;
  private int count = 0;

  public Score() {
    try {

      File fontFile = new File("../assets/digital-7.regular.ttf");

      // Copy the font file to a temporary file
      Path tempFontFile = Files.createTempFile("digital-7", ".ttf");
      Files.copy(fontFile.toPath(), tempFontFile, StandardCopyOption.REPLACE_EXISTING);

      // Load the font from the temporary file
      font = Font.createFont(Font.TRUETYPE_FONT, tempFontFile.toFile()).deriveFont(40f);

      fontFile = new File("../assets/Lobster-Regular.ttf");

      // Copy the font file to a temporary file
      tempFontFile = Files.createTempFile("digital-7", ".ttf");
      Files.copy(fontFile.toPath(), tempFontFile, StandardCopyOption.REPLACE_EXISTING);

      font2 = Font.createFont(Font.TRUETYPE_FONT, tempFontFile.toFile()).deriveFont(30f);

    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics g) {
    g.setFont(font2);
    Color color = new Color(255, 215, 0);

    g.setColor(color);

    g.drawString("Score", 250, 675);

    g.setFont(font);
    g.drawString(Integer.toString(count), 350, 675);
    g.drawString("Score: "+Integer.toString(count), 350,  675);

  }

  public void setCount() {
    this.count = count + 1;
  }

}
