
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JLabel;


public class GameWindow extends JFrame {
    
    Ui gameui;
    Game game;

    GameWindow(int ScreenW, int ScreenH, Game game) {

        this.game = game;
        gameui = new Ui(ScreenW, ScreenH, game);
        //images
        ImageIcon icon = new ImageIcon("/ui/icon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gameui);
        this.pack();
        this.setVisible(true);
    }

    public Ui ui() {return this.gameui;}

}
