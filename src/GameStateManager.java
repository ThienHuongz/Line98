import java.awt.Graphics;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameStateBase> gameStates;
    private int currentState;
    public static final int STARTSTATE = 0;
    public static final int MAP = 1;
    public static final int GAMEOVERSTATE = 2;
    public static final int PAUSESTATE = 3;
    // public static final int GAMEWINNERSTATE = 4;

    public GameStateManager(GamePanel pn) {
        gameStates = new ArrayList<GameStateBase>();
        currentState = STARTSTATE;
        gameStates.add(new StartState(pn));
        gameStates.add(GamePlay.getInstance(pn));
        gameStates.add(new GameOverState(pn));
        gameStates.add(new PauseState(pn));

    }

    public void setState(int state) {
        currentState = state;
        // gameStates.get(currentState).init();
    }

    public int getCurrentState() {
        return currentState;
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(Graphics g) {
        gameStates.get(currentState).draw(g);
    }

    public void mouse_click(int mx, int my) {
        gameStates.get(currentState).mouse_click(mx, my);
    }

    public void mouse_move(int mx, int my) {
        gameStates.get(currentState).mouse_move(mx, my);
    }

    public void setScore(int score) {
        ((GameOverState) gameStates.get(GAMEOVERSTATE)).setScore(score);
    }
}
