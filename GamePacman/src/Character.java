import java.awt.*;

public abstract class Character implements ICollisional {
	
	protected float speed;
	protected float x;
	protected float y;
	protected boolean isActive;
	protected Stereotip stereotip;
	protected int dimension;
	protected float speedX;
	protected float speedY;
	
	public Character(float speed, float x, float y) {
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.dimension = 19;
		this.speedX = 0.0f;
		this.speedY = 0.0f;
	}

	public void move(long deltaTime) {
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Stereotip getStereotip() {
		return stereotip;
	}

	public void setStereotip(Stereotip stereotip) {
		this.stereotip = stereotip;
	}
	public int getDimension() {
		return dimension;
	}

	public int setDimension(int dim) {
		return this.dimension = dim;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public abstract void onCollisionEnter(ICollisional other);

	@Override
	public abstract Shape getCollider();

	@Override
	public abstract Point getPosition();

	public void changeDirection(int direction) {

		if (direction == GameData.RIGHT){
			speedX = speed;
			speedY = 0;
		}
		else if (direction == GameData.LEFT){
			speedX = -speed;
			speedY = 0;
		}
		else if (direction == GameData.UP){
			speedY = -speed;
			speedX = 0;
		}
		else if (direction == GameData.DOWN){
			speedY = speed;
			speedX = 0;
		}
	}
}
