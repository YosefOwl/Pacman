import java.awt.*;

public class Coin extends Character {

	static final int VALUE = 1;
	static final int DIMENSION = 4;
	private boolean eaten;
	
	public Coin(float speed, float x, float y) {
		super(speed, x, y); //x=0, y=0
		setDimension(DIMENSION);
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
	public Shape getCollider() {
		return null;
	}

	@Override
	public Point getPosition() {
		//return new Point((int)x%15,(int)y%26);
		return new Point((int)x,(int)y);


	}

	public void setState(boolean state) {
		this.eaten = state;
	}
	
	public boolean getState() {
		return this.eaten;
	}
}
