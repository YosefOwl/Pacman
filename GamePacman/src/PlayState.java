import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {

	private boolean active;
	private int scores;
	private Ghost ghost;
	private Pacman pacman;
	private Maze maze;

	public PlayState() {
		ghost = new Ghost(0.1f, 20, 20);
		pacman = new Pacman(0.1f, 0, 20);
		maze = new Maze();
		scores = 0;
		
	}

	public void enter(Object memento) {
		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_Q)
			active = false;

		if (aKeyCode == KeyEvent.VK_UP){
			pacman.speedY = -pacman.speed;
			pacman.speedX = 0;
		}

		if (aKeyCode == KeyEvent.VK_DOWN) {
			pacman.speedY = pacman.speed;
			pacman.speedX = 0;
		}

		if (aKeyCode == KeyEvent.VK_LEFT) {
			pacman.speedX = -pacman.speed;
			pacman.speedY = 0;
		}


		
		if (aKeyCode == KeyEvent.VK_RIGHT) {
			pacman.speedX = pacman.speed;
			pacman.speedY = 0;
		}

	}
	
	public void update(long deltaTime) {

		pacman.move(deltaTime);
		ghost.move(deltaTime);
		
//		float px = pacman.getX();
//		float py = pacman.getY();
//		float gx = ghost.getX();
//		float gy = ghost.getX();
//		int d = pacman.getDimension();

		// check collision
//		if (px <= gx && py <= gy || px >= gx && py >= gy) {
//			scores = scores + 10;


	}

	public boolean isActive() { return active; }

	public String next() {
		return "Result";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		
		Graphics g = aGameFrameBuffer.graphics();
		maze.render(g);
		g.setColor(Color.white);
		g.drawOval((int)pacman.getX(), (int)pacman.getY(), pacman.getDimension(), pacman.getDimension());
		g.drawRect((int)ghost.getX(), (int)ghost.getY(), ghost.getDimension(), ghost.getDimension());
		String message = "Scores : " + scores;
		g.drawString(message, 10, 10);

	}

	public Object memento() {
		return this;
	}

	public int getScores() {
		return scores;
	}
}
