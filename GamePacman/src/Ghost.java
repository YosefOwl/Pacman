import java.awt.*;
import java.util.Random;

public class Ghost extends DynamicCharacter {

	private long nextMoveCounterX = 0;
	private long nextMoveCounterY = 0;
	private int dx = 0;
	private int dy = 0;

	public Ghost(float speed, int x, int y) {
		super(speed, x, y);
		setActive(true);
		setStereotype(Stereotype.eGhost);
		setDimension(new Dimension(GameConsts.GHOST_D, GameConsts.GHOST_D));
	}

	private long time = 0;

	@Override
	public void move(long deltaTime) {

		setLastPosition(new Point(this.getPosition()));
		int vt = (int) (speed*deltaTime);

		time += deltaTime;

		if (time > 1000) {

			Random rand = new Random();
			int randDir = rand.nextInt(10);
			setDirection(randDir);
			time = 0;
		}



		if (nextMoveCounterX > 0) {
			translatePosition(dx, dy);
			nextMoveCounterX--;
			return;
		}

		if (nextMoveCounterY > 0){
			translatePosition(dx, dy);
			nextMoveCounterY--;
			return;
		}

		if(direction == GameConsts.UP) {
			nextMoveCounterY = GameConsts.BLOCK_HEIGHT;
			dy = -vt;
			dx = 0;
			nextMoveCounterX = 0;
		}
		else if(direction == GameConsts.DOWN) {
			nextMoveCounterY = GameConsts.BLOCK_HEIGHT;
			dy = vt;
			dx = 0;
			nextMoveCounterX = 0;
		}

		if (direction == GameConsts.RIGHT) {
			nextMoveCounterX = GameConsts.BLOCK_WIDTH;
			dx = vt;
			dy = 0;
			nextMoveCounterY = 0;
		}
		else if (direction == GameConsts.LEFT) {
			nextMoveCounterX = GameConsts.BLOCK_WIDTH;
			dx = -vt;
			dy = 0;
			nextMoveCounterY = 0;
		}

		direction = 0;

	}

	@Override
	public void onCollisionEnter(ICollisional other) {
		if(other.getCharacter().getStereotype().equals(Stereotype.eWall)) {
			nextMoveCounterX = 0;
			nextMoveCounterY = 0;
			this.setPosition(this.getLastPosition());
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
		Shape shape = new Rectangle(
				new Point(this.getPosition().x + this.dimension.width/2 - GameConsts.BLOCK_WIDTH/2 + 1, this.getPosition().y + dimension.height/2 - GameConsts.BLOCK_HEIGHT/2 + 1),
				new Dimension(GameConsts.BLOCK_WIDTH - 2,GameConsts.BLOCK_HEIGHT - 2) );
		return shape;
	}
}
