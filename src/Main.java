import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static Playfield playfield;
    static JFrame window;
    static Color color = new Color(168, 168, 168);
    static String name = "";
    static Font mainFont = new Font("Comic Sans MS", Font.PLAIN, 24);
    static String scoreBoardFilePath = System.getProperty("user.home") + File.separator + "ScoreBoard.txt";
    static String separator = "\n";

    public static void main(String[] args) {
        window = new JFrame();
        SetCursor();
        window.setPreferredSize(new Dimension(200,640));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        window.setSize(new Dimension(200,640));
        window.setLocationRelativeTo(null);
        window.setBackground(color);
        window.setResizable(false);


        StartMenu();
        AddListeners();

    }
    static void StartMenu(){
        window.setSize(new Dimension(200,640));
        window.setLocationRelativeTo(null);
        JLabel label = new JLabel();
        label.setFont(new Font("Comic Sans MS", Font.PLAIN,18));
        label.setText("Your Name");
        label.setBounds((window.getWidth()/2) - 54,0,100,30);
        window.add(label);

        JTextField field = new JFormattedTextField();
        field.setBounds((window.getWidth()/2) - 54,35,100,25);
        window.add(field);

        JPanel scoreBoard = new JPanel();
        scoreBoard.setBounds(0,100,window.getWidth() - 16,window.getHeight() - 140);
        scoreBoard.setBorder(BorderFactory.createTitledBorder("Score Board"));

        JTextArea scoreTexts = new JTextArea();
        scoreTexts.setEditable(false);
        scoreTexts.setBounds(5,20,scoreBoard.getWidth() - 10,scoreBoard.getHeight() - 25);



        scoreTexts.setText(ReadNamesFromFile());

        scoreBoard.add(scoreTexts);
        window.add(scoreBoard);

        JButton startButton = new JButton();
        startButton.setFocusable(false);
        startButton.setText("Start Game");
        startButton.setBounds((window.getWidth()/2) - 54,65,100,25);
        window.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = field.getText();

                //remove shit from window window.
                window.getContentPane().remove(label);
                window.getContentPane().remove(field);
                window.getContentPane().remove(startButton);
                window.getContentPane().remove(scoreBoard);
                InitPlayfield();
            }
        });





        window.repaint();
    }
    static void WriteNamesToFile(String Name, int score){
        System.out.println(scoreBoardFilePath);
        try(FileWriter fileWriter = new FileWriter(scoreBoardFilePath, true)) {
            String fileContent = "Name: " + Name + separator + "Score: " + score + separator;
            fileWriter.append(fileContent);
        } catch (IOException a) {
            // Cxception handling
        }
    }
    static String ReadNamesFromFile(){
        String scoreBoardString = "";
        try(FileReader fileReader = new FileReader(scoreBoardFilePath)) {
            int ch = fileReader.read();
            while(ch != -1) {
                scoreBoardString += (char)ch;
                //System.out.print((char)ch);
                ch = fileReader.read();
            }
        } catch (Exception e) {
            // Exception handling
        }


        String[] arrOfScores = scoreBoardString.split(separator);
        String[] arrOfScoresRev = new String[arrOfScores.length];
        int j = arrOfScores.length;

        for (int i = 0; i < arrOfScores.length; i++) {
            arrOfScoresRev[j - 1] = arrOfScores[i];
            j = j - 1;
        }

        String scores = "";
        for (int i = 0; i < arrOfScoresRev.length; i++) {
            scores += arrOfScoresRev[i] + separator;
        }

        System.out.println(scores);
        return scores;

        /*
        String[] arrOfScoresWithNames = scoreBoardString.split(separator);

        for (String string: arrOfScoresWithNames) {
            if(string.matches(".*\\d.*")){ // 1 to infinity

            }
            else { // wrods

            }
        }

         */




    }

    static void InitPlayfield(){
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        playfield = new Playfield(new Dimension(window.getWidth(), window.getHeight()));
        playfield.setBounds(0,0,window.getWidth(),window.getHeight());
        playfield.setBackground(color);
        playfield.setLayout(null);
        window.add(playfield);



        playfield.InitEnemy();
        MainTimer.start();
    }
    static void InitEndPanel(int score){
        window.setSize(new Dimension(200,300));
        window.setLocationRelativeTo(null);
        JButton mainMenu = new JButton();
        WriteNamesToFile(name,score);
        window.setExtendedState(JFrame.NORMAL);

        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setFont(mainFont);
        gameOverLabel.setText("Game Over!");
        gameOverLabel.setBounds(20,0,200,40);

        JLabel gameOverLabel2 = new JLabel();
        gameOverLabel2.setFont(mainFont);
        gameOverLabel2.setText("Your score: " + score);
        gameOverLabel2.setBounds(15,40,200,40);


        JButton tryAgainButton = new JButton();
        tryAgainButton.setFocusable(false);
        tryAgainButton.setText("Try again?");
        tryAgainButton.setBounds(40,80,100,30);
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getContentPane().remove(gameOverLabel);
                window.getContentPane().remove(tryAgainButton);
                window.getContentPane().remove(mainMenu);
                window.getContentPane().remove(gameOverLabel2);
                InitPlayfield();
                window.repaint();
                MainTimer.start();
            }
        });


        mainMenu.setFocusable(false);
        mainMenu.setText("Main Menu");
        mainMenu.setBounds(40,115,100,30);
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getContentPane().remove(gameOverLabel);
                window.getContentPane().remove(tryAgainButton);
                window.getContentPane().remove(mainMenu);
                window.getContentPane().remove(gameOverLabel2);
                window.repaint();
                StartMenu();
            }
        });

        window.add(gameOverLabel2);
        window.add(mainMenu);
        window.add(gameOverLabel);
        window.add(tryAgainButton);



        window.repaint();








    }
    static Timer MainTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int res = playfield.OneSecondTimer();
            if (res == 1){
                EndGame();
            }
        }
    });
    static void EndGame(){
        int score;
        MainTimer.stop();
        window.getContentPane().remove(playfield);
        score = playfield.getScore();
        playfield.Dispose();
        playfield = null;

        InitEndPanel(score);
    }

    static void SetPlayFieldDim(){
        playfield.setDim(new Dimension(window.getWidth(), window.getHeight()));
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
    }
    static void SetCursor(){
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


        window.setCursor(cursor);

    }
}
