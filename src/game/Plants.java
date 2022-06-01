package game;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Plants {
    int x, y, page, hp, timer, state;
    int type; // 0: shooter, 1: sunflower, 2: nut, 3: ice_shooter

    public Plants() {
    }
    public Plants(int x, int y) {
        hp = 200;
        this.x = x;
        this.y = y;
        shooter_move();
    }
    public Plants(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        if (type == 0) {
            shooter_move();
        }
        if (type == 1) {
            sunflower_move();
        }
        if (type == 2) {
            nut_health();
        }
        if (type == 3) {
            ice_shooter_move();
        }
    }
    public Plants(int type, int x, int y, int state) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.state = state;
        if (type == 0) {
            shooter_put();
        }
        if (type == 1) {
            sunflower_put();
        }
        if (type == 2) {
            nut_put();
        }
        if (type == 3) {
            ice_shooter_put();
        }
    }

    //Remove plants from the field
    public void clear() {
        state = 2;
    }

    //Peashooter timer
    public void shooter_timer() {
        timer++;
    }
    public boolean shooter_time_over() {
        return timer > 17;
    }

    //Peashooter animation
    public void shooter_move() {
        hp = 170;
        state = 1;
    }
    public void shooter_move_show(Graphics g) {
        g.drawImage(new ImageIcon("image/plant/shooter/shooter" + page + ".png")
                .getImage(), 34 + x, 81 + y, null);
    }
    public void shooter_move_animate(ArrayList<Bullet> bulletList) {
        if (page == 12) page = 0;
        else page++;
        if (hp <= 0) clear(); //Remove the plant
        shooter_timer(); //Increase the timer
        if (shooter_time_over()) {
            bulletList.add(new Bullet(x, y)); //Add bullet
            timer = 0; //Reset timer
        }
    }

    //Peashooter put
    public void shooter_put() {
        state = 3;
    }
    public void shooter_put_show(Graphics g) {
        if (state == 3) {
            g.drawImage(new ImageIcon("image/plant/shooter/shooter0.png")
                    .getImage(), x, y, null);
            g.drawImage(new ImageIcon("image/plant/shooter/shooter.png")
                    .getImage(), 34 + ((x - 34) / 80) * 80, 81 + ((y - 81) / 100) * 100, null);
        }
    }

    //Sunflower timer
    public void sunflower_timer() {
        timer++;
    }
    public boolean sunflower_timer_over() {
        return timer > 83;
    }

    //Sunflower animation
    public void sunflower_move() {
        hp = 170;
        state = 1;
    }
    public void sunflower_move_show(Graphics g) {
        g.drawImage(new ImageIcon("image/plant/sunflower/sunflower" + page + ".png")
                .getImage(), 34 + x, 81 + y, null);
    }
    public void sunflower_move_animate(ArrayList<Sun> sunList) {
        if (page == 17) page = 0;
        else page++;
        if (hp == 0) clear();
        sunflower_timer();
        if (sunflower_timer_over()) {
            sunList.add(new Sun(x, y));
            timer = 0;
        }
    }

    //Sunflower put
    public void sunflower_put() {
        state = 3;
    }
    public void sunflower_put_show(Graphics g) {
        if (state == 3) {
            g.drawImage(new ImageIcon("image/plant/sunflower/sunflower0.png")
                    .getImage(), x, y, null);
            g.drawImage(new ImageIcon("image/plant/sunflower/sunflower.png")
                    .getImage(), 34 + ((x - 34) / 80) * 80, 81 + ((y - 81) / 100) * 100, null);
        }
    }

    //Nut animation
    public void nut_health() {
        hp = 390;
        state = 1;
    }
    private void nut_health_show(Graphics g) {
        g.drawImage(new ImageIcon("image/plant/nut/nut" + page + ".png")
                .getImage(), 34 + x, 81 + y, null);
    }
    private void nut_health_move() {
        if (page == 12) page = 0;
        else page++;
        if (hp <= 260) nut_hurts();
    }
    private void nut_hurts() {
        state = 4;
        page = 0;
    }
    private void nut_hurts_show(Graphics g) {
        g.drawImage(new ImageIcon("image/plant/nut/nuthurts" + page + ".png")
                .getImage(), 34 + x, 81 + y, null);
    }
    private void nut_hurts_move() {
        if (page == 10) page = 0;
        else page++;
        if (hp <= 130) nut_badlyhurts();
    }
    private void nut_badlyhurts() {
        state = 5;
        page = 0;
    }
    private void nut_badlyhurts_show(Graphics g) {
        g.drawImage(new ImageIcon("image/plant/nut/nutbadlyhurts" + page + ".png")
                .getImage(), 34 + x, 81 + y, null);
    }
    private void nut_badlyhurts_move() {
        if (page == 14) page = 0;
        else page++;
        if (hp <= 0) clear();
    }

    //Nut put
    public void nut_put() {
        state = 3;
    }
    public void nut_put_show(Graphics g) {
        if (state == 3) {
            g.drawImage(new ImageIcon("image/plant/nut/nut0.png")
                    .getImage(), x, y, null);
            g.drawImage(new ImageIcon("image/plant/nut/nut.png")
                    .getImage(), 34 + ((x - 34) / 80) * 80, 81 + ((y - 81) / 100) * 100, null);
        }
    }

    void nut_show(Graphics g) {
        if (state == 1) nut_health_show(g);
        if (state == 4) nut_hurts_show(g);
        if (state == 5) nut_badlyhurts_show(g);
    }

    public void nut_action() {
        if (state == 1) nut_health_move();
        if (state == 4) nut_hurts_move();
        if (state == 5) nut_badlyhurts_move();
    }

    //Ice shooter animation
    public void ice_shooter_move() {
        hp = 170;
        state = 1;
    }
    public void ice_shooter_move_show(Graphics g) {
        g.drawImage(new ImageIcon("image/plant/iceshooter/iceshooter" + page + ".png")
                .getImage(), 34 + x, 81 + y, null);
    }
    public void ice_shooter_move_animate(ArrayList<Ice_Bullet> ice_bulletList) {
        if (page == 14) page = 0;
        else page++;
        if (hp <= 0) clear();
        shooter_timer();
        if (shooter_time_over()) {
            ice_bulletList.add(new Ice_Bullet(x, y));
            timer = 0;
        }
    }

    //Ice shooter put
    public void ice_shooter_put() {
        state = 3;
    }
    public void ice_shooter_put_show(Graphics g) {
        if (state == 3) {
            g.drawImage(new ImageIcon("image/plant/iceshooter/iceshooter0.png")
                    .getImage(), x, y, null);
            g.drawImage(new ImageIcon("image/plant/iceshooter/iceshooter.png")
                    .getImage(), 34 + ((x - 34) / 80) * 80, 81 + ((y - 81) / 100) * 100, null);
        }
    }

    //Put disappear
    public void put_disappear() { state = 2; }
    public void put() { state = 3; }
    public void show(Graphics g) {
        if (type == 0) shooter_move_show(g);
        if (type == 1) sunflower_move_show(g);
        if (type == 2) nut_show(g);
        if (type == 3) ice_shooter_move_show(g);
    }

    public void action(ArrayList<Bullet> bulletList, ArrayList<Ice_Bullet> ice_bulletList, ArrayList<Sun> sunList) {
        if (type == 0) shooter_move_animate(bulletList);
        if (type == 1) sunflower_move_animate(sunList);
        if (type == 2) nut_action();
        if (type == 3) ice_shooter_move_animate(ice_bulletList);
    }
}