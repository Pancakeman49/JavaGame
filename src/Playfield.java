import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Playfield extends JPanel {
    private BufferedImage image;
    private Enemy[] enemies = new Enemy[3];
    private Dimension dim;
    private Random random = new Random();
    private int score = 0;
    private int seconds;

    Playfield(Dimension d){
        this.setPreferredSize(d);
        dim = d;

        AddListeners();
        SetCursor();


    }

    private Timer moveAndPaintEnemy = new Timer(5, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Enemy enemy : enemies) {
                enemy.Move(dim);
            }
            repaint();
        }
    });

    public int OneSecondTimer(){
        seconds += 1;
        if(seconds % 10 == 0){
            for (Enemy enemy : enemies) {
                enemy.IncreaseEnemySpeed();
            }
        }
        if (seconds == 60){
            return 1;
        }
        return 0;
    }

    public void EnableEnemies(){
        for (Enemy enemy:enemies) {
            enemy.setDeadEnemyStatus(false);
        }
    }

    public void InitEnemy(){
        //gets image from res folder and sets it in BufferedImage
        try {
            image = ImageIO.read(new File("res/Enemy.png"));
        }catch (IOException e){
            System.out.println("Image missing u dumbass");
            e.printStackTrace();
        }


        //inits enemy with all the required vars

        enemies[0] = new Enemy(128, 128, image, random.nextInt(0, dim.width - 128),random.nextInt(0, dim.height - 128));
        enemies[1] = new Enemy(128, 128, image, random.nextInt(0, dim.width - 128),random.nextInt(0, dim.height - 128));
        enemies[2] = new Enemy(128, 128, image, random.nextInt(0, dim.width - 128),random.nextInt(0, dim.height - 128));
    }

    public void paint(Graphics g){
        super.paint(g);
        for (Enemy enemy: enemies) {
            if (!enemy.getDeadEnemyStatus()){
                enemy.Paint(g);
            }


        }



        //paints x and y for enemy
        g.setFont(new Font("Ariel", Font.PLAIN, 18));
        g.setColor(Color.BLACK);
        int i = 0;
        for (Enemy enemy: enemies) {
            i++;
            g.drawString("Enemy" + i + " X:" + enemy.getEnemyX() + " Y: " + enemy.getEnemyY() + " velx: " + enemy.getVelx() + " vely: " + enemy.getVely(),10 + ((i - 1) * 400),20);
            g.drawString("Score:" + score + " Time: " + seconds,10,40);
        }
        moveAndPaintEnemy.start();
    }

    public void setDim(Dimension d){
        this.dim = d;
    }

    public void AddListeners(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Point clckPoint = e.getPoint();
                System.out.println("mouse clicked");
                for (Enemy enemy: enemies) {
                    if (clckPoint.x >= enemy.getEnemyX() && clckPoint.x <= enemy.getEnemyX() + 226){
                        if (clckPoint.y >= enemy.getEnemyY() && clckPoint.y <= enemy.getEnemyY() + 226){
                            System.out.println("Kaching");
                            enemy.setEnemyX(random.nextInt(0, dim.width - 226));
                            enemy.setEnemyY(random.nextInt(0, dim.height - 226));
                            score++;
                        }
                    }
                }
            }
        });

//dont need this for now but maybe will need for when i add scoreboard
/*

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point mouseLocation = e.getPoint();
                if (mouseLocation.x <= dim.width && mouseLocation.y <= dim.height){
                    SetCursor();
                }
            }
        });

 */

    }
    public void SetCursor(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image curImage = null;
        try {
            curImage = ImageIO.read(new File("res/Cursor.png"));
        }catch (IOException e){
            System.out.println("Image missing u dumbass");
            e.printStackTrace();
        }
        Dimension bestSize = toolkit.getBestCursorSize(0,0);

        Cursor cursor = toolkit.createCustomCursor(curImage, new Point(bestSize.width / 2, bestSize.height / 2),"img");


        this.setCursor(cursor);

    }







    /*
    public void paint(Graphics g){

        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(5));
        g2D.setPaint(Color.blue);
        //g2D.drawLine(10,10, 500, 500);
        g2D.drawRect(0,0, 200, 200);
        g2D.fillRect(0,0, 200, 200);
        g2D.drawImage(image, 100,100, image.getWidth()/2, image.getHeight()/2, null);

    }
     */
}
