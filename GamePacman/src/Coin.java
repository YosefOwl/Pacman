import java.awt.*;

public class Coin extends Character {
	static final int DIMENSION = 4;
	private boolean eaten;
	public Coin(float speed, Point position) {
		super(position); //x=0, y=0
		setDimension(new Dimension(DIMENSION,DIMENSION));
		setActive(true);
		setStereotip(Stereotip.eCoin);
	}

	@Override
	public void onCollisionEnter(ICollisional other) {
		if(other.getCharacter().getStereotip().equals(Stereotip.ePacman))
		{
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
