package game;
import java.awt.*;

import javax.swing.ImageIcon;

public class winfield {
    PvZ pvz;
    public winfield(PvZ pvz) {
        this.pvz = pvz;
    }
    void winfield(Graphics g) {
        g.drawImage(new ImageIcon("image/win.png")
                .getImage(), 0, 0, 850, 678, null);
    }
    public void mouseclick(int x, int y) {
        if (new Rectangle(0, 0, 850, 678).contains(x, y)) {
            pvz.pvz_win = null;
            pvz.pvz_pre = new preparepanel(pvz);
        }
    }
}
