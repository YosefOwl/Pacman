import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pacman extends DynamicCharacter {
	private List<Coin> coins = new ArrayList<>();

	public Pacman(float speed, int x, int y) {

		super(speed, x, y);
		setSpeed(speed);
		setActive(true);
		setStereotype(Stereotype.ePacman);

		setDimension(new Dimension(GameConsts.PACMAN_D, GameConsts.PACMAN_D));
		lastPosition = new Point(getPosition());
		setDirection(GameConsts.STOP);
		lastDirection = direction;
	}

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
	}

	@Override
	public void onCollisionEnter(ICollisional other) {

		Stereotype otherStereotype = other.getCharacter().getStereotype();

		if (otherStereotype.equals(Stereotype.eWall))
			handWallCollision();

		if (otherStereotype.equals(Stereotype.eCoin))
			coins.add((Coin) other.getCharacter());

		if (otherStereotype.equals(Stereotype.eGhost))
			handGhostCollision(other);
			//TODO: set state "gameOver"
	}

	private void handGhostCollision(ICollisional other) {

//		Point pOther = other.getPosition();
//		int dirOther = other.g
//		this.setActive(false);
		//checkDirectionCollision();

		// Sticky

		// change color

		//

		// life
	}

	private void handWallCollision() {

		if (position.y == lastPosition.y && position.x == lastPosition.x)
			return;

		if (position.y != lastPosition.y && position.x != lastPosition.x) {
			nextMoveCounterY = 0;
			nextMoveCounterX = 0;
		}

		if (position.y != lastPosition.y)
			nextMoveCounterY = 0;

		if (position.x != lastPosition.x)
			nextMoveCounterX = 0;

		direction = GameConsts.STOP;
		setPosition(new Point(this.getLastPosition()));
	}

	@Override
	public Character getCharacter() {
		return this;
	}

	@Override
	public Shape getCollider() {

		int x = position.x;
		int y = position.y;
		int dw = dimension.width / 2;
		int dh = dimension.height / 2;

		int relativeX = x + dw - (GameConsts.BLOCK_WIDTH / 2) + 1;
		int relativeY = y + dh - (GameConsts.BLOCK_HEIGHT / 2) + 1;

		int dimX = GameConsts.BLOCK_WIDTH  - 2;
		int dimY = GameConsts.BLOCK_HEIGHT - 2;

		return new Rectangle( new Point(relativeX, relativeY), new Dimension(dimX, dimY) );
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public int getCoinsAccount() {
		return coins.size();
	}

	public void draw(Graphics g) {

		g.setColor(Color.YELLOW);

		g.drawRect(position.x, position.y, dimension.width, dimension.height);
		g.fillRect(position.x, position.y, dimension.width, dimension.height);
	}
}
