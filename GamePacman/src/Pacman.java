import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Pacman extends DynamicCharacter{
	private List<Coin> coins = new ArrayList<>();
	private long nextMoveCounterX = 0;
	private long nextMoveCounterY = 0;
	private int dx = 0;
	private int dy = 0;

	public Pacman(float speed, int x, int y) {
		super(x, y);

		setSpeed(speed);
		setDimension(GameData.PACMAN_D);
		setStereotype(Stereotype.ePacman);
	}
	
	public void move(long deltaTime) {

		// TODO : implement identify wall

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



		if(direction == GameData.UP) {
			nextMoveCounterY = (int)Maze.BLOCK_HEIGHT;
			dy = -vt;
			dx = 0;
			direction = 0;
			nextMoveCounterX = 0;
		}
		else if(direction == GameData.DOWN) {
			nextMoveCounterY = (int)Maze.BLOCK_HEIGHT;
			dy = vt;
			dx = 0;
			direction = 0;
			nextMoveCounterX = 0;
		}
		if (direction == GameData.RIGHT) {
			nextMoveCounterX = (int)Maze.BLOCK_WIDTH;
			dx = vt;
			dy = 0;
			direction = 0;
			nextMoveCounterY = 0;
		}
		else if (direction == GameData.LEFT) {
			nextMoveCounterX = (int)Maze.BLOCK_WIDTH;
			dx = -vt;
			direction = 0;
			dy = 0;
			nextMoveCounterY = 0;
		}
	}

	@Override
	public void onCollisionEnter(ICollisional other) {
		try{
			if(other.getCharacter().getStereotip().equals(Stereotip.eWall))
			{
				if(this.getLastPosition().x == this.getPosition().x){
					this.setSpeedY(0);
				}
				if(this.getLastPosition().y == this.getPosition().y){
					this.setSpeedX(0);
				}
				this.setPosition(this.getLastPosition());

			}
			else {
				this.setSpeed(GameConsts.PACMAN_SPEED);
			}

			if(other.getCharacter().getStereotip().equals(Stereotip.eCoin))
			{
				coins.add((Coin) other.getCharacter());
				return;
			}

			if(other.getCharacter().getStereotype().equals(Stereotype.eGhost))
			{
				this.setActive(false);
				//TODO: set state "gameOver"
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
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
		return new Rectangle(
				this.getPosition(),
				new Dimension(this.dimension.width + 2,this.dimension.height + 2));
	}

	@Override
	public Point getPosition() {
		return point;
	}
}
