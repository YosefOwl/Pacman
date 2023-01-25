import com.sun.tools.jconsole.JConsoleContext;

import java.awt.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Pacman extends Character{
	private List<Coin> coins = new ArrayList<>();
	public Pacman(float speed, float x, float y) {
		super(speed, x, y);
	}
	
	public void move(long deltaTime) {

		float lastX = x;
		float lastY = y;
		x = x + speedX*deltaTime;
		y = y + speedY*deltaTime;
		
//		if(Maze.collision(this.getDimension() , this.getDimension(), x, y)==false) {
//			x=lastx;
//			y=lasty;
//		}
		
		if (x < 0)
			x = 0;

		if (x > Game.WIDTH - dimension)
			x = Game.WIDTH - dimension;

		if (y < 0)
			y = 0;

		if (y > Game.HEIGHT - dimension)
			y = Game.HEIGHT - dimension;
	}

	@Override
	public void onCollisionEnter(ICollisional other) {
		try{
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
	public Shape getCollider() {
		return null;
	}

	@Override
	public Point getPosition() {
		return new Point((int)x,(int)y);
	}
}
