package game;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Card {
    int canbuyornot, cardnumber;
    String cardname;

    public Card(int cardnumber, String cardname) {
        this.cardnumber = cardnumber;
        this.cardname = cardname;
        canbuyornot = 0;
    }

    public void showinshop_canbuy(Graphics g) {
        g.drawImage(new ImageIcon("image/card/"+cardname+"canbuy.png")
                .getImage(), 95 + 54 * cardnumber, 8, null);
    }

    public void showinshop_cannotbuy(Graphics g) {
        g.drawImage(new ImageIcon("image/card/"+cardname+"cannotbuy.png")
                .getImage(), 95 + 54 * cardnumber, 8, null);
    }

    public boolean if_pressed(int x, int y) {
        if (new Rectangle(95 + 54 * cardnumber, 8, 50, 70).contains(x, y)) {
            return true;
        }
        return false;
    }
}
