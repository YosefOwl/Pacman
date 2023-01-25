import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PlayState extends GameState {

	private boolean active;
	private List<Ghost> ghosts;
	private Pacman pacman;
	private Maze maze;
	private CollisionDetector collisionDetector;
	private GameData data;

	public PlayState() {
		//ghost = new Ghost(0.1f, 20, 20);
		ghosts = new ArrayList<>();
		pacman = new Pacman(0.1f, 26, 35);
		pacman.setActive(true);
		pacman.setStereotip(Stereotip.ePacman);
		maze = new Maze();
		collisionDetector = new CollisionDetector(maze);
		data = GameData.getInstance();
	}

	public void enter(Object memento) {
		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_Q)
			active = false;

		if (aKeyCode == KeyEvent.VK_UP)
			pacman.changeDirection(GameData.UP);

		if (aKeyCode == KeyEvent.VK_DOWN)
			pacman.changeDirection(GameData.DOWN);

		if (aKeyCode == KeyEvent.VK_LEFT)
			pacman.changeDirection(GameData.LEFT);

		if (aKeyCode == KeyEvent.VK_RIGHT)
			pacman.changeDirection(GameData.RIGHT);

	}
	
	public void update(long deltaTime) {

		pacman.move(deltaTime);
		maze.setCharacterInPosition(pacman);
		//maze.setCharacterInPosition(ghost);
		var collisions = collisionDetector.DetectCollisions();
		collisionDetector.ExecuteOnCollisionEnters(collisions);
		ghosts.stream()
				.map(ghost -> {
					if(ghost.isActive){
						ghost.move(deltaTime);
					}
				});
	}

	public boolean isActive() { return active; }

	public String next() {
		return "Result";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		
		Graphics g = aGameFrameBuffer.graphics();
		maze.render(g);
		drawPacman(g);
		drawGhosts(g);
		drawStatusBar(g);
	}

	// TODO may need to move to GameData class or Pacman class
	private void drawPacman(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawOval((int)pacman.getX(), (int)pacman.getY(), pacman.getDimension(), pacman.getDimension());
		g.fillOval((int)pacman.getX(), (int)pacman.getY(), pacman.getDimension(), pacman.getDimension());
	}

	private void putCoins(Graphics g) {
		g.setColor(Color.PINK);
		for(int i=0; i<maze.coins.size(); i++) {
			g.drawOval((int)coins.get(i).getX(), (int)coins.get(i).getY(), coins.get(i).dimension, coins.get(i).dimension);
			g.fillOval((int)coins.get(i).getX(), (int)coins.get(i).getY(), coins.get(i).dimension, coins.get(i).dimension);
		}		
	}
	// TODO may need to move to GameData class or Pacman class

	public void drawGhosts(Graphics g) {
		g.setColor(Color.GREEN);

		for(int i = 0; i < ghosts.size(); i++) {

			int x , y, d;

			x = (int)ghosts.get(i).getX();
			y = (int)ghosts.get(i).getY();
			d = ghosts.get(i).getDimension();

			g.drawRect(x, y, d, d);
			g.fillRect(x, y, d, d);
		}
	}

	public void drawStatusBar(Graphics g) {
		g.setColor(Color.RED);

		String scoreTxt = "SCORES:  " + data.getScore();
		String levelTxt = "LEVEL:  " + data.getGameLevel();
		String lifeTxt = "LIFE:  " + data.getGameLife();

		int scoreTxtWidth = g.getFontMetrics().stringWidth(scoreTxt);
		int lifeTxtWidth = g.getFontMetrics().stringWidth(lifeTxt);
		//int scoreTxtWidth = g.getFontMetrics().stringWidth(scoreTxt);

		g.setFont(new Font("Serif", Font.BOLD, 28) );

		g.drawString(levelTxt, (Game.WIDTH/Maze.MAZE_COL) , 515);
		g.drawString(scoreTxt, (Game.WIDTH/2) -scoreTxtWidth , 515);

		g.drawString(lifeTxt, (int)Maze.BLOCK_WIDTH*Maze.MAZE_COL - lifeTxtWidth*3 , 515);

	}

	public Object memento() {
		return this;
	}

}
