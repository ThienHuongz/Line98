
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {

    private static Clip clipBGM, clip;
    private static File soundFile[] = new File[10];
    static {
        new SoundEffect();  
    }

    public SoundEffect() {
        try {
            soundFile[0] = new File("../assets/sound/BGM.wav");
            soundFile[1] = new File("../assets/sound/game-start-6104.wav");
            soundFile[2] = new File("../assets/sound/select-sound-121244.wav");
            soundFile[3] = new File("../assets/sound/BeginSound.wav");
            soundFile[4] = new File("../assets/sound/PauseSound.wav");
            soundFile[5] = new File("../assets/sound/GameOverSound.wav");
            soundFile[6] = new File("../assets/sound/mouse-click-sound.wav");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void play(int i) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {

        }
    }

    public static void playBGM(int i) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile[i]);
            clipBGM = AudioSystem.getClip();
            clipBGM.open(audioStream);
            clipBGM.start();
            clipBGM.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {

        }
    }

    public void pause() {
        // if (clip != null && clip.isRunning()) {
        clip.stop();
        // }
    }

    public static void StopBGM() {
        if (clipBGM != null) {
            clipBGM.stop();
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
