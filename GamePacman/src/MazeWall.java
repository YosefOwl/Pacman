import java.awt.*;

public class MazeWall extends Character {

    public MazeWall(int x, int y) {
        super(x, y);
        setStereotype(Stereotype.eWall);
        setActive(true);
        setColor(GameConsts.WALL_COLOR);
    }

    @Override
    public void onCollisionEnter(ICollisional other) {
        Character c = other.getCharacter();

        if (c.getStereotype().equals(Stereotype.eGhost))
            setColor(c.getColor());
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
                new Dimension(GameConsts.BLOCK_WIDTH, GameConsts.BLOCK_HEIGHT));
    }

    @Override
    public void draw (Graphics g) {
        Rectangle wall = getCollider().getBounds();
        g.setColor(color);
        g.drawRect(wall.x, wall.y, wall.width, wall.height);
    }
}
