import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.awt.event.*;
import java.util.ArrayList;

public class Ui extends JPanel {

    private ArrayList<BufferedImage> imageList = new ArrayList<>();
    public Image face;
    public Image topPanelImage;
    private int screenH;
    private int screenW;
    private ArrayList<Player> players;
    private MouseHandeler mouse;
    private Game game;
    private final int targetFPS = 60;
    private int fps = 60;
    private String gameState;
    private Player currentHover;
    private double delta;



//#region Constructor
// Initializes the draw thread and calculates game speed and delta time
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

                    double drawInterval = 1000000000/targetFPS;
                    delta = 0;
                    long lastTime = System.nanoTime();
                    long currentTime;
                    long timer = 0;
                    int drawCount = 0;

                while (true) {

                    currentTime = System.nanoTime();

                    delta += (currentTime - lastTime) / drawInterval;
                    timer += (currentTime - lastTime);
                    lastTime = currentTime;

                    if (delta >= 1) {
                        update();   // update the position and image
                        repaint();  // Refresh the display
                        delta--;
                        drawCount++;
                    }

                    if(timer >= 1000000000) {
                        fps = drawCount;
                        drawCount = 0;
                        timer = 0;
                    }

                }
            }
        };
        animationThread.start(); 
    }
//#endregion


//#region Graphics Update
 
    public void update() {

        if (this.players != null) {
            for (int i = this.players.size()-1; i>=0;i--) {
                Player current = this.players.get(i);
                if (playerCollision(current)) {
                    currentHover = current;
                    current.hover();
                } else {
                    current.unhover();
                }
            }
        }

        if (mouse.mousePressed) {
            for (int i = this.players.size()-1; i >= 0; i--) {
                Player current = this.players.get(i);
                if (playerCollision(current)){
                    game.shoot(current, current);
                }
            }
            System.out.println("MouseX: " + mouse.pxy.x +  " MouseY: " + mouse.pxy.y);
            mouse.resetPress();
        }

    }

//#endregion


//#region Graphics paint
//Paints the graphics dependeing on gamestate
//Split up into own functions with links to "game" to update 
    public void paint(Graphics g) {

        clearScreen(g);
        switch(gameState) {
            case "main":
                drawMainGame(g);
                game.gameUpdate();
                break;
            
            case "resultScreen":
                break;
            
            default:
                drawMainMenu(g);
                break;

        }

        
    }
//#endregion

    public void setPlayers(ArrayList<Player> array) { this.players = array;}
    public ArrayList<Player> getPlayers() {return this.players;}
    public void setGameState(String s) {this.gameState = s;}


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
            for (int i = this.players.size()-1; i >= 0; i--) {
                Player current = this.players.get(i);
                current.setPxy(125*i+100, 300);
                current.render(g);
            }
        }
    }

    private void drawMainGame(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        
        drawUi(g);
        g.drawString("FPS: " + fps, 312, 25);
        g.drawString("Mouse x: " + mouse.pxy.x, 512, 25);
        g.drawString("Mouse y: " + mouse.pxy.y, 612, 25);
        uiHover(g);
        g2D.setPaint(Color.BLACK);
        g2D.fillRect(0, screenH-200, screenW, 200);
        g2D.drawImage(face, (screenW/2)-50, screenH-200, null);
        drawPlayers(g2D);
    }

    private void drawMainMenu(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, screenW, screenH);
        g.setColor(Color.white);
        g.drawString("MAIN MENU", screenW/2, screenH/2);
    }

    private void clearScreen(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.WHITE);
        g2D.fillRect(0, 0, screenW, screenH);
    }

    private boolean collision(Point m, Point pp, int osx, int osy){
        if (m.x > pp.x && m.x < pp.x + osx){
            if (m.y > pp.y && m.y < pp.y + osy) {
                return true;
            }
        }
        return false;
    }

    private boolean playerCollision (Player p) {
            if (p.dead()) {return false;}
            if (collision(mouse.pxy, 
                p.getPxy(), 
                p.getSizeX(), 
                p.getSizeY())) 
                {return true;} else {return false;}
    }

    private void uiHover(Graphics g) {
        if (currentHover != null) {
            g.drawString("Player: " + currentHover.getName(), 100, 25);
        }
    }

}
