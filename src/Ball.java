import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Ball {

  private BufferedImage ball, ball2;
  private int x, y, type;
  private boolean ballClicked;

  public Ball(int i, int x, int y, int type) {
    this.x = x;
    this.y = y;
    this.type = type;

    try {
      ball = ImageIO.read(new File("../assets/big" + i + ".png"));
      ball2 = ImageIO.read(new File("../assets/d" + i + ".png"));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void draw(Graphics g) {
    g.drawImage(ball, 20 + 10 * x, 20 + 10 * y, null);
  }

  public void setBallClicked(boolean ballClicked) {
    this.ballClicked = ballClicked;
  }
}
