import java.util.Random;

public class Ghost extends Character {
	
	public Ghost(float speed, float x, float y, int dimension) {
		super(speed, x, y, dimension);
		this.speedX = speed;
	}

	@Override
	public void move(long deltaTime) {

	}
}
