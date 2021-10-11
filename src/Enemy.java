import javax.imageio.ImageIO;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {
    private int enemyWidth;
    private int enemyHeight;
    private BufferedImage enemyImage;


    public Enemy (int width, int height, BufferedImage image){
        this.enemyHeight = height;
        this.enemyWidth = width;
        this.enemyImage = image;
    }

    public void Paint(Graphics g, int x, int y){
        g.drawImage(enemyImage, x, y, enemyWidth, enemyHeight, null);
    }



}
