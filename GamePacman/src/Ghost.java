import java.awt.*;

public class Ghost extends Character {
	
	public Ghost(float speed, float x, float y) {
		super(speed, x, y);
		this.speedX = speed;
	}

	
	public void move(long deltaTime) {
		//TODO: implement
		x = x + speedX*deltaTime;
		y = y + speedY*deltaTime;

	}

	@Override
	public void onCollisionEnter(ICollisional other) {

	}

	@Override
	public Character getCharacter() {
		return this;
	}

	@Override
	public Shape getCollider() {
		return null;
	}

	@Override
	public Point getPosition() {
		return null;
	}

}
