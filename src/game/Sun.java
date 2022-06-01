package game;
import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Sun {
    int x, y, timer, endY;
    int state; // 1: uncollected, 2: collected

    public Sun(int x, int y) {
        this.x = x;
        this.y = y;
        state = 1;
    }
    public Sun() {
        state = 1;
        y -= 80;
        x = 80 * new Random().nextInt(9);
        endY = 100 * new Random().nextInt(5);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("image/sun.png").getImage(), 34 + x, 81 + y, null);
    }

    public void drop() {
        if (y < endY) {
            y += 5;
        }
        else {
            timer++;
            if (timer > 50) state = 2;
        }
    }

    boolean ifclicked(int x, int y) {
        if (new Rectangle(34 + this.x, 81 + this.y, 80, 100).contains(x, y)) {
            SoundAndMusic sm = new SoundAndMusic("music/sun.wav");
            sm.playSound("music/sun.wav");
            return true;
        }
        return false;
    }
}