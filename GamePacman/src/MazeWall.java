import java.awt.*;

public class MazeWall extends Character {

    public MazeWall(int x, int y) {
        super(x, y);
        setStereotype(Stereotype.eWall);
        setActive(true);
    }
    @Override
    public void onCollisionEnter(ICollisional other) {
    }

    @Override
    public Character getCharacter() {
        return this;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Shape getCollider() {

        return new Rectangle(
                this.getPosition(),
                new Dimension( GameConsts.BLOCK_WIDTH, GameConsts.BLOCK_HEIGHT ));
    }

}
