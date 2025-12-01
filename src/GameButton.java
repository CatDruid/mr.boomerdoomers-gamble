import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GameButton {
    private String id;
    private Point pxy = new Point();
    private Point psxy = new Point();
    private boolean pressed;

    GameButton(String id, int x, int y, int sx, int sy) {
        this.id = id;
        this.pxy.move(x,y);
        this.psxy.move(sx,sy);
    }
    
    public boolean getPressed() {return pressed;}
    public void setPressed(boolean pressed) {this.pressed = pressed;}
    public String getId() {return id;}
    public Point getPxy() {return pxy;}
    public Point getPsxy() {return psxy;}

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect(pxy.x, pxy.y, psxy.x, psxy.y);
        g.setColor(Color.BLACK);
        g.drawString(id, pxy.x + ((psxy.x/2)-(id.length()*3)),  pxy.y + (psxy.y/2));
        g.setColor(Color.gray);
        g.drawRect(pxy.x, pxy.y, psxy.x, psxy.y);
    }
}
