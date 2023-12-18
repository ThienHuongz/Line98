import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Timer {
    private long startTime;
    private long stopTime = 0;
    private long endTime;
    private boolean running;

    public Timer(int time) {
        running = false;
        endTime = time;
    }

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    public void resume() {
        if (!running) {
            running = true;
            startTime += System.currentTimeMillis() - stopTime;
            stopTime = 0;
        }
    }

    public void stop() {
        if (running) {
            stopTime = System.currentTimeMillis();
            running = false;
        }
    }

    public int getElapsedTime() {
        int timeStopped = 0;
        if(running == false){ 
            timeStopped = (int)((System.currentTimeMillis() - stopTime) / 1E3);
        }
        int res = (int)(endTime - (System.currentTimeMillis()-startTime) / 1E3 + timeStopped);
        return res;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
        running = false;
    }

    public void draw(Graphics g){
        int currentTime = getElapsedTime();
        String minute = Integer.toString(currentTime/60);
        int second = currentTime%60;
        String zero = "0";
        if(second >= 10) zero = "";

        String res = minute + ":" + zero + Integer.toString(second);

        g.setColor(Color.black);
        g.setFont(new Font("Source Code Pro", Font.PLAIN, 30));
        g.drawString(res,700,675);   
    }
}