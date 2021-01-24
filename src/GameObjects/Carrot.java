package GameObjects;

import GameUserInterface.GameScreen;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Carrot {
    private int x;
    private int y;
    private static Image carrotImage;
    private static Rectangle rect;
    private Bunny bunny;
    private int newY;

    public Carrot(Bunny bunny) {
        x = 1300;
        y = getRandomNumber(20, 500);
        carrotImage = Toolkit.getDefaultToolkit().getImage("images/carrot.png");
        rect = new Rectangle(x, y, 147, 97);
        this.bunny = bunny;
    }

    public void update() {
        if(this.x < 0){
            generateCarrot();
        }
        else{
            this.x--;
            rect.x--;
        }

        //Collision with bunny
        if(rect.intersects(bunny.getRect())){
            generateCarrot();

            //add score
            GameScreen.score += 10;
            //set flag to then show bonus score notification
            GameScreen.bonusScoreAcquired = true;
        }

    }

    private void generateCarrot() {
        newY = getRandomNumber(20, 500);
        this.x = 1300;
        this.y = newY;
        rect.x = 1300;
        rect.y = newY;
    }

    private int getRandomNumber(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    public void draw(Graphics g) {
        g.drawImage(carrotImage, x, y, null);
    }
}
