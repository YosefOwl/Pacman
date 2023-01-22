
public class Pacman extends Character{

	public Pacman(float speed, float x, float y) {
		super(speed, x, y);
	}
	
	public void move(long deltaTime) {
		
		x = x + speedX*deltaTime;
		y = y + speedY*deltaTime;
		
		
		if (x < 0)
			x = 0;
		
		if (x > Game.WIDTH - 11)
			x = Game.WIDTH - 11;
		
		if (y < 0)
			y = 0;
		
		if (y > Game.HEIGHT - 11)
			y = Game.HEIGHT - 11;
	}
}
