import java.util.Random;

public class CrazyGhost extends Ghost {

    public CrazyGhost(float speed, float x, float y) {
        super(speed, x, y);
    }

    @Override
    public void move(long deltaTime) {

        float lastX = x;
        float lastY = y;

        x = x + speedX*deltaTime;
        y = y + speedY*deltaTime;

        Random rand = new Random();

        int randDir = rand.nextInt(100);
        /*
        if(!Maze.collision(dimension, dimension, x, y)) {
            x = lastX;
            y = lastY;

            changeDirection(randDir);
        }
        else {
            changeDirection(randDir);
        }

         */
    }

}
