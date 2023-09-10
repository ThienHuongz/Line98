import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Map{
    private BufferedImage bg;
    private int[][] matrix = new int[10][10]; 
    private ArrayList<Ball> listOfBall = new ArrayList<>();
    private Random random = new Random();
    private int ballInitNumber = 7;    
    private int EmptyLine = 81; 
 
    
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
        for (int i=0; i< listOfBall.size();i++){
            listOfBall.get(i).draw(g);
        }
    }
    public void init(){
        int i, j, remain, count, stop;
        // srand(time(NULL));
    
        for (i = 0; i < 9; i++){
            for (j = 0; j < 9; j++){
                matrix[i][j] = 0;
            }
        }
    
        
        for (int k=0; k<ballInitNumber;k++){
            remain = randomX(EmptyLine--) + 1;
            stop = 0;
            for (i = 0; i < 9; i++){
                for (j = 0; j < 9; j++){
                    if (matrix[i][j] == 0){
                        remain--;
                        if (remain == 0){
                            int color = randomX(7) + 1;
                            listOfBall.add(new Ball(color, i,j, 1));
                            matrix[i][j] = color;
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
                    if (matrix[i][j] == 0){
                        remain--;
                        if (remain == 0){
                            int color = randomX(7) + 1;
                            listOfBall.add(new Ball(color, i,j, 2));
                            matrix[i][j] = - color;
                            stop = 1;
                            break;
                        }
                    }
                }
                if (stop == 1) break;
            }
        }
    }
}