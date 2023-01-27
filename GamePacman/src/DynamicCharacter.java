import java.awt.*;

public abstract class DynamicCharacter extends Character {

    protected Point lastPosition;
    protected float speed;

    public DynamicCharacter(float speed, int x, int y) {
        super(x, y);
        setSpeed(speed);
    }

    public void move(long deltaTime) {
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Point getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Point lastPosition) {
        this.lastPosition = lastPosition;
    }

}
