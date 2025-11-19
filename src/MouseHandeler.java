import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class MouseHandeler extends MouseInputAdapter {

    public int x;
    public int y;
    public boolean mousePressed;
    public int pressedX;
    public int pressedY;
    public Point pxy = new Point();

    MouseHandeler() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedX = e.getX();
        pressedY = e.getY();
        mousePressed = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        this.pxy.move(this.x, this.y);
    }
    
    public void resetPress() {
        this.mousePressed = false;
        this.pressedX = 0;
        this.pressedY = 0;
    }
}
