import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class MouseHandeler extends MouseInputAdapter {

    public boolean mousePressed;
    public Point pressedpyx = new Point();
    public Point pxy = new Point();

    MouseHandeler() {}

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.pressedpyx.move(e.getX(), e.getY());
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
        this.pxy.move(e.getX(), e.getY());
    }
    
    public void resetPress() {
        this.mousePressed = false;
        this.pressedpyx.move(0,0);
    }
}
