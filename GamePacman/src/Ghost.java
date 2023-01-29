import java.awt.*;
import java.util.*;
import java.util.List;

public class Ghost extends DynamicCharacter {

	private long time = 0;
	private Queue<Integer> dirQueue = new LinkedList<>();

	public Ghost(float speed, int x, int y) {
		super(speed, x, y);

		setActive(true);
		setStereotype(Stereotype.eGhost);
		setDimension(new Dimension(GameConsts.GHOST_D, GameConsts.GHOST_D));

		dirQueue.add(GameConsts.UP);
		dirQueue.add(GameConsts.LEFT);
		dirQueue.add(GameConsts.DOWN);
		dirQueue.add(GameConsts.RIGHT);
		setMoveFlow();

		setLastPosition(new Point(this.position));
		lastDirection = direction;

	}

	@Override
	public void move(long deltaTime) {
		super.move(deltaTime);

		int vt = (int) (speed*deltaTime);
		setLastPosition(new Point(this.getPosition()));

		dx = 0;
		dy = 0;

		if (nextMoveCounterX > 0)
			accuracyMoveX(vt);
		else if (nextMoveCounterY > 0)
			accuracyMoveY(vt);
		else if (isDirectionOnAxisX())
			moveOnAxisX(vt);
		else if (isDirectionOnAxisY())
			moveOnAxisY(vt);

		translatePosition(dx, dy);

		time += deltaTime;
		if (time > 1000 && (nextMoveCounterX == 0 || nextMoveCounterY == 0)) {
			setMoveFlow();
			time = 0;
		}
	}

	private void setMoveFlow() {

		int newDirection;

		newDirection = dirQueue.poll();
		dirQueue.add(newDirection);

		if (newDirection == direction)
			dirQueue.add(dirQueue.poll());

		setDirection(dirQueue.peek());
		Collections.shuffle((List<Integer>) dirQueue);
	}


	@Override
	public void onCollisionEnter(ICollisional other) {

		if (other.getCharacter().getStereotype().equals(Stereotype.eWall)) {

			if (position.y == lastPosition.y && position.x == lastPosition.x)
				return;

			if (position.x == lastPosition.x)
				nextMoveCounterY = 0;

			if(position.y == lastPosition.y)
				nextMoveCounterX = 0;

			this.setPosition(new Point(this.getLastPosition()));

			setMoveFlow();
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

		int dw = dimension.width / 2;
		int dh = dimension.height / 2;

		int relativeX = position.x + dw - (GameConsts.BLOCK_WIDTH / 2) + 1;
		int relativeY = position.y + dh - (GameConsts.BLOCK_HEIGHT / 2) + 1;

		int dimX = GameConsts.BLOCK_WIDTH  - 2;
		int dimY = GameConsts.BLOCK_HEIGHT - 2;

		return new Rectangle( new Point(relativeX, relativeY), new Dimension(dimX, dimY) );
	}

	public void draw(Graphics g) {

		g.setColor(Color.GREEN);

		g.drawRect(position.x, position.y, dimension.width, dimension.height);
		g.fillRect(position.x, position.y, dimension.width, dimension.height);
	}

}
