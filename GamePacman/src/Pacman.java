import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Pacman extends DynamicCharacter{
	private List<Coin> coins = new ArrayList<>();
	public Pacman(float speed, Point position) {
		super(speed, position);
		this.setActive(true);
		this.setStereotip(Stereotip.ePacman);

	}
	
	public void move(long deltaTime) {

		this.setLastPosition(new Point(position.x, position.y));
		position.x = (int)(Math.ceil(position.x + speedX*deltaTime));
		position.y = (int)(Math.ceil(position.y + speedY*deltaTime));

		if (position.x < 0)
			position.x = 0;

		if (position.x > Game.WIDTH - dimension.width)
			position.x = Game.WIDTH - dimension.width;

		if (position.y < 0)
			position.y = 0;

		if (position.y > Game.HEIGHT - dimension.height)
			position.y = Game.HEIGHT - dimension.height;

		System.out.println("pacman last position : " + this.getLastPosition().toString());
		System.out.println("pacman new position : " + this.getPosition().toString());

	}

	public void changeDirection(int direction) {

		if (direction == GameConsts.RIGHT){
			speedX = speed;
			speedY = 0;
		}
		else if (direction == GameConsts.LEFT){
			speedX = -speed;
			speedY = 0;
		}
		else if (direction == GameConsts.UP){
			speedY = -speed;
			speedX = 0;
		}
		else if (direction == GameConsts.DOWN){
			speedY = speed;
			speedX = 0;
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

			if(other.getCharacter().getStereotip().equals(Stereotip.eGhost))
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
	public boolean HasBound() {
		return true;
	}
}
