public class Ghost extends Character {
	
	public Ghost(float speed, float x, float y) {
		super(speed, x, y);
		this.speedX = speed;
	}

	public void move(long deltaTime) {
		x = x + speedX*deltaTime;
		y = y + speedY*deltaTime;
		
		if (x < 0) {
			x = 0;
			speedX = Math.abs(speedX);
		}
			
		
		if (x > Game.WIDTH - 11) {
			x = Game.WIDTH - 11;
			speedX = -Math.abs(speedX);
		}
		
		
		if (y < 0)
			y = 0;
		
		if (y > Game.HEIGHT - 11)
			y = Game.HEIGHT - 11;
	}
	
}
