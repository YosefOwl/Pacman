import java.awt.*;

public class Coin extends Character {


	private int coinVal = 1;

	public Coin(int x, int y) {
		super(x, y);
		setDimension(new Dimension(GameConsts.COIN_DIMENSION, GameConsts.COIN_DIMENSION));
		setActive(true);
		setStereotype(Stereotype.eCoin);
		setColor(Color.ORANGE);
	}

	public int getCoinVal() {
		return coinVal;
	}

	public void setCoinVal(int value) {
		coinVal = value;
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

		g.drawOval(position.x, position.y, dimension.width, dimension.height);
		g.fillOval(position.x, position.y, dimension.width, dimension.height);
	}

}
