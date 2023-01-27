import java.awt.*;

public class Ghost extends DynamicCharacter {
	private Rectangle collider;
	public Ghost(float speed, Point position) {
		super(speed, position);
		this.speedX = speed;
		collider = new Rectangle(
				this.getPosition(),
				new Dimension((int)GameConsts.BLOCK_WIDTH,(int)GameConsts.BLOCK_HEIGHT)
		);

	}

	
	public void move(long deltaTime) {
		//TODO: implement
		position.x = (int)(position.x + speedX*deltaTime);
		position.y = (int)(position.y + speedY*deltaTime);

	}

	@Override
	public void onCollisionEnter(ICollisional other) {

	}

	@Override
	public Character getCharacter() {
		return this;
	}

	@Override
	public Point getPosition() {
		return null;
	}

	@Override
	public Shape getCollider() {
		return null;
	}

	@Override
	public boolean HasBound() {
		return true;
	}

}
