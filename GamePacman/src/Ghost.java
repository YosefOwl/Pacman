import java.awt.*;
import java.util.Random;

public class Ghost extends DynamicCharacter {
	private Rectangle collider;
	public Ghost(float speed, int x, int y) {
		super(x, y);
		this.speedX = speed;
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

		if (direction == GameData.RIGHT)
			translatePosition( (int)(speed*deltaTime), 0 );

		else if (direction == GameData.LEFT)
			translatePosition( (int)(-speed*deltaTime), 0 );

		else if (direction == GameData.UP)
			translatePosition( 0, (int)(-speed*deltaTime) );

		else if (direction == GameData.DOWN)
			translatePosition( 0, (int)(speed*deltaTime) );

		if (point.x < 0)
			point.x = 0;

		if (point.x > Game.WIDTH - dimension)
			point.x = Game.WIDTH - dimension;

		if (point.y < 0)
			point.y = 0;

		if (point.y > Game.HEIGHT - dimension)
			point.y = Game.HEIGHT - dimension;
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
	public Point getPosition() {
		return point;
	}

}
