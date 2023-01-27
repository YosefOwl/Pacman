import java.awt.*;
import java.io.Console;

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
        try
        {
            return new Rectangle(
                    this.getPosition(),
                    new Dimension((int)GameConsts.BLOCK_WIDTH,(int)GameConsts.BLOCK_HEIGHT));

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean HasBound() {
        return true;
    }
}
