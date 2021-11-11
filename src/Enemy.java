import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Enemy {
    private int enemyWidth;
    private int enemyHeight;
    private BufferedImage enemyImage;
    private int enemyX, enemyY = 0, velx = 4, vely = 4;
    private boolean dead = false;


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
    }

    public void Move(Dimension dim){
        if (enemyX < 0 || enemyX > dim.width - enemyWidth){
            velx = -velx;
        }
        if (enemyY < 0 || enemyY >  dim.height - enemyHeight){
            vely = -vely;
        }
        enemyX += velx;
        enemyY += vely;
    }

    public int getEnemyX(){
        return enemyX;
    }
    public int getEnemyY(){
        return enemyY;
    }

    public boolean getDeadEnemyStatus(){
        return dead;
    }
    public void setDeadEnemyStatus(boolean deadStatus){
        dead = deadStatus;
    }


}

