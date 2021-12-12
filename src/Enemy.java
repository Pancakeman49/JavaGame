import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Enemy {
    private int enemyWidth;
    private int enemyHeight;
    private BufferedImage enemyImage;
    private int enemyX, enemyY, velx = 4, vely = 4;
    private boolean dead = false;
    private Random random = new Random();
    private int rotation;



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

    public void Paint(Graphics2D g2d){
        //g2d.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
        //g.drawRect(enemyX, enemyY, enemyWidth, enemyHeight);



        if (rotation == 360){
            rotation = 0;
        }
        double rotationRequired = Math.toRadians (rotation);
        rotation++;
        double locationX = enemyImage.getWidth() / 2;
        double locationY = enemyImage.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g2d.drawImage(op.filter(enemyImage, null), enemyX, enemyY, null);
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

