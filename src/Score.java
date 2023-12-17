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

  Font font;
  private int count = 0;

  public Score() {
    try {

      File fontFile = new File("../assets/digital-7.regular.ttf");

      // Copy the font file to a temporary file
      Path tempFontFile = Files.createTempFile("digital-7", ".ttf");
      Files.copy(fontFile.toPath(), tempFontFile, StandardCopyOption.REPLACE_EXISTING);

      // Load the font from the temporary file
      font = Font.createFont(Font.TRUETYPE_FONT, tempFontFile.toFile()).deriveFont(80f);

    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics g) {
    g.setFont(font);
    Color color = new Color(255, 242, 211);

    g.setColor(color);

    g.drawString(Integer.toString(count), 380, 95);

  }

  public void setCount() {
    this.count = count + 1;
  }

}
