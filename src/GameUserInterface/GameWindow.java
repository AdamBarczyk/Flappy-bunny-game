package GameUserInterface;

import javax.swing.*;

public class GameWindow extends JFrame{

    public GameScreen gameScreen;

    public GameWindow(){

        super ("Flappy Bunny game created by Adam Barczyk");
        setSize(1024, 640); //1280x960
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);

    }
    public void startGame(){
        gameScreen.startGame();
    }
    public static void main (String args[]){
        GameWindow gw = new GameWindow();
        /*try{
            gw.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("BG.png")))));
        }catch (IOException e){
            System.out.println(e);
        }*/
        gw.setVisible(true);
        gw.startGame();
    }
}
