import javax.imageio.ImageIO;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy {
    private int enemyWidth;
    private int enemyHeight;
    private BufferedImage enemyImage;
    private int enemyX, enemyY = 0, velx = 4, vely = 4;


    public Enemy (int width, int height, BufferedImage image, int x, int y){
        this.enemyHeight = height;
        this.enemyWidth = width;
        this.enemyImage = image;
        this.enemyX = x;
        this.enemyY = y;
    }

    public void Paint(Graphics g){
        g.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
    }

    public void Move(Dimension dim){
        if (enemyX < 0 || enemyX > dim.width - 226){
            velx = -velx;
        }
        if (enemyY < 0 || enemyY >  dim.height - 226){
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

}

