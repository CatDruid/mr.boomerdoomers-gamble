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
    private MouseHandeler mouse;
    private Game game;

    public Ui (int screenW, int screenH, Game game) {
        this.screenH = screenH;
        this.screenW = screenW;
        this.game = game;
        topPanelImage = new ImageIcon("ui/toppanel.png").getImage();
        face = new ImageIcon("graphics/VILEG1.png").getImage();
        this.setPreferredSize(new Dimension(screenW, screenH));

        //MainUI file loader
        initImages();
        this.mouse = new MouseHandeler();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        
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

        if (this.players != null) {
            for (int i = this.players.size()-1; i >= 0; i--) {
                if (this.players.get(i).dead() == true) {continue;}
                Player current = this.players.get(i);
                if (collision(mouse.pxy, current.getX(), current.getY(), current.getSizeX(), current.getSizeY())){
                    current.hover();
                } else {current.unhover();}
            }
        }

        if (mouse.mousePressed) {
            for (int i = this.players.size()-1; i >= 0; i--) {
                Player current = this.players.get(i);
                if (current.dead() == true) {continue;}
                if (collision(mouse.pxy, current.getX(), current.getY(), current.getSizeX(), current.getSizeY())){
                    game.shoot(current, current);
                }
            }
            mouse.resetPress();
        }

    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
       
        g2D.setPaint(Color.WHITE);
        g2D.fillRect(0, 0, screenW, screenH);
        
        drawUi(g);
        g.drawString("Mouse x: " + mouse.x, 512, 20);
        g.drawString("Mouse y: " + mouse.y, 612, 20);
        g2D.setPaint(Color.BLACK);
        g2D.fillRect(0, screenH-200, screenW, 200);
        g2D.drawImage(face, (screenW/2)-50, screenH-200, null);
        drawPlayers(g2D);
        



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

    private void drawPlayers(Graphics g) {
        if (this.players != null) {
            for (int i = 0; i < this.players.size(); i++) {
                Player current = this.players.get(i);
                current.setY(300);
                current.setX(125*i+100);
                current.render(g);
            }
        }
    }

    private boolean collision(Point m, int ox, int oy, int osx, int osy){
        if (m.x > ox && m.x < ox + osx){
            if (m.y > oy && m.y < oy + osy) {
                return true;
            }
        }
        return false;
    }

}
