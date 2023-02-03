import java.awt.*;
import java.util.*;
import java.util.List;

public class Ghost extends DynamicCharacter {

	static final List<Color> ghostColor = Arrays.asList(Color.MAGENTA, Color.RED, Color.GREEN, Color.CYAN, Color.BLUE);

	private Queue<Integer> dirQueue;
	private long time = 0;


	public Ghost(float speed, int x, int y) {
		super(speed, x, y,null);

		setActive(true);
		setStereotype(Stereotype.eGhost);
		setDimension(new Dimension(GameConsts.GHOST_D, GameConsts.GHOST_D));

		dirQueue = new LinkedList<>();
		dirQueue.addAll(Arrays.asList(GameConsts.UP, GameConsts.LEFT, GameConsts.DOWN, GameConsts.RIGHT));
		setLastPosition(new Point(this.position));

		setMoveFlow();
		lastDirection = direction;
		initGhostColor();
	}


	@Override
	public void move(long deltaTime) {

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
		if (time > 1000 && (nextMoveCounterX == 0 && nextMoveCounterY == 0)) {
			setMoveFlow();
			time = 0;
		}
	}

	@Override
	public void executeStateBehavior(long deltaTime) {

	}

	private void setMoveFlow() {

		int newDirection;
		newDirection = dirQueue.poll();
		dirQueue.add(newDirection);

		if (newDirection == direction) {
			dirQueue.add(dirQueue.poll());
			Collections.shuffle((List<Integer>) dirQueue);
		}

		setDirection(dirQueue.peek());
	}


	@Override
	public void onCollisionEnter(ICollisional other) {

		if (other.getCharacter().getStereotype().equals(Stereotype.eWall)) {
			handleWallCollision();
		}

		if (other.getCharacter().getStereotype().equals(Stereotype.eGhost)) {
			changeColor();
		}

	}

	private void changeColor() {
		int currentColorId = ghostColor.indexOf(color);

		if (currentColorId == -1 || currentColorId == ghostColor.size() -1 )
			setColor(ghostColor.get(0));
		else
			setColor(ghostColor.get(currentColorId + 1));


	}

	private void handleWallCollision() {
		if (position.y == lastPosition.y && position.x == lastPosition.x)
			return;

		if (position.x == lastPosition.x)
			nextMoveCounterY = 0;

		if(position.y == lastPosition.y)
			nextMoveCounterX = 0;

		this.setPosition(new Point(this.getLastPosition()));
		setMoveFlow();
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

		int relativeX = position.x + dimension.width/2 - (GameConsts.BLOCK_WIDTH/2) + 1;
		int relativeY = position.y + dimension.height/2 - (GameConsts.BLOCK_HEIGHT/2) + 1;
		int dimX = GameConsts.BLOCK_WIDTH  - 2;
		int dimY = GameConsts.BLOCK_HEIGHT - 2;

		return new Rectangle( new Point(relativeX, relativeY), new Dimension(dimX, dimY) );
	}

	private void initGhostColor() {
		Collections.shuffle(ghostColor);
		setColor(ghostColor.stream().findAny().get());
	}

	public void draw(Graphics g) {

		g.setColor(this.color);

		g.drawRect(position.x, position.y, dimension.width, dimension.height);
		g.fillRect(position.x, position.y, dimension.width, dimension.height);
	}

}
