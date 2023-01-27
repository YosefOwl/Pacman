import java.awt.*;

public abstract class DynamicCharacter extends Character {

    protected Point lastPosition;
    protected float speed;
    protected float speedX;
    protected float speedY;

    public DynamicCharacter(float speed, Point position) {
        super(position);
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

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
