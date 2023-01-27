import java.awt.*;

public abstract class Character implements ICollisional {
	
	protected Point point;
	protected boolean isActive;
	protected Stereotype stereotype;
	protected Dimension dimension;

	public Character(int x, int y) {

		point = new Point(x, y);
		setDimension(new Dimension(19,19));
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
	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dim) {
		this.dimension = dim;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void translatePosition(int dx, int dy) {
		point.translate(dx, dy);
	}

	@Override
	public abstract void onCollisionEnter(ICollisional other);

	@Override
	public abstract Point getPosition();
	
}
