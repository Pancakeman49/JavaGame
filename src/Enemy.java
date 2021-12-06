import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.Random;
import java.util.Timer;


public class Enemy {
    private int enemyWidth;
    private int enemyHeight;
    private BufferedImage enemyImage;
    private int enemyX, enemyY, velx = 4, vely = 4;
    private boolean dead = false;
    private Random random = new Random();



    public Enemy (int width, int height, BufferedImage image, int x, int y){
        Random random = new Random();
        this.enemyHeight = height;
        this.enemyWidth = width;
        this.enemyImage = image;
        this.enemyX = x;
        this.enemyY = y;
        int temp = random.nextInt(-1,1);
        if (temp != 0){
            this.velx *= temp;
        }
        temp = random.nextInt(-1,1);
        if (temp != 0){
            this.vely *= temp;
        }
    }

    public void Paint(Graphics g){
        g.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
        g.drawRect(enemyX, enemyY, enemyWidth, enemyHeight);
    }

    public void Move(Dimension dim){
        if (!dead){
            if (enemyX < 0){
                enemyX += 5;
                velx = -velx;
            }
            else if (enemyX > dim.width - enemyWidth){
                enemyX -= 5;
                velx = -velx;
            }

            if (enemyY < 0){
                enemyY += 5;
                vely = -vely;
            }
            else if (enemyY >  dim.height - enemyHeight){
                enemyY -= 5;
                vely = -vely;
            }
            enemyX += velx;
            enemyY += vely;
        }
    }
    public void IncreaseEnemySpeed(){
        velx *= 1.5;
        vely *= 1.5;
    }



    public int getEnemyX(){
        return enemyX;
    }
    public int getEnemyY(){
        return enemyY;
    }

    public void setEnemyX(int X){
        enemyX = X;
    }
    public void setEnemyY(int Y){
        enemyY = Y;
    }

    public int getVelx(){return velx;}
    public int getVely(){return vely;}

    public boolean getDeadEnemyStatus(){
        return dead;
    }
    public void setDeadEnemyStatus(boolean deadStatus){
        dead = deadStatus;
    }



}

