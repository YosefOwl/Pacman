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

		setColor(Color.YELLOW);
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

		Character c = other.getCharacter();

		if (c.getStereotype().equals(Stereotype.eWall))
			handleWallCollision();

		if (c.getStereotype().equals(Stereotype.eCoin)) {
			if (c.isActive()) {
				coins.add( (Coin)c);
			}
		}

		if (c.getStereotype().equals(Stereotype.eGhost))
			handleGhostCollision(other);
			//TODO: set state "gameOver"
	}

	private void handleGhostCollision(ICollisional other) {

		Point pOther = other.getPosition();
		int directionOther = ((DynamicCharacter)other).getLastDirection();

		if (directionOther == lastDirection) {
			System.out.println("Behind you");
			GameData.getInstance().setLife(-1);

		}

		/*
		 checkDirectionCollision();
		 Sticky
		 change color
		 freeze with some special coin
		 life
		 פסילות
		 */

	}

	private void handleWallCollision() {

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

		int relativeX = position.x + (dimension.width - GameConsts.BLOCK_WIDTH)/2 + 1;
		int relativeY = position.y + (dimension.height - GameConsts.BLOCK_HEIGHT)/2 + 1;

		int dimX = GameConsts.BLOCK_WIDTH  - 2;
		int dimY = GameConsts.BLOCK_HEIGHT - 2;

		return new Rectangle( new Point(relativeX, relativeY), new Dimension(dimX, dimY) );
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

	public int getCoinsSize() {
		return coins.size();
	}

	public void draw(Graphics g) {

		g.setColor(this.color);

		g.drawOval(position.x, position.y, dimension.width, dimension.height);
		g.fillOval(position.x, position.y, dimension.width, dimension.height);
	}
}
