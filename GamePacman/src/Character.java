import java.awt.*;

public abstract class Character implements ICollisional {

	protected Point position;
	protected boolean isActive;
	protected Stereotip stereotip;
	protected Dimension dimension;

	
	public Character(Point position) {
		this.position = position;
		this.dimension = new Dimension(19,19);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Stereotip getStereotip() {
		return stereotip;
	}

	public void setStereotip(Stereotip stereotip) {
		this.stereotip = stereotip;
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


	@Override
	public abstract void onCollisionEnter(ICollisional other);

	@Override
	public abstract Point getPosition();

}
