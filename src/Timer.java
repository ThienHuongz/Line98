import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Timer {
    private long startTime;
    private long stopTime = 0;
    private long endTime;
    private boolean running;
    private Font font, font2;

    private boolean isGameOver = false;

    public Timer(int time) {
        running = false;
        endTime = time;

        try {

            File fontFile = new File("../assets/digital-7.regular.ttf");

            // Copy the font file to a temporary file
            Path tempFontFile = Files.createTempFile("digital-7", ".ttf");
            Files.copy(fontFile.toPath(), tempFontFile, StandardCopyOption.REPLACE_EXISTING);

            // Load the font from the temporary file
            font = Font.createFont(Font.TRUETYPE_FONT, tempFontFile.toFile()).deriveFont(80f);

            fontFile = new File("../assets/Lobster-Regular.ttf");

            // Copy the font file to a temporary file
            tempFontFile = Files.createTempFile("digital-7", ".ttf");
            Files.copy(fontFile.toPath(), tempFontFile, StandardCopyOption.REPLACE_EXISTING);

            font2 = Font.createFont(Font.TRUETYPE_FONT, tempFontFile.toFile()).deriveFont(70f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
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
        isGameOver = true;
    }

    public int getElapsedTime() {
        int timeStopped = 0;
        if (running == false) {
            timeStopped = (int) ((System.currentTimeMillis() - stopTime) / 1E3);
        }
        int res = (int) (endTime - (System.currentTimeMillis() - startTime) / 1E3 + timeStopped);
        if (res == 0) {
            stop();
        }
        return res;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
        running = false;
    }

    public void draw(Graphics g) {
        int currentTime = getElapsedTime();
        String minute = Integer.toString(currentTime / 60);
        int second = currentTime % 60;
        String zero = "0";
        if (second >= 10)
            zero = "";

        String res = minute + ":" + zero + Integer.toString(second);

        Color color = new Color(255, 242, 211);

        g.setColor(color);
        g.setFont(font2);
        g.drawString("Time", 370, 100);

        g.setFont(font);
        g.drawString(res, 550, 100);

    }

    public boolean isGameOver() {
        return isGameOver;
    }

}