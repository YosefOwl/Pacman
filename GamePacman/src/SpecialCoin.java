import java.awt.*;

public class SpecialCoin extends Coin {
    public SpecialCoin(int x, int y) {
        super(x, y);
        setDimension(new Dimension(GameConsts.S_COIN_DIMENSION , GameConsts.S_COIN_DIMENSION ));
        setActive(true);
        setStereotype(Stereotype.eSpecCoin);
        setColor(GameConsts.S_COIN_COLOR);
        setCoinVal(GameConsts.S_COIN_VALUE);
    }

    @Override
    public void onCollisionEnter(ICollisional other) {
        if (other.getCharacter().getStereotype().equals(Stereotype.ePacman)) {
            this.setActive(false);
        }
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
                this.position,
                new Dimension(dimension.width + 2, dimension.height + 2));
    }

    public void draw(Graphics g) {

        g.setColor(this.color);

        g.drawRect(position.x, position.y, dimension.width, dimension.height);
        g.fillRect(position.x, position.y, dimension.width, dimension.height);
    }
}