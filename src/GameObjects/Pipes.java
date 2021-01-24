package GameObjects;

import GameUserInterface.GameScreen;
import Util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class Pipes {
    private BufferedImage pipeImage;
    public List <Pipe> pipeList;
    private Bunny bunny;

    public Pipes(Bunny bunny){
        this.bunny = bunny;
        BufferedImage pipeDownLongImage = Resource.getResourceImage("images/pipe_down_long.png");
        BufferedImage pipeDownShortImage = Resource.getResourceImage("images/pipe_down_short.png");
        BufferedImage pipeDownMidImage = Resource.getResourceImage("images/pipe_down_mid.png");
        BufferedImage pipeUpLongImage = Resource.getResourceImage("images/pipe_up_long.png");
        BufferedImage pipeUpShortImage = Resource.getResourceImage("images/pipe_up_short.png");
        BufferedImage pipeUpMidImage = Resource.getResourceImage("images/pipe_up_mid.png");
        pipeList = new ArrayList<Pipe>();

        Pipe newPipe = new Pipe();
        newPipe.X_pos = 300;
        newPipe.Y_pos = 650-330;
        newPipe.image = pipeDownLongImage;
        newPipe.rect = new Rectangle(newPipe.X_pos, newPipe.Y_pos, newPipe.image.getWidth(), newPipe.image.getHeight());
        pipeList.add(newPipe);

        newPipe = new Pipe();
        newPipe.X_pos = 300;
        newPipe.Y_pos = -50;
        newPipe.image = pipeUpShortImage;
        newPipe.rect = new Rectangle(newPipe.X_pos, newPipe.Y_pos, newPipe.image.getWidth(), newPipe.image.getHeight());
        pipeList.add(newPipe);

        newPipe = new Pipe();
        newPipe.X_pos = 700;
        newPipe.Y_pos = 650-120;
        newPipe.image = pipeDownShortImage;
        newPipe.rect = new Rectangle(newPipe.X_pos, newPipe.Y_pos, newPipe.image.getWidth(), newPipe.image.getHeight());
        pipeList.add(newPipe);

        newPipe = new Pipe();
        newPipe.X_pos = 700;
        newPipe.Y_pos = -50;
        newPipe.image = pipeUpLongImage;
        newPipe.rect = new Rectangle(newPipe.X_pos, newPipe.Y_pos, newPipe.image.getWidth(), newPipe.image.getHeight());
        pipeList.add(newPipe);

        newPipe = new Pipe();
        newPipe.X_pos = 1000;
        newPipe.Y_pos = 650-250;
        newPipe.image = pipeDownMidImage;
        newPipe.rect = new Rectangle(newPipe.X_pos, newPipe.Y_pos, newPipe.image.getWidth(), newPipe.image.getHeight());
        pipeList.add(newPipe);

        newPipe = new Pipe();
        newPipe.X_pos = 1000;
        newPipe.Y_pos = -50;
        newPipe.image = pipeUpMidImage;
        newPipe.rect = new Rectangle(newPipe.X_pos, newPipe.Y_pos, newPipe.image.getWidth(), newPipe.image.getHeight());
        pipeList.add(newPipe);
    }

    public void update(){
        for (Pipe pipe : this.pipeList){
            pipe.X_pos --;
            pipe.rect.x --; //update hitbox position
        }
        Pipe firstPipe = pipeList.get(0);
        Pipe secondPipe = pipeList.get(1);

        //Collision between pipes and bunny
        if(firstPipe.rect.intersects(bunny.getRect()) || secondPipe.rect.intersects(bunny.getRect())){
            GameScreen.running = false;
        }
        //Collision with top or bottom of the screen
        else if(bunny.getY() < -40 || bunny.getY()+bunny.getHeight() >= 600){
            GameScreen.running = false;
        }

        //Move pipes from start to end of the screen(when pipes would disappear on the screen)
        if (firstPipe.X_pos + pipeList.get(0).image.getWidth() < 0){
            firstPipe.X_pos = 1024;
            firstPipe.rect.x = 1024;
            pipeList.remove(firstPipe);
            pipeList.add(firstPipe);
            GameScreen.score += 0.5;
        }
    }
    public void drawPipe(Graphics g){
        for(Pipe pipe : this.pipeList) {
            g.drawImage(pipe.image, (int) pipe.X_pos, (int) pipe.Y_pos, null);
            //g.drawRect(pipe.rect.x, pipe.rect.y, pipe.rect.width, pipe.rect.height);
        }
    }
    private class Pipe {
        int X_pos;
        int Y_pos;
        private BufferedImage image;
        private Rectangle rect;
    }
}