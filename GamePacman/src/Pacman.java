
public class Pacman extends Character {

	public Pacman(float speed, float x, float y, int dimension) {
		super(speed, x, y, dimension);
	}

	@Override
	public void move(long deltaTime) {

		float lastX = x;
		float lastY = y;
		x = x + speedX*deltaTime;
		y = y + speedY*deltaTime;
		
		if(Maze.collision(this.getDimension() , this.getDimension(), x, y) == false) {
			y = lastY;
			x = lastX;
		}
		
		if (x < 0)
			x = 0;

		if (x > Game.WIDTH - dimension)
			x = Game.WIDTH - dimension;

		if (y < 0)
			y = 0;

		if (y > Game.HEIGHT - dimension)
			y = Game.HEIGHT - dimension;
	}

}
