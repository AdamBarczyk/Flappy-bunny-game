package GameObjects;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import static GameUserInterface.GameScreen.GRAVITY;
import static GameUserInterface.GameScreen.GROUND;

public class Bunny {
    private static int x;
    private static int y;
    private static int previousY;
    private float speedY;
    private static Image bunnyFallingDownImage;
    private static Image bunnyRisingUpImage;
    public static Rectangle rect;

    public Bunny(){
        x = 15;
        y = 100;
        speedY = 0;
        bunnyFallingDownImage = Toolkit.getDefaultToolkit().getImage("images/BunnyFallingDown.png");
        bunnyRisingUpImage = Toolkit.getDefaultToolkit().getImage("images/BunnyRisingUp.png");
        rect = new Rectangle(x, y+30, 95, 77-30);
    }

    public Rectangle getRect()
    {
        return rect;
    }

    public void update(){
        previousY = y;
        if(y >= GROUND - bunnyFallingDownImage.getHeight(null)){
            speedY = 0;
            y = GROUND - bunnyFallingDownImage.getHeight(null);
        }else {
            speedY += GRAVITY;
            y += speedY;
        }
        rect.y = y+30;
    }
    public void draw(Graphics g){
        if(y < previousY){
            g.drawImage(bunnyRisingUpImage, (int)x, (int)y, null);
        }
        else{
            g.drawImage(bunnyFallingDownImage, (int)x, (int)y, null);
        }
        //g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void jump(){
        speedY = -3;
        y += speedY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        Bunny.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        Bunny.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getWidth() { return bunnyFallingDownImage.getWidth(null); } //Both images have got the same size(px)

    public int getHeight() { return bunnyFallingDownImage.getHeight(null); }

}
