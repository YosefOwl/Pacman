import java.awt.*;
import java.util.Random;

public class Ghost extends DynamicCharacter {
	private Rectangle collider;
	public Ghost(float speed, int x, int y) {
		super(speed, x, y);
		setActive(true);
		setStereotype(Stereotype.eGhost);
		collider = new Rectangle(
				this.getPosition(),
				new Dimension((int)GameConsts.BLOCK_WIDTH,(int)GameConsts.BLOCK_HEIGHT)
		);

	}

	private long time = 0;

	@Override
	public void move(long deltaTime) {
		//TODO: implement

		time += deltaTime;


		if (time > 1000) {

			Random rand = new Random();
			int randDir = rand.nextInt(5);
			setDirection(randDir);
			time = 0;
		}

		if (direction == GameConsts.RIGHT)
			translatePosition( (int)(speed*deltaTime), 0 );

		else if (direction == GameConsts.LEFT)
			translatePosition( (int)(-speed*deltaTime), 0 );

		else if (direction == GameConsts.UP)
			translatePosition( 0, (int)(-speed*deltaTime) );

		else if (direction == GameConsts.DOWN)
			translatePosition( 0, (int)(speed*deltaTime) );

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
		return position;
	}

	@Override
	public Shape getCollider() {
		return null;
	}

	@Override
	public boolean HasBound() {
		return false;
	}

}
