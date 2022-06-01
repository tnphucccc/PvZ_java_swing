package game;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Zombie {
    int x, y, hp, page;
    int state; // 1: move 2: eat 3: dead 4: clear
    SoundAndMusic sound = new SoundAndMusic("music/eat.wav");

    public SoundAndMusic sound() {
        return sound;
    }

    //Zombie constructor
    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
        move();
        hp = 170;
    }

    //Getter and Setter
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getstate() {
        return state;
    }
    public void setHP(int hp) {
        this.hp -= hp;
    }

    //Meet plants
    public boolean meet_plants(Plants[][] plants) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if (plants[i][j] != null && new Rectangle(x + 34, y + 81, 80, 100)
                        .intersects(plants[i][j].x - 10, plants[i][j].y + 81, 70, 70)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Move constructor
    public void move() {
        state = 1;
    }
    public void move_show(Graphics g) {
        g.drawImage(new ImageIcon("image/zombie/zombie"+page+".png")
                .getImage(), x, y, null);
    }
    public void move_animate() {
        if (page == 17) page = 0;
        else page++;
    }
    public void move_action(Plants[][] plants) {
        x -= 1;
        if (meet_plants(plants)) {
            eat();
        }
        if (hp <= 0) {
            dead();
        }
    }

    //Eat constructor
    public void eat() {
        state = 2;
        page = 0;
    }
    public void eat_show(Graphics g) {
        g.drawImage(new ImageIcon("image/zombie/eat"+page+".png")
                .getImage(), x, y, null);
    }
    public void eat_animate() {
        if (page == 17) {
            page = 0;
            sound.playSound("music/eat.wav");
        }
        else page++;
    }
    public void eat_action(Plants[][] plants) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if (plants[i][j] != null && new Rectangle(x + 34, y + 81, 80, 100)
                        .intersects(plants[i][j].x - 10, plants[i][j].y + 81, 70, 70)) {
                    plants[i][j].hp -= 2;
                }
            }
        }
        if (!meet_plants(plants)) {
            move();
        }
        if (hp <= 0) {
            dead();
        }
    }

    //Dead constructor
    public void dead() {
        state = 3;
        page = 0;
    }
    public void dead_show(Graphics g) {
        g.drawImage(new ImageIcon("image/zombie/die"+page+".png")
                .getImage(), x, y, null);
        g.drawImage(new ImageIcon("image/zombie/head"+page+".png")
                .getImage(), x, y, null);
    }
    public void dead_animate() {
        if (page == 11) {
            clear();
        }
        else page++;
    }

    //Clear constructor
    public void clear() {
        state = 4;
    }

    //Iced constructor
    public void iced() {
        if (state == 1) state = 10;
        if (state == 2) state = 20;
    }

    int iced = 0, iced_1 = 0, iced_2 = 0, iced_3 = 0;
    public void iced_move() {
        state = 10;
    }
    public void iced_move_show(Graphics g) {
        g.drawImage(new ImageIcon("image/zombie/zombie"+page+".png")
                .getImage(), x, y, null);
    }
    public void iced_move_animate() {
        if (page == 17) page = 0;
        else {
            if (iced == 0) {
                page++;
                iced++;
                iced%=2;
            } else {
                iced++;
                iced%=2;
            }
        }
    }
    public void iced_move_action(Plants[][] plants) {
        if (iced_1 == 0) {
            x -= 1;
            iced_1++;
            iced_1%=2;
        }
        else {
            iced_1++;
            iced_1%=2;
        }
        if (meet_plants(plants)) {
            iced_eat();
        }
        if (hp <= 0) {
            dead();
        }
    }

    //Iced eat constructor
    public void iced_eat() {
        state = 20;
        page = 0;
    }
    public void iced_eat_show(Graphics g) {
        g.drawImage(new ImageIcon("image/zombie/eat"+page+".png")
                .getImage(), x, y, null);
    }
    public void iced_eat_animate() {
        if (page == 17) {
            page = 0;
            sound.playSound("music/eat.wav");
        }
        else {
            if (iced_2 == 0) {
                page++;
                iced_2++;
                iced_2%=2;
            } else {
                iced_2++;
                iced_2%=2;
            }
        }
    }
    public void iced_eat_action(Plants[][] plants) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if (plants[i][j] != null && new Rectangle(x + 34, y + 81, 80, 100)
                        .intersects(plants[i][j].x - 10, plants[i][j].y + 81, 70, 70)) {
                    if (iced_3 == 0) {
                        plants[i][j].hp -= 1;
                        iced_3++;
                        iced_3%=2;
                    } else {
                        iced_3++;
                        iced_3%=2;
                    }
                }
            }
        }
        if (!meet_plants(plants)) {
            iced_move();
        }
        if (hp <= 0) {
            dead();
        }
    }

    //Show and Action
    public void show(Graphics g) {
        if (state == 1) move_show(g);
        if (state == 2) eat_show(g);
        if (state == 3) dead_show(g);
        if (state == 10) iced_move_show(g);
        if (state == 20) iced_eat_show(g);
    }
    public void action(Plants[][] plants) {
        if (state == 1) {
            move_animate();
            move_action(plants);
        }
        if (state == 2) {
            eat_animate();
            eat_action(plants);
        }
        if (state == 3) dead_animate();
        if (state == 10) {
            iced_move_animate();
            iced_move_action(plants);
        }
        if (state == 20) {
            iced_eat_animate();
            iced_eat_action(plants);
        }
    }
}
