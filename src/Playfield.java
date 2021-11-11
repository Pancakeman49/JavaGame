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

    private Timer t = new Timer(5, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            enemies[0].Move(dim);
            enemies[1].Move(dim);
            enemies[2].Move(dim);
            repaint();
        }
    });


    Playfield(Dimension d){
        this.setPreferredSize(d);
        dim = d;

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
                            enemy.setDeadEnemyStatus(true);
                        }
                    }
                }
            }
        });



    }
    public void EnableEnemies(){
        for (Enemy enemy:enemies) {
            enemy.setDeadEnemyStatus(false);
        }
    }

    public void InitEnemy(){
        //gets image from res folder and sets it in BufferedImage
        try {
            image = ImageIO.read(new File("res/5842-che-thonk.png"));
        }catch (IOException e){
            System.out.println("Image missing u dumbass");
            e.printStackTrace();
        }

        Random random = new Random();
        //inits enemy with all the required vars

        enemies[0] = new Enemy(226, 226, image, random.nextInt(0, dim.width - 226),random.nextInt(0, dim.height - 226));
        enemies[1] = new Enemy(226, 226, image, random.nextInt(0, dim.width - 226),random.nextInt(0, dim.height - 226));
        enemies[2] = new Enemy(226, 226, image, random.nextInt(0, dim.width - 226),random.nextInt(0, dim.height - 226));
    }

    public void paint(Graphics g){
        super.paint(g);
        for (Enemy enemy: enemies) {
            if (!enemy.getDeadEnemyStatus()){
                enemy.Paint(g);
            }
        }



        //paints x and y for enemy
        g.setFont(new Font("Ariel", Font.PLAIN, 24));
        g.setColor(Color.BLACK);
        int i = 0;
        for (Enemy enemy: enemies) {
            i++;
            g.drawString("X:" + enemy.getEnemyX() + " Y: " + enemy.getEnemyY(),10 + (i * 200),20);
        }
        t.start();
    }

    public void setDim(Dimension d){
        this.dim = d;
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
