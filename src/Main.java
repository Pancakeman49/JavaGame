import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Main {
    static Playfield playfield;
    static JPanel startMenu;
    static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setPreferredSize(new Dimension(800,600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);




        StartMenu();
        startMenu.setFocusable(false);

        window.pack();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        AddListeners();






    }

    static void SetPlayFieldDim(){
        playfield.setDim(new Dimension(window.getWidth(), window.getHeight()));
    }
    static void StartMenu(){
        startMenu = new JPanel();

        JLabel label = new JLabel();
        label.setFont(new Font("Comic Sans MS", Font.PLAIN,24));
        label.setText("Start");
        startMenu.add(label);
        label.setLocation(0,40);

        JButton startButton = new JButton();
        startButton.setFocusable(false);
        startButton.setSize(100, 20);
        startButton.setText("Start");
        startMenu.add(startButton);
        startButton.setLocation(40 ,100);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitPlayfield();
            }
        });

        window.add(startMenu);
    }

    static void InitPlayfield(){
        playfield = new Playfield(new Dimension(window.getWidth(), window.getHeight()));
        window.add(playfield);
        window.getContentPane().remove(startMenu);

        window.pack();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        playfield.InitEnemy();

    }

    static void AddListeners(){
        window.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(playfield != null){
                    SetPlayFieldDim();
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("key typed");
                System.out.println(e.getKeyChar());
                if (e.getKeyChar() == 'r'){
                    playfield.EnableEnemies();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("key pressed");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("key released");
            }
        });

    }
}
