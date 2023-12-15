import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Ball {

  private BufferedImage ballBig, ballSmall;
  private Image ball2;
  private int x, y, type, color;
  private boolean ballClicked = false;

  public Ball(int i, int x, int y, int type) {
    this.x = x;
    this.y = y;
    this.type = type;
    this.color = i;
    try {
      ballBig = ImageIO.read(new File("../assets/big" + i + ".png"));
      ballSmall = ImageIO.read(new File("../assets/small" + i + ".png"));
      ball2 = new ImageIcon("../assets/d" + i + ".gif").getImage();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void draw(Graphics g) {
    // g.drawImage(ball2, 15 , 70, null);
    // g.drawImage(ballSmall, 60 , 115, null);

    if (ballClicked == true) {
      g.drawImage(ball2, 45 * x, 50 + 45 * y, null);
    } else {
      if (type == 1) {
        // System.out.println(x+" "+y);
        g.drawImage(ballBig, 45 * x, 50 + 45 * y, null);
      } else {
        g.drawImage(ballSmall, 15 + 45 * x, 70 + 45 * y, null);
      }
    }

  }

  public int getColor() {
    return color;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public void setBallClicked() {
    this.ballClicked = !ballClicked;
  }

  public boolean getBallClicked() {
    return ballClicked;
  }

  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
