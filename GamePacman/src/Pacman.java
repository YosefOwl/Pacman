

public class Pacman extends Character{

	public Pacman(float speed, float x, float y) {
		super(speed, x, y);
	}
	
	public void move(long deltaTime) {

		float lastx = x;
		float lasty = y;
		x = x + speedX*deltaTime;
		y = y + speedY*deltaTime;
		
		if(Maze.collision(this.getDimension() , this.getDimension(), x, y)==false) {
			x=lastx;
			y=lasty;
		}
		
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
