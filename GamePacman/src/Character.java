import java.awt.*;

public abstract class Character implements ICollisional {

	protected float speed;
	protected Point point;
	protected boolean isActive;
	protected Stereotype stereotype;
	protected int dimension;
	protected int direction;

	public Character(int x, int y) {

		point = new Point(x, y);
		setDimension(0);
		setDirection(0);
		setSpeed(0);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void move(long deltaTime) {}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Stereotype getStereotype() {
		return stereotype;
	}

	public void setStereotype(Stereotype stereotype) {
		this.stereotype = stereotype;
	}
	public int getDimension() {
		return dimension;
	}

	public int setDimension(int dim) {
		return this.dimension = dim;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void translatePosition(int dx, int dy) {
		point.translate(dx, dy);
	}

	@Override
	public abstract void onCollisionEnter(ICollisional other);

	@Override
	public abstract Shape getCollider();

	@Override
	public abstract Point getPosition();
}
