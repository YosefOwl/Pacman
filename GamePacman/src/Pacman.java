import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Pacman extends DynamicCharacter {
	private List<Coin> coins = new ArrayList<>();
	private long nextMoveCounterX = 0;
	private long nextMoveCounterY = 0;
	private int dx = 0;
	private int dy = 0;

	public Pacman(float speed, int x, int y) {

		super(speed, x, y);
		setSpeed(speed);
		setDimension(new Dimension(GameConsts.PACMAN_D, GameConsts.PACMAN_D));
		setStereotype(Stereotype.ePacman);
	}
	
	public void move(long deltaTime) {

		// TODO : implement identify wall

		setLastPosition(new Point(this.getPosition()));

		int vt = (int) (speed*deltaTime);

		if (nextMoveCounterX > 0){
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

		if(other.getCharacter().getStereotype().equals(Stereotype.eCoin)) {
			coins.add((Coin) other.getCharacter());
			return;
		}

		if(other.getCharacter().getStereotype().equals(Stereotype.eGhost)) {

			System.out.println("Crash ");
			this.setActive(false);
			//TODO: set state "gameOver"
		}

	}

	@Override
	public Character getCharacter() {
		return this;
	}

	@Override
	public Shape getCollider() {
		Shape shape = new Rectangle(
				new Point(this.getPosition().x + this.dimension.width/2 - GameConsts.BLOCK_WIDTH/2 + 1, this.getPosition().y + dimension.height/2 - GameConsts.BLOCK_HEIGHT/2 + 1),
				new Dimension(GameConsts.BLOCK_WIDTH - 2,GameConsts.BLOCK_HEIGHT - 2) );
		return shape;
	}

	@Override
	public Point getPosition() {
		return position;
	}
}
