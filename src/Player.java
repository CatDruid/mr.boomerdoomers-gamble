import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private boolean ai;
    private boolean dead = false;
    private ArrayList<BufferedImage> avatar;
    private Point pxy = new Point();
    private int sizeX = 100;
    private int sizeY = 100;
    private boolean hover;

//#region Constructors
//Overloaded for seperate usecases
    public Player(String name, int x, int y, boolean ai, ArrayList<BufferedImage> avatar) {
        this.avatar = avatar;
        this.pxy.move(x, y);
        this.avatar = avatar;
        this.name = name;
        this.health = 3;
        this.ai = ai;
        this.dead = false;
    }

    public Player(String name, int x, int y, boolean ai) {
        this.pxy.move(x, y);
        this.name = name;
        this.health = 3;
        this.ai = ai;
        this.dead = false;
    }

    public Player(String name, boolean ai) {
        this.pxy.move(0,0);
        this.name = name;
        this.health = 3;
        this.ai = ai;
        this.dead = false;
    }

    public Player(String name, int x, int y) {
        this.pxy.move(x, y);
        this.name = name;
        this.health = 3;
        this.ai = false;
        this.dead = false;
    }

     public Player(String name) {
        this.pxy.move(0,0);
        this.name = name;
        this.health = 3;
        this.ai = false;
        this.dead = false;
    }
//#endregion

//#region Get/Set
    public String getName() {return name;}
    public int getHealth() {return health;}
    public boolean getAi() {return ai;}
    public Point getPxy() {return pxy;}
    public void setPxy(int x, int y) {pxy.move(x, y);}
    public int getSizeX() {return sizeX;}
    public int getSizeY() {return sizeY;}
    public void setSizeX(int x) {this.sizeX = x;}
    public void setSizeY(int y) {this.sizeY = y;}
    public void hover() {this.hover = true;}
    public void unhover() {this.hover = false;}
    public boolean dead() {return dead;}
//#endregion


    public void damage(int amount) {
        System.out.println("BANG*** " + this.name + " got shot in the head");
        health -= amount;
        if (health <= 0) { 
            System.out.println(this.name + " has died for the last time!");
            this.dead = true;
        }
    }



    public void heal(int amount) {
        health += amount;
    }

//#region Render
    public void render(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        if (this.avatar != null){
            if (hover){
                switch(this.health){
                case 3:
                    g.drawImage(this.avatar.get(4).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;
                case 2:
                    g.drawImage(this.avatar.get(5).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;
                case 1:
                    g.drawImage(this.avatar.get(6).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;
                default:
                    g.drawImage(this.avatar.get(0).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;                    
                }
            } else {
                switch(this.health){
                case 3:
                    g.drawImage(this.avatar.get(1).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;
                case 2:
                    g.drawImage(this.avatar.get(2).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;
                case 1:
                    g.drawImage(this.avatar.get(3).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;
                default:
                    g.drawImage(this.avatar.get(0).getScaledInstance(sizeY, sizeX, Image.SCALE_DEFAULT), pxy.x, pxy.y, null);
                    break;                    
                }
            }
        } else {
            if (hover) {
                g.setColor(Color.GREEN);
                g.fillRect(pxy.x, pxy.y, sizeX, sizeY);
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
                g.fillRect(pxy.x, pxy.y, sizeX, sizeY);
                g.setColor(Color.WHITE);
            }
        }
    }
//#endregion

}
