package game;
import java.awt.*;

import javax.swing.ImageIcon;

public class diefield {
    PvZ pvz;
    public diefield(PvZ pvz) {
        this.pvz = pvz;
    }
    void diefield(Graphics g) {
        g.drawImage(new ImageIcon("image/die_1.png")
                .getImage(), 0, 0, 850, 678, null);
    }
    public void mouseclick(int x, int y) {
        if (new Rectangle(0, 0, 850, 678).contains(x, y)) {
            pvz.pvz_die = null;
            pvz.pvz_pre = new preparepanel(pvz);
        }
    }
}
