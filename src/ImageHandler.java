import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageHandler {
    
    private File path;
    ArrayList<BufferedImage> images;
    private int counter;

    public ImageHandler(File path) {
        this.path = path;
        this.images = new ArrayList<>();
        this.counter = 0;
        initImages();
    }

    private void initImages() {
        try{
            if (path.isFile()) throw new IOException("Path is not directory.");

            for (File files : path.listFiles()) {
                if (files.isDirectory()) {
                    System.out.println(files + " is a directory. Skipping");
                } else {
                    images.add(ImageIO.read(files));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<BufferedImage> getImages() { return images;}

    public void drawAnimation(Graphics g, int x, int y, int sx, int sy) {
            Graphics2D g2 = (Graphics2D) g;
            BufferedImage img = images.get(counter);
            g2.drawImage(img, x, y, sx, sy, null);
    }

    public void updateAnimation() {
        counter++;
        if (counter > images.size()-1) {counter = 0;}
    }
}


