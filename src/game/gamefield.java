package game;
import java.awt.*;
import java.util.*;

public class gamefield {
    PvZ maingame;
    public gamefield(PvZ maingame) {
        this.maingame = maingame;
    }

    ArrayList<Sun> sunList;
    ArrayList<Zombie> zombieList;
    ArrayList<Bullet> bulletList;
    ArrayList<Ice_Bullet> ice_bulletList;
    ArrayList<Card> cardList;

    background bg;
    Plants[][] plants = new Plants[6][9];
    Plants sunflower;
    Plants peashooter;
    Plants nut;
    Plants ice_peashooter;

    int sunNum = 50;

    int zombiesNum, zombies_randomNum, zombies_deadNum, total_zombiesNum;
    SoundAndMusic BGM;

    void newGame(int zombiesNum, int zombies_randomNum) {
        this.zombiesNum = zombiesNum;
        this.zombies_randomNum = zombies_randomNum;
        zombies_deadNum = 0;
        total_zombiesNum = zombiesNum;
        sunList = new ArrayList<Sun>();
        zombieList = new ArrayList<Zombie>();
        bulletList = new ArrayList<Bullet>();
        ice_bulletList = new ArrayList<Ice_Bullet>();
        cardList = new ArrayList<Card>();

        cardList.add(new Card(0, "sunflower"));
        cardList.add(new Card(1, "peashooter"));
        cardList.add(new Card(2, "nut"));
        cardList.add(new Card(3, "icepeashooter"));

        bg = new background();
        sunflower = new Plants();
        peashooter = new Plants();
        ice_peashooter = new Plants();
        nut = new Plants();
        plants = new Plants[6][9];

        sunNum = 100;
        BGM = new SoundAndMusic("music/BGM.wav");
        BGM.StartPlay_BGM();
    }

    void mouseclick(int mx, int my) {
        for (int i = 0; i < cardList.size(); i++) {
            //==============Sunflower==============
            if(cardList.get(i).cardname.equals("sunflower")
                    && cardList.get(i).canbuyornot == 1) {
                int ah = (my-81)/100;
                int al = (mx-34)/80;

                Plants newsun= new Plants(1, al*80, ah*100);
                if(plants[ah][al] ==null) {
                    plants[ah][al] = newsun;
                    sunNum -= 25;
                    cardList.get(i).canbuyornot=0;
                    sunflower.clear();
                }
            }
            if(cardList.get(i).cardname.equals("sunflower")
                    && cardList.get(i).if_pressed(mx, my)
                    && sunNum >= 25) {
                cardList.get(i).canbuyornot++;
                cardList.get(i).canbuyornot%=2;
                if(sunflower == null) sunflower = new Plants(1, mx, my, 3);
                else sunflower.sunflower_put();
            }

            //==============Peashooter==============
            if(cardList.get(i).cardname.equals("peashooter")
                    && cardList.get(i).canbuyornot == 1) {
                int ah = (my-81)/100;
                int al = (mx-34)/80;

                Plants newpes= new Plants(0, al*80, ah*100);
                if(plants[ah][al] == null) {
                    plants[ah][al] = newpes;
                    sunNum -= 100;
                    cardList.get(i).canbuyornot=0;
                    peashooter.clear();
                }
            }
            if(cardList.get(i).cardname.equals("peashooter")
                    && cardList.get(i).if_pressed(mx, my)
                    && sunNum >= 100) {
                cardList.get(i).canbuyornot++;
                cardList.get(i).canbuyornot%=2;
                if(sunflower == null) peashooter=new Plants(0, mx, my, 3);
                else peashooter.shooter_put();
            }

            //==============Nut==============
            if(cardList.get(i).cardname.equals("nut")
                    && cardList.get(i).canbuyornot == 1) {
                int ah = (my-81)/100;
                int al = (mx-34)/80;

                Plants newnut= new Plants(2, al*80, ah*100);
                if(plants[ah][al] ==null) {
                    plants[ah][al] = newnut;
                    sunNum-=50;
                    cardList.get(i).canbuyornot=0;
                    nut.clear();
                }
            }
            if(cardList.get(i).cardname.equals("nut")
                    && cardList.get(i).if_pressed(mx,my)
                    && sunNum >= 50) {
                cardList.get(i).canbuyornot++;
                cardList.get(i).canbuyornot%=2;
                if(nut==null)nut=new Plants(2, mx, my, 3);
                else nut.nut_put();
            }

            //==============Ice_Peashooter==============
            if(cardList.get(i).cardname.equals("icepeashooter")
                    && cardList.get(i).canbuyornot == 1) {
                int ah = (my-81)/100;
                int al = (mx-34)/80;

                Plants newicepes= new Plants(3, al*80, ah*100);
                if(plants[ah][al] ==null) {
                    plants[ah][al] = newicepes;
                    sunNum -= 150;
                    cardList.get(i).canbuyornot=0;
                    ice_peashooter.clear();
                }
            }
            if(cardList.get(i).cardname.equals("icepeashooter")
                    && cardList.get(i).if_pressed(mx,my)
                    && sunNum >= 150) {
                cardList.get(i).canbuyornot++;
                cardList.get(i).canbuyornot%=2;
                if(ice_peashooter==null) ice_peashooter=new Plants(3, mx, my, 3);
                else ice_peashooter.ice_shooter_put();
            }
        }
        //==============Sun Token==============
        for (int i = 0; i < sunList.size(); i++) {
            if (sunList.get(i).ifclicked(mx, my)) {;
                sunList.remove(i);
                sunNum += 50;
            }
        }

        //========================================
        if (new Rectangle(785, 0, 50, 50).contains(mx, my)) {
            System.exit(0);
        } //exit
        if (new Rectangle(710, 0, 50, 50).contains(mx, my)) {
            maingame.runstate = 1;
        } //pause
        if (new Rectangle(625, 0, 50, 50).contains(mx, my)) {
            maingame.runstate = 0;
        } //resume
    }

    public void mouse_move(int mx, int my) {
        if (sunflower != null && sunflower.state == 3) {
            sunflower.x = mx;
            sunflower.y = my;
        }
        if (peashooter != null && peashooter.state == 3) {
            peashooter.x = mx;
            peashooter.y = my;
        }
        if (nut != null && nut.state == 3) {
            nut.x = mx;
            nut.y = my;
        }
        if (ice_peashooter != null && ice_peashooter.state == 3) {
            ice_peashooter.x = mx;
            ice_peashooter.y = my;
        }
    }

    public void gamepanel(Graphics g) {
        bg.runbackground(g);

        if (sunNum >= 25) {
            cardList.get(0).showinshop_canbuy(g);
        } else {
            cardList.get(0).showinshop_cannotbuy(g);
        }

        if (sunNum >= 100) {
            cardList.get(1).showinshop_canbuy(g);
        } else {
            cardList.get(1).showinshop_cannotbuy(g);
        }

        if (sunNum >= 50) {
            cardList.get(2).showinshop_canbuy(g);
        } else {
            cardList.get(2).showinshop_cannotbuy(g);
        }

        if (sunNum >= 150) {
            cardList.get(3).showinshop_canbuy(g);
        } else {
            cardList.get(3).showinshop_cannotbuy(g);
        }

        //Field
        for (int h = 0; h < 6; h++) {
            for (int l = 0; l < 9; l++) {
                if (plants[h][l] != null) {
                    plants[h][l].show(g);
                }
            }
        }

        //Plants
        if (sunflower != null) {
            sunflower.sunflower_put_show(g);
        }
        if (peashooter != null) {
            peashooter.shooter_put_show(g);
        }
        if (nut != null) {
            nut.nut_put_show(g);
        }
        if (ice_peashooter != null) {
            ice_peashooter.ice_shooter_put_show(g);
        }

        //Zombie
        for (int i = 0; i < zombieList.size(); i++) {
            zombieList.get(i).show(g);
        }

        //Bullet & Ice Bullet
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).image(g);
        }
        for (int i = 0; i < ice_bulletList.size(); i++) {
            ice_bulletList.get(i).image(g);
        }

        //Sun Token
        for (int i = 0; i < sunList.size(); i++) {
            sunList.get(i).paintComponent(g);
        }
        g.setFont(new Font("Serif", Font.BOLD, 17));
        g.drawString("" + sunNum, 38, 77);
    }

    public void timer() throws Exception {
        if (new Random().nextInt(800) < zombies_randomNum && zombiesNum > 0) {
            int x = 800;
            int y = 100 * new Random().nextInt(6);
            Zombie newzombie = new Zombie(x, y);
            zombieList.add(newzombie);
            zombiesNum--;
        }

        for (int i = 0; i <= bulletList.size() - 1; i++) {
            bulletList.get(i).Action(zombieList);
        }
        for (int i = 0; i <= ice_bulletList.size() - 1; i++) {
            ice_bulletList.get(i).Action(zombieList);
        }

        for (int h = 0; h < 6; h++) {
            for (int l = 0; l < 9; l++) {
                if (plants[h][l] != null) {
                    plants[h][l].action(bulletList, ice_bulletList, sunList);
                    if (plants[h][l].state == 2) plants[h][l] = null;
                }
            }
        }

        for (int i = 0; i <= zombieList.size()-1; i++) {
            zombieList.get(i).action(plants);
            if (zombieList.get(i).getstate() == 4) {
                zombieList.remove(i);
                zombies_deadNum++;
            }
        }

        for (int i = 0; i <= zombieList.size()-1; i++) {
            if (zombieList.get(i).getX() < -200) {
                BGM.StopPlay_BGM();

                Thread.sleep(200);
                SoundAndMusic so = new SoundAndMusic("music/laugh.wav");
                so.playSound("music/laugh.wav");

                maingame.pvz_game = null;
                maingame.pvz_die = new diefield(maingame);
            }
        }

        if (zombies_deadNum == total_zombiesNum) {
            BGM.StopPlay_BGM();

            Thread.sleep(1000);
            SoundAndMusic so = new SoundAndMusic("music/winner.wav");
            so.playSound("music/winner.wav");

            maingame.pvz_game = null;
            maingame.pvz_win = new winfield(maingame);
        }

        if (new Random().nextInt(600) < 7) {
            Sun sun = new Sun();
            sunList.add(sun);
        }
        for (int i = 0; i <= sunList.size() - 1; i++) {
            sunList.get(i).drop();
            if (sunList.get(i).state == 2) {
                sunList.remove(i);
            }
        }
    }
}