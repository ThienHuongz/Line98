import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class game{
    public static void main(String[] args){
        JFrame panel = new JFrame("The Animal Lines");
        
        panel.setContentPane(new GamePanel());
        // if close window -> stop the program
        panel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setSize(1040, 735);

        // show the window on screen    
        panel.setVisible(true);

        // can not resize window
        panel.setResizable(false);

        // set the location of a Window | null -> center of the component
        panel.setLocationRelativeTo(null);

        // add logo

        panel.setIconImage(new ImageIcon("../assets/icon.png").getImage());
    }
}
