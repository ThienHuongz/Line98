import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;

public class Map{
    private BufferedImage bg;

    private Ball[][] listOfBall = new Ball[10][10];
    private Random random = new Random();
    private int ballInitNumber = 7;    
    private int EmptyLine = 81; 
    private Score sc = new Score();

    private static Point p = new Point(-1, -1);
    public ArrayList<Point> pathBall = new ArrayList<>();

    public static class Point {
		public int x, y;
		public Point(int x1, int y1) {
			x = x1;
			y = y1;
		}
	}

    public Map(){
        try {
            bg = ImageIO.read(new File("../assets/layout.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }
    public int randomX(int n)    {
        return random.nextInt(n);
    }
    public void draw (Graphics g){
        g.drawImage(bg, 0, 0, null);
        // System.out.println(listOfBall.size());
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (listOfBall[i][j] != null){

                    listOfBall[i][j].draw(g);
                }
            }
        }
        sc.draw(g);
    }
    public void init(){
        int i, j, remain, count, stop;
        // srand(time(NULL));
    
    
        
        for (int k=0; k<ballInitNumber;k++){
            remain = randomX(EmptyLine--) + 1;
            stop = 0;
            for (i = 0; i < 9; i++){
                for (j = 0; j < 9; j++){
                    if (listOfBall[i][j] == null){
                        remain--;
                        if (remain == 0){
                            int color = randomX(7) + 1;
                            listOfBall[i][j]= new Ball(color, i,j, 1);
                            stop = 1;
                            break;
                        }
                    }
                }
                if (stop == 1) break;
            }
        }
        addSmallBall();
    }
    public void addSmallBall(){
        int tmp, i, j, remain, stop;
     
        // srand(time(NULL));
        for (tmp = 0; tmp < 3; tmp++){
            remain = randomX(EmptyLine--) + 1;

            stop = 0;
            for (i = 0; i < 9; i++){
                for (j = 0; j < 9; j++){
                    if (listOfBall[i][j] == null){
                        remain--;
                        if (remain == 0){
                            int color = randomX(7) + 1;
                            listOfBall[i][j]= new Ball(color, i,j, 2);
                            stop = 1;
                            break;
                        }
                    }
                }
                if (stop == 1) break;
            }
        }
    }

    public void smallToBigBall(){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (listOfBall[i][j]!=null && listOfBall[i][j].getType() == 2){
                    listOfBall[i][j].setType(1);
                }
            }
        }
    }

    public boolean checkPath(int startX, int startY, int finishX, int finishY) {
		int []u = {-1, 1, 0, 0};
		int []v = {0, 0, -1, 1};

		Point pCurrent;
        Queue<Point> queue = new LinkedList<>();

		Point [][] parent = new Point[9][9];
		boolean [][] visited = new boolean[9][9];
		

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
							return true;
						}
					}
				}
			}
		return false;
	}

    public void findPath(Point p, Point [][] parent) {
		pathBall = new ArrayList<Point>();

		if (p.x != -1 && p.y != -1) {
			if (parent[p.x][p.y] != new Point(-1, -1)) {
                findPath(parent[p.x][p.y], parent);
			}
		}
        pathBall.add(p);

	}
    
    public void showPath(){
        int i;
        for(i = 1; i < pathBall.size(); i++) {
			// System.out.println(pathBall.get(i).x + " " + pathBall.get(i).y);
            listOfBall[p.x][p.y].setXY(pathBall.get(i).x,pathBall.get(i).y);
            
		}
        i--;
        listOfBall[p.x][p.y].setBallClicked();
        listOfBall[pathBall.get(i).x][pathBall.get(i).y] = listOfBall[p.x][p.y];
        listOfBall[p.x][p.y] = null ;
        p.x=-1;
    }
    public void mouse_click(int mx, int my) {
        if (my>50){
            int yy = (my - 50) / 45;
            int xx = mx  / 45;

            // System.out.println(yy+" "+xx); 
            if ( listOfBall[xx][yy] != null && listOfBall[xx][yy].getType() == 1 ){
                if (p.x != -1){
                    listOfBall[p.x][p.y].setBallClicked();
                }
                listOfBall[xx][yy].setBallClicked();
                p.x=xx;
                p.y=yy;
            }
            else if (p.x != -1 && listOfBall[p.x][p.y].getBallClicked() && (listOfBall[xx][yy] == null || listOfBall[xx][yy].getType() == 2 ) ){
                if (checkPath(p.x, p.y, xx, yy)){
                    showPath();
                    smallToBigBall();
                    addSmallBall();
                }
                

            }
        }

    }

}