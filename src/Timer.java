public class Timer {
    GamePanel gp;
    private long startTime;
    private long endTime;
    private boolean running;

    public Timer(GamePanel gp) {
        this.gp=gp;
        running = false;
    }

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    public void stop() {
        if (running) {
            endTime = System.currentTimeMillis();
            running = false;
        }
    }

    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        running = false;
    }
}
