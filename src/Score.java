import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Score {

  private BufferedImage img;
  private BufferedImage sc[] = new BufferedImage[10];
  private int count = 0;

  public Score() {
    try {
      img = ImageIO.read(new File("../assets/score.png"));
      for (int i = 0; i < 10; i++) {

        sc[i] = img.getSubimage(0 + 18 * i, 0, 18, 35);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void draw(Graphics g) {
    int n = count;
    for (int i = 5; i > 0; i--) {
      int j = n % 10;
      n /= 10;
      g.drawImage(sc[j], 20 + 20 * i, 10, null);
      g.drawImage(sc[j], 250 + 20 * i, 10, null);

    }
  }

  public void setCount() {
    this.count = count + 1;
  }

}
