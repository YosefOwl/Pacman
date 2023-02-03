import java.awt.*;

public abstract class Character implements ICollisional {
	
	protected Point position;
	protected boolean isActive;
	protected Stereotype stereotype;
	protected Dimension dimension;

	protected Color color;


	public Character(int x, int y) {
		position = new Point(x, y);
		setDimension(new Dimension(0,0));
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
		position.translate(dx, dy);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void draw(Graphics g) {}

	@Override
	public abstract void onCollisionEnter(ICollisional other);

	@Override
	public abstract Point getPosition();

}
