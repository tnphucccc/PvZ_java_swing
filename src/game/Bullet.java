package game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bullet {
    int x, y;
    int state; // 1: flying 2: disappear

    public Bullet() {}
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        fly();
    }
    private void fly() {
        state = 1;
    }

    public void clear() {
        state = 2;
    }

    private boolean hit(Zombie zom) {
        if (new Rectangle(x, y, 30, 30)
                .intersects(zom.getX(), zom.getY(), 80, 100)) {
            SoundAndMusic sm = new SoundAndMusic("music/peng.wav");
            sm.playSound("music/peng.wav");
            return true;
        }
        return false;
    }

    private void flyimage(Graphics g) {
        g.drawImage(new ImageIcon("image/bullet/bullet.png")
                .getImage(), 58 + x, 80 + y, null);
    }
    private void flyaction(ArrayList<Zombie> zombieList) {
        x += 5;
        for (int i = 0; i < zombieList.size(); i++) {
            if (hit(zombieList.get(i))) {
                zombieList.get(i).setHP(10);
                clear();
            }
        }
        if (x >= 700) clear();
    }

    public void Action(ArrayList<Zombie> zombieList) {
        if (state == 1) flyaction(zombieList);
    }
    public void image(Graphics g) {
        if (state == 1) flyimage(g);
    }
}
