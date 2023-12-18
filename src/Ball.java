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
      e.printStackTrace();
    }
  }

  public Ball(Ball other) {
    this.color = other.color;
    this.x = other.x;
    this.y = other.y;
    this.type = other.type;

    try {
      ballBig = ImageIO.read(new File("../assets/big" + color + ".png"));
      ballSmall = ImageIO.read(new File("../assets/small" + color + ".png"));
      ball2 = new ImageIcon("../assets/d" + color + ".gif").getImage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void draw(Graphics g) {
    // g.drawImage(ball2, 280, 150, null);
    // g.drawImage(ball2, 280 + 50, 150 + 55, null);

    // g.drawImage(ballSmall, 280 + 60, 150 + 70, null);

    if (ballClicked == true) {
      g.drawImage(ball2, 280 + 54 * x, 150 + 54 * y, null);
    } else {
      if (type == 1) {
        // System.out.println(x+" "+y);
        g.drawImage(ballBig, 280 + 54 * x, 150 + 54 * y, null);
      } else {
        g.drawImage(ballSmall, 285 + 54 * x, 155 + 55 * y, null);
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
