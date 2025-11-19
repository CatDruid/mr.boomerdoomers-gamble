
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
//import javax.swing.JLabel;


public class GameWindow extends JFrame {
    
    Ui gameui;
    Game game;
    BufferedImage icon;

    GameWindow(int ScreenW, int ScreenH, Game game) {

        this.game = game;
        gameui = new Ui(ScreenW, ScreenH, game);
        //images
        try {icon = ImageIO.read(new File("ui/icon.png"));}
        catch(IOException e){System.out.println("Error opening image file: " + e.getMessage());}
        
        this.setTitle("MR.BoomerDoomers Gamble");
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gameui);
        this.pack();
        this.setVisible(true);
    }

    public Ui ui() {return this.gameui;}

}
