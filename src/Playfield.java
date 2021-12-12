import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;


public class Playfield extends JPanel {
    private BufferedImage image;
    private Enemy[] enemies = new Enemy[3];
    private Dimension dim;
    private Random random = new Random();
    private int score = 0;
    private int seconds;
    private Clip clip;

    Playfield(Dimension d){
        this.setPreferredSize(d);
        dim = d;

        AddListeners();


    }
    public void Dispose(){
        moveAndPaintEnemy.stop();
        moveAndPaintEnemy = null;
        image = null;
        enemies = null;
        dim = null;
        random = null;
        setCursor(null);
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
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);

        for (Enemy enemy: enemies) {
            if (!enemy.getDeadEnemyStatus()){
                enemy.Paint(g2d);
            }
        }


        //paints x and y for enemy
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + score + " Time: " + seconds,10,20);

        moveAndPaintEnemy.start();
    }

    public void setDim(Dimension d){
        this.dim = d;
    }
    private void PlayShootSound(){
        clip = null;
        try {
            URL url = this.getClass().getClassLoader().getResource("Gun-Shot.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }
        catch (Exception e){

        }
        clip.start();
    }

    public void AddListeners(){

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Point clckPoint = e.getPoint();
                System.out.println("mouse clicked");
                PlayShootSound();
                for (Enemy enemy: enemies) {
                    if (clckPoint.x >= enemy.getEnemyX() && clckPoint.x <= enemy.getEnemyX() + 128){
                        if (clckPoint.y >= enemy.getEnemyY() && clckPoint.y <= enemy.getEnemyY() + 128){
                            System.out.println("Kaching");
                            switch (random.nextInt(1,3)){
                                case 1:
                                    enemy.setEnemyX(-128);
                                    enemy.setEnemyY(random.nextInt(0, dim.height));
                                    break;
                                case 2:
                                    enemy.setEnemyX(dim.width + 128);
                                    enemy.setEnemyY(random.nextInt(0, dim.height));
                                    break;
                            }
                            score++;
                        }
                    }
                }
            }
        });


    }
    public int getScore(){
        return score;
    }

}
        /*
        int i = 0;
        for (Enemy enemy: enemies) {
            i++;
            g.drawString("Enemy" + i + " X:" + enemy.getEnemyX() + " Y: " + enemy.getEnemyY() + " velx: " + enemy.getVelx() + " vely: " + enemy.getVely(),10 + ((i - 1) * 400),20);

        }
         */
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