import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.awt.event.*;
import java.util.ArrayList;

public class Ui extends JPanel {

    private ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
    public Image face;
    public Image topPanelImage;
    private int screenH;
    private int screenW;
    private int frameRate = 60;
    private ArrayList<Player> players;
    private int yaxis;

    public Ui (int screenW, int screenH) {
        this.screenH = screenH;
        this.screenW = screenW;
        topPanelImage = new ImageIcon("ui/toppanel.png").getImage();
        face = new ImageIcon("graphics/VILEG1.png").getImage();
        this.setPreferredSize(new Dimension(screenW, screenH));

        //MainUI file loader
        initImages();


        Thread animationThread = new Thread () {
         @Override
         public void run() {
            while (true) {
               update();   // update the position and image
               repaint();  // Refresh the display
               try {
                  Thread.sleep(1000 / frameRate); // delay and yield to other threads
               } catch (InterruptedException ex) { }
            }
         }
        };
        animationThread.start(); 
    }


    public void update() {
        if (this.yaxis > 500) {
            this.yaxis = 200;
        } else {
        this.yaxis = yaxis + 1;
        }
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
       
        drawUi(g);

        g2D.fillRect(0, screenH-200, screenW, 200);
        g2D.drawImage(face, (screenW/2)-50, screenH-200, null);

        g2D.setPaint(Color.BLACK);

        if (this.players != null) {
            int totalplayers = this.players.size();
            for (int i = 0; i < this.players.size(); i++) {
                g2D.fillRect(((this.screenW-50)/totalplayers)*i + 50, yaxis, 50, 50);
            }
        }

    }

    public void setPlayers(ArrayList<Player> array) { this.players = array;}
    public ArrayList<Player> getPlayers() {return this.players;}


    private void initImages() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            this.imageList.add(ImageIO.read(new File("ui/toppanel.png")));
            //this.imageList.add();
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    private void drawUi(Graphics g) {
        g.drawImage(this.imageList.get(0), 0, 0, null); //top panel
       // g.drawImage(this.imageList.get(1), 0, screenH-200, null); // bottom panel
    }
}
