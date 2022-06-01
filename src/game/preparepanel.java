package game;
import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class preparepanel {
    PvZ pvz;
    SoundAndMusic BGM = new SoundAndMusic("music/gaming.wav");

    public preparepanel(PvZ pvz) {
        this.pvz = pvz;
        BGM.StartPlay_BGM();
    }

    void preparepanel(Graphics g) {
        g.drawImage(new ImageIcon("image/start.png")
                .getImage(), 0, 0, null);
    }

    public void mouseclick(int mx, int my) {
        if(new Rectangle(300,600,244,42).contains(mx, my)) {
            BGM.StopPlay_BGM();
            SoundAndMusic a=new SoundAndMusic("music/laugh.wav");
            a.playSound("music/laugh.wav");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
            pvz.pvz_pre=null;
            pvz.pvz_game=new gamefield(pvz);
            pvz.pvz_game.newGame(10 + new Random().nextInt(15), 5 + new Random().nextInt(10));
        }
        if(new Rectangle(785,0,50,50).contains(mx, my)) {
            System.exit(0);
        }
    }
}