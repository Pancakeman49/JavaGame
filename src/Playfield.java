import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
/*
TODO
Move the moving logic to enemy class
Make it so that i can just init multiple enemy's
 */




public class Playfield extends JPanel {
    private BufferedImage image;
    int x, y = 0, velx = 4, vely = 4;
    private Enemy enemy;
    private Dimension dim;
    private Timer t = new Timer(5, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (x < 0 || x > dim.width - 226){
                velx = -velx;
            }
            if (y < 0 || y >  dim.height - 226){
                vely = -vely;
            }
            x += velx;
            y += vely;
            repaint();
        }
    });


    Playfield(Dimension d){
        this.setPreferredSize(d);
        dim = d;
        InitEnemy();
    }

    private void InitEnemy(){
        //gets image from res folder and sets it in BufferedImage
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/5842-che-thonk.png"));
        }catch (IOException e){
            System.out.println("Image missing u dumbass");
            e.printStackTrace();
        }

        //inits enemy with all the required vars
        enemy = new Enemy(226, 226, image);
    }

    public void paint(Graphics g){
        super.paint(g);
        enemy.Paint(g,x,y);
        g.setFont(new Font("Ariel", Font.PLAIN, 24));
        g.setColor(Color.BLACK);
        g.drawString("X:" + x + " Y: " + y,10,20);
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