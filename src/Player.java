import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private boolean ai;
    private boolean dead = false;
    private ArrayList<Image> avatar;
    private int x;
    private int y;
    private int sizeX = 100;
    private int sizeY = 100;
    private boolean hover;

    public Player(String name, int x, int y, boolean ai, ArrayList<Image> avatar) {
        this.avatar = avatar;
        this.x = x;
        this.y = y;
        this.avatar = avatar;
        this.name = name;
        this.health = 3;
        this.ai = ai;
        this.dead = false;
    }

    public Player(String name, int x, int y, boolean ai) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.health = 3;
        this.ai = ai;
        this.dead = false;
    }

    public Player(String name, boolean ai) {
        this.x = 0;
        this.y = 0;
        this.name = name;
        this.health = 3;
        this.ai = ai;
        this.dead = false;
    }

    public Player(String name, int x, int y) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.health = 3;
        this.ai = false;
        this.dead = false;
    }

     public Player(String name) {
        this.x = 0;
        this.y = 0;
        this.name = name;
        this.health = 3;
        this.ai = false;
        this.dead = false;
    }

    public String getName() {return name;}
    public int getHealth() {return health;}
    public boolean getAi() {return ai;}
    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getSizeX() {return sizeX;}
    public int getSizeY() {return sizeY;}
    public void setSizeX(int x) {this.sizeX = x;}
    public void setSizeY(int y) {this.sizeY = y;}
    public void hover() {this.hover = true;}
    public void unhover() {this.hover = false;}
    public boolean dead() {return dead;}

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

    public void render(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        if (this.avatar != null){
            if (hover){
                switch(this.health){
                case 3:
                    g.drawImage(this.avatar.get(4), x, y, null);
                    break;
                case 2:
                    g.drawImage(this.avatar.get(5), x, y, null);
                    break;
                case 1:
                    g.drawImage(this.avatar.get(6), x, y, null);
                    break;
                default:
                    g.drawImage(this.avatar.get(0), x, y, null);
                    break;                    
                }
            } else {
                switch(this.health){
                case 3:
                    g.drawImage(this.avatar.get(1), x, y, null);
                    break;
                case 2:
                    g.drawImage(this.avatar.get(2), x, y, null);
                    break;
                case 1:
                    g.drawImage(this.avatar.get(3), x, y, null);
                    break;
                default:
                    g.drawImage(this.avatar.get(0), x, y, null);
                    break;                    
                }
            }
        } else {
            if (hover) {
                g.setColor(Color.GREEN);
                g.fillRect(x, y, sizeX, sizeY);
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
                g.fillRect(x, y, sizeX, sizeY);
                g.setColor(Color.WHITE);
            }
        }
    }


}
