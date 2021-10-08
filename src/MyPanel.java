import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MyPanel extends JPanel {
    public int width;
    public int height;
    private BufferedImage image;
    int x, y = 0, velx = 4, vely = 4;


    Timer t = new Timer(5, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (x < 0 || x > width - 226){
                velx = -velx;
            }
            if (y < 0 || y >  height - 226){
                vely = -vely;
            }
            x += velx;
            y += vely;
            repaint();
        }
    });


    MyPanel(){
        this.setPreferredSize(new Dimension(800,600));
        width = 800;
        height = 600;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/5842-che-thonk.png"));
        }catch (IOException e){
            System.out.println("Image missing u dumbass");
            e.printStackTrace();
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image, x,y, image.getWidth()/2, image.getHeight()/2, null);
        g.setFont(new Font("Ariel", Font.PLAIN, 24));
        g.setColor(Color.BLACK);
        g.drawString("X:" + x + " Y: " + y,10,20);
        t.start();
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
