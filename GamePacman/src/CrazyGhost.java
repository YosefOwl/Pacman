import java.awt.*;
import java.util.Random;

public class CrazyGhost extends Ghost {

    public CrazyGhost(float speed, Point position) {
        super(speed, position);
    }

    @Override
    public void move(long deltaTime) {

        position.x = (int)(position.x + speedX*deltaTime);
        position.y = (int)(position.y + speedY*deltaTime);

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
