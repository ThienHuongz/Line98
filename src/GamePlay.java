import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;

public class GamePlay implements GameStateBase {
    private BufferedImage bg;

    private Ball[][] listOfBall;
    private Random random;
    private int ballInitNumber = 7;
    private int EmptyLine = 81;
    private Score sc;
    private Timer timer;

    private static Point p;
    public ArrayList<Point> pathBall;

    private GamePanel gamepanel;
    private boolean showPathIsDone = true;
    private static GamePlay INSTANCE;

    public static class Point {
        public int x, y;

        public Point(int x1, int y1) {
            x = x1;
            y = y1;
        }
    }

    public GamePlay(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();

    }

    public static GamePlay getInstance(GamePanel gamePanel) {
        if (INSTANCE == null) {
            INSTANCE = new GamePlay(gamePanel);
        }
        return INSTANCE;
    }

    public int randomX(int n) {
        return random.nextInt(n);
    }

    public void draw(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (listOfBall[i][j] != null) {

                    listOfBall[i][j].draw(g);
                }
            }
        }
        sc.draw(g);
        timer.draw(g);

    }

    public void init() {
        listOfBall = new Ball[10][10];
        random = new Random();
        sc = new Score();
        timer = new Timer(100);
        p = new Point(-1, -1);
        pathBall = new ArrayList<>();

        try {
            bg = ImageIO.read(new File("../assets/background.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        int i, j, remain, stop;
        // srand(time(NULL));
        for (int k = 0; k < ballInitNumber; k++) {
            remain = randomX(EmptyLine--) + 1;
            stop = 0;
            for (i = 0; i < 9; i++) {
                for (j = 0; j < 9; j++) {
                    if (listOfBall[i][j] == null) {
                        remain--;
                        if (remain == 0) {
                            int color = randomX(7) + 1;
                            listOfBall[i][j] = new Ball(color, i, j, 1);
                            stop = 1;
                            break;
                        }
                    }
                }
                if (stop == 1)
                    break;
            }
        }
        addSmallBall();
    }

    public void addSmallBall() {
        int tmp, i, j, remain, stop;

        // srand(time(NULL));
        for (tmp = 0; tmp < 3; tmp++) {
            remain = randomX(EmptyLine--) + 1;

            stop = 0;
            for (i = 0; i < 9; i++) {
                for (j = 0; j < 9; j++) {
                    if (listOfBall[i][j] == null) {
                        remain--;
                        if (remain == 0) {
                            int color = randomX(7) + 1;
                            listOfBall[i][j] = new Ball(color, i, j, 2);
                            stop = 1;
                            break;
                        }
                    }
                }
                if (stop == 1)
                    break;
            }
        }
    }

    public void smallToBigBall() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (listOfBall[i][j] != null && listOfBall[i][j].getType() == 2) {
                    listOfBall[i][j].setType(1);
                }
            }
        }
    }

    public static Ball[][] deepCopyBallArray(Ball[][] original) {
        if (original == null) {
            return null;
        }

        int rows = original.length;
        int cols = original[0].length;

        Ball[][] copy = new Ball[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (original[i][j] != null) {
                    copy[i][j] = new Ball(original[i][j]); // Assuming Ball has a copy constructor
                }
            }
        }

        return copy;
    }

    public boolean checkPath(int startX, int startY, int finishX, int finishY) {

        // Ball[][] listOfBallTest = deepCopyBallArray(listOfBall);

        // smallToBigBall();

        int[] u = { -1, 1, 0, 0 };
        int[] v = { 0, 0, -1, 1 };

        Point pCurrent;
        Queue<Point> queue = new LinkedList<>();

        Point[][] parent = new Point[9][9];
        boolean[][] visited = new boolean[9][9];

        queue.add(new Point(startX, startY));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (listOfBall[i][j] != null && listOfBall[i][j].getType() == 1) {
                    visited[i][j] = true;
                } else {
                    visited[i][j] = false;
                }
            }
        }

        parent[startX][startY] = new Point(-1, -1);
        while (!queue.isEmpty()) {
            pCurrent = queue.peek();

            queue.poll();

            for (int k = 0; k < 4; k++) {

                int adji = pCurrent.x + u[k];
                int adjj = pCurrent.y + v[k];

                if (adji >= 0 && adji < 9 && adjj >= 0 && adjj < 9 && !visited[adji][adjj]) {

                    queue.add(new Point(adji, adjj));

                    visited[adji][adjj] = true;

                    parent[adji][adjj] = new Point(pCurrent.x, pCurrent.y);

                    if (visited[finishX][finishY]) {
                        findPath(new Point(finishX, finishY), parent);
                        // listOfBall = deepCopyBallArray(listOfBallTest);
                        // listOfBall[p.x][p.y].setBallClicked();
                        return true;
                    }
                }
            }
        }
        // listOfBall = deepCopyBallArray(listOfBallTest);
        // listOfBall[p.x][p.y].setBallClicked();

        return false;
    }

    public void findPath(Point p, Point[][] parent) {
        pathBall = new ArrayList<Point>();

        if (p.x != -1 && p.y != -1) {
            if (parent[p.x][p.y] != new Point(-1, -1)) {
                findPath(parent[p.x][p.y], parent);
            }
        }
        pathBall.add(p);

    }

    public void showPath() {
        int delayBetweenBalls = 250; // 0.25 seconds
        showPathIsDone = false;

        ActionListener drawAction = new ActionListener() {
            private int index = 1; // Start from 1 to skip the first point (p.x, p.y)

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < pathBall.size()) {
                    Point currentPoint = pathBall.get(index);
                    listOfBall[p.x][p.y].setXY(currentPoint.x, currentPoint.y);

                    index++;
                } else {
                    // All points processed, stop the timer
                    ((javax.swing.Timer) e.getSource()).stop();

                    index--;
                    listOfBall[p.x][p.y].setBallClicked();
                    listOfBall[pathBall.get(index).x][pathBall.get(index).y] = listOfBall[p.x][p.y];
                    listOfBall[p.x][p.y] = null;
                    p.x = -1;

                    smallToBigBall();
                    addSmallBall();
                    showPathIsDone = true;
                }
            }
        };

        javax.swing.Timer timer = new javax.swing.Timer(delayBetweenBalls, drawAction);
        timer.start();
    }

    public void handleBallConsecutive(int i, int j, int type) {
        if (type == 1) {
            for (int k = 0; k < 5; k++) {
                listOfBall[i][j + k] = null;
            }
        } else {
            for (int k = 0; k < 5; k++) {
                listOfBall[i + k][j] = null;
            }
        }
    }

    public boolean checkBallScore() {
        // vertical
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (listOfBall[i][j] != null) {
                    int d = 1;
                    for (int k = 1; k < 5; k++) {
                        // check inside
                        if (j + k >= 9 || listOfBall[i][j + k] == null) {
                            break;
                        }
                        // check 5 ball in line
                        if (listOfBall[i][j].getColor() == listOfBall[i][j + k].getColor()) {
                            d++;
                            // System.out.println(d + " " + i + " " + j + " " + k);
                            if (d == 5) {
                                handleBallConsecutive(i, j, 1);
                                EmptyLine += 5;
                                return true;
                            }
                        } else {
                            d = 1;
                        }
                    }
                }

            }
        }
        // horizontal
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (listOfBall[i][j] != null) {
                    int d = 1;
                    for (int k = 1; k < 5; k++) {
                        // check inside
                        if (i + k >= 9 || listOfBall[i + k][j] == null) {
                            break;
                        }
                        // check 5 ball in line
                        if (listOfBall[i][j].getColor() == listOfBall[i + k][j].getColor()) {
                            d++;
                            // System.out.println(d + " " + i + " " + j + " " + k + "aaaaaa");
                            if (d == 5) {
                                handleBallConsecutive(i, j, 2);
                                return true;
                            }
                        } else {
                            d = 1;
                        }
                    }
                }

            }
        }
        return false;
    }

    // click => checkPath => if can go => showPath => smallToBigBall => addSmallBall
    // => checkBall count Score
    public void mouse_click(int mx, int my) {
        if (new Rectangle(570, 650, 30, 30).contains(mx, my)) {
            pauseScreen();
            SoundEffect.StopBGM();
            SoundEffect.playBGM(4);
            gamepanel.getGameStateManager().setState(3);

        } else if (new Rectangle(640, 650, 50, 30).contains(mx, my)) {
            SoundEffect.StopBGM();
        } else if (new Rectangle(720, 650, 50, 30).contains(mx, my)) {
            SoundEffect.playBGM(0);
        }

        if (showPathIsDone) {
            if (my > 150) {
                int yy = (my - 150) / 54;
                int xx = (mx - 280) / 54;
                SoundEffect.play(2);
                // System.out.println(yy+" "+xx);
                if (listOfBall[xx][yy] != null && listOfBall[xx][yy].getType() == 1) {
                    if (p.x != -1) {
                        listOfBall[p.x][p.y].setBallClicked();
                    }
                    listOfBall[xx][yy].setBallClicked();
                    p.x = xx;
                    p.y = yy;
                } else if (p.x != -1 && listOfBall[p.x][p.y].getBallClicked()
                        && (listOfBall[xx][yy] == null || listOfBall[xx][yy].getType() == 2)) {
                    if (checkPath(p.x, p.y, xx, yy)) {
                        showPath();

                        // System.out.println("asd");
                    }

                }
            }
        }

    }

    public void mouse_move(int mx, int my) {

    }

    public void update() {
        if (checkBallScore()) {
            SoundEffect.play(1);
            sc.setCount();
        }

        if (timer.isGameOver()) {
            gamepanel.getGameStateManager().setScore(sc.getCount());
            SoundEffect.StopBGM();
            SoundEffect.playBGM(5);
            gamepanel.getGameStateManager().setState(2);

        }

    }

    public void pauseScreen() {
        timer.stop();
    }

    public void resumeScreen() {
        timer.resume();
    }

    public void startTimer() {
        timer.start();
    }

    public void restart() {
        init();
    }
}