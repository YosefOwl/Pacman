import java.awt.*;

public class Coin extends Character {

	static final int DIMENSION = 4;
	private boolean eaten;
	
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
	public Point getPosition() {
		return point;
	}

	@Override
	public Shape getCollider() {
		return new Rectangle(
				this.position,
				new Dimension(DIMENSION + 2,DIMENSION + 2));
	}

	@Override
	public boolean HasBound() {
		return false;
	}

	public void setState(boolean state) {
		this.eaten = state;
	}
	
	public boolean getState() {
		return this.eaten;
	}
}
