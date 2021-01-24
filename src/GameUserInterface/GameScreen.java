package GameUserInterface;

import GameObjects.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.channels.Pipe;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final float GRAVITY = 0.1f;
    public static final int GROUND = 600;
    private final Image background = Toolkit.getDefaultToolkit().getImage("images/background.png");
    private final Image gameOver = Toolkit.getDefaultToolkit().getImage("images/gameOver.png");
    private final Image startAgain = Toolkit.getDefaultToolkit().getImage("images/pressEnterToStartAgain1.png");
    private static Image scoreBonusNotification = Toolkit.getDefaultToolkit().getImage("images/BonusScoreNotification.gif");
    private static int bonusNotificationTimer = 0;
    private Pipes pipe;
    private Bunny bunny;
    private Carrot carrot;
    public static float score;
    private Thread thread;
    public static boolean running = true;
    public static boolean bonusScoreAcquired = false;
    private static final File song = new File("sounds/song.wav");

    public GameScreen(){
        thread = new Thread(this);
        bunny = new Bunny();
        carrot = new Carrot(bunny);
        pipe = new Pipes(bunny);
        score = 0;
    }
    public void startGame(){
        thread.start();
    }
    @Override
    public void run(){
        playMusic();
        while (true){
            try {
                if(running){
                    bunny.update();
                    carrot.update();
                    pipe.update();
                }
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void paint (Graphics g){
        super.paint(g);

        g.drawImage(background, 0, 0, this);

        if(running)
        {
            bunny.draw(g);
            carrot.draw(g);
            pipe.drawPipe(g);
            if(bonusScoreAcquired){
                g.drawImage(scoreBonusNotification, 150, 100, this);
                if(bonusNotificationTimer > 250){
                    bonusScoreAcquired = false;
                    bonusNotificationTimer = 0;
                }
                bonusNotificationTimer++;
            }
        }
        else
        {
            bunny.draw(g);
            carrot.draw(g);
            pipe.drawPipe(g);
            g.drawImage(gameOver, 204, 150, this);
            g.drawImage(startAgain, 150, 300, this);
        }

        //show score
        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 20));
        g.drawString("Score: "  + (int) score, 880, 580);

    }

    private void playMusic(){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-40f);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if(running){
                    bunny.jump();
                }
                break;
            case KeyEvent.VK_ENTER:
                bunny = new Bunny();
                carrot = new Carrot(bunny);
                pipe = new Pipes(bunny);
                running = true;
                score = 0;
        }

    }
    @Override
    public void keyReleased(KeyEvent e){

    }

}
