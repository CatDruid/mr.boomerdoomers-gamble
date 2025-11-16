import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class Ui extends JPanel {

    public Image topPanelImage;
    private int ScreenH;
    private int ScreenW;

    Ui(int ScreenW, int ScreenH) {
        this.ScreenH = ScreenH;
        this.ScreenW = ScreenW;
        topPanelImage = new ImageIcon("ui/toppanel.png").getImage();
        this.setPreferredSize(new Dimension(ScreenW, ScreenH));
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        
        g2D.drawImage(topPanelImage, 0,0, null);

        g2D.fillRect(0, ScreenH-200, ScreenW, 200);

        g2D.setPaint(Color.BLACK);

    }
}
