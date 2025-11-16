import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Magazine {
    int bulletAmmount;
    int blankNR;
    ArrayList<Bullet> bulletArray = new ArrayList<Bullet>();

    public Magazine(int bulletAmmount, int blankNR) {
        this.bulletAmmount = bulletAmmount;
        this.blankNR =  blankNR;
        
        int blankCount = 0;
        for (int i = 0; i < this.bulletAmmount; i++) {
            if (blankCount < this.blankNR) {
                Bullet newBullet = new Bullet(false);
                bulletArray.add(newBullet);
                blankCount++;
            } else {
                Bullet newBullet = new Bullet(true);
                bulletArray.add(newBullet);
            }
        }

    }

    public ArrayList<Bullet> getMagazine() { return bulletArray;}

    public int getAmountLive() {
        int live = 0;
        for (int i = 0; i < bulletArray.size(); i++) {
            if (bulletArray.get(i).getLive()) {
                live += 1;
            }
        }

        return live;
    }

    public void shuffleMag() {
        Random r1 = new Random();

        for (int i = bulletArray.size() - 1; i >= 1; i--) {

            Collections.swap(bulletArray, i, r1.nextInt(i + 1));
        }
        
        
    }

    public void showBulletArray() {
        Iterator<Bullet> baIterator = bulletArray.iterator();

        while (baIterator.hasNext()) {
            System.out.println("Bullet is: " + baIterator.next().getLive());
        }
    }

    public Bullet getNextRound() {
        Bullet tempBullet = bulletArray.get(0);
        bulletArray.remove(0);
        return tempBullet;
    }

}
