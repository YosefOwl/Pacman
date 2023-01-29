import java.awt.*;

public class Coin extends Character {

	static final int DIMENSION = 4;
	
	public Coin(int x, int y) {
		super(x, y);
		setDimension(new Dimension(DIMENSION,DIMENSION));
		setActive(true);
		setStereotype(Stereotype.eCoin);
	}

	@Override
	public void onCollisionEnter(ICollisional other) {
		if(other.getCharacter().getStereotype().equals(Stereotype.ePacman)) {
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
				new Dimension(DIMENSION + 2,DIMENSION + 2));
	}

	public void draw(Graphics g) {

		g.setColor(Color.ORANGE);

		g.drawOval(position.x, position.y, dimension.width, dimension.height);
		g.fillOval(position.x, position.y, dimension.width, dimension.height);
	}

}
