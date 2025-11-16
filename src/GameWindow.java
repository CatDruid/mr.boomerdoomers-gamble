import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
    
    Ui gameui;

    GameWindow(int ScreenW, int ScreenH) {
        
        gameui = new Ui(ScreenW, ScreenH);
        //images
        ImageIcon icon = new ImageIcon("/ui/icon.png");

        this.setIconImage(icon.getImage());

        JLabel l = new JLabel("panel label");

        JPanel p = new JPanel();
        JPanel topBar = new JPanel();
        JPanel bottomBar = new JPanel();
       // JPanel rightBar = new JPanel();
       // JPanel leftBar = new JPanel();


        topBar.setPreferredSize(new Dimension(ScreenW,40));
        topBar.setBackground(Color.black);



        bottomBar.setPreferredSize(new Dimension(ScreenW, 100));
        bottomBar.setBackground(Color.black);

        // setbackground of panel
        p.setBackground(Color.gray);

        // Adding panel to frame

        
        // Setting the size of frame
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(gameui);
        this.pack();
        this.setVisible(true);
    }

}
