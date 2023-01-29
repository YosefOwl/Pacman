import java.awt.*;

public abstract class DynamicCharacter extends Character {

    protected Point lastPosition;
    protected float speed;
    protected long nextMoveCounterX = 0;
    protected long nextMoveCounterY = 0;
    protected int dx = 0;
    protected int dy = 0;
    protected int direction;
    protected int lastDirection;

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

    public void accuracyMoveX(int vt) {

        dx = ((GameConsts.LEFT == lastDirection ) ? -vt : vt);

        nextMoveCounterX = nextMoveCounterX - vt ;
        if (nextMoveCounterX < 0) {
            if (GameConsts.LEFT == lastDirection)
                dx = dx + (int) Math.abs(nextMoveCounterX);
            else
                dx = dx - (int) Math.abs(nextMoveCounterX);
            nextMoveCounterX = 0;
        }
    }

    public void accuracyMoveY(int vt) {

        dy = ((GameConsts.UP == lastDirection ) ? -vt : vt);

        nextMoveCounterY = nextMoveCounterY - vt;
        if (nextMoveCounterY < 0) {
            if (GameConsts.UP == lastDirection)
                dy = dy + (int) Math.abs(nextMoveCounterY);
            else
                dy = dy - (int) Math.abs(nextMoveCounterY);
            nextMoveCounterY = 0;
        }
    }
    public void moveOnAxisX(int vt) {
        if (!isDirectionOnAxisX())
            return;

        dx = ((direction == GameConsts.LEFT) ? -vt : vt );
        nextMoveCounterX = GameConsts.BLOCK_WIDTH - nextMoveCounterX - vt;
        lastDirection = direction;
    }

    public void moveOnAxisY(int vt) {

        if (!isDirectionOnAxisY())
            return;

        dy = ( (direction == GameConsts.UP) ? -vt : vt );
        nextMoveCounterY = GameConsts.BLOCK_HEIGHT - nextMoveCounterY - vt;
        lastDirection = direction;
    }

    public boolean isDirectionOnAxisX() {
        return direction == GameConsts.RIGHT || direction == GameConsts.LEFT;
    }

    public boolean isDirectionOnAxisY() {
        return direction == GameConsts.DOWN || direction == GameConsts.UP;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getLastDirection() {
        return lastDirection;
    }
}
