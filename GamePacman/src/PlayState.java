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
	
	private List<DynamicCharacter> dynamicCharacters = new ArrayList<>();

	//private GameData data;

	public PlayState() {
		data = GameData.getInstance();
		ghosts = new ArrayList<>();
//		int x = GameData.GHOST_X;
//		for (int i = 0; i < GameData.NUMBER_OF_GHOST ; i++) {
//			ghosts.add(new Ghost(GameData.DEFAULT_SPEED, x, GameData.GHOST_Y));
//			x += Maze.BLOCK_WIDTH;
//		}
		pacman = new Pacman(GameConsts.PACMAN_SPEED, new Point(26,35));
		maze = new Maze();

		collisionDetector = new CollisionDetector(maze);
		dynamicCharacters.add(pacman);
		dynamicCharacters.addAll(ghosts);
		//data = GameData.getInstance();
	}

	public void enter(Object memento) {
		active = true;
	}

	public void processKeyPressed (int aKeyCode) {

		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_Q)
			active = false;

		if (aKeyCode == KeyEvent.VK_UP)
			pacman.setDirection(GameData.UP);

		if (aKeyCode == KeyEvent.VK_DOWN)
			pacman.setDirection(GameData.DOWN);

		if (aKeyCode == KeyEvent.VK_LEFT)
			pacman.setDirection(GameData.LEFT);

		if (aKeyCode == KeyEvent.VK_RIGHT)
			pacman.setDirection(GameData.RIGHT);
	}
	
	public void update(long deltaTime) {

		pacman.move(deltaTime);
		maze.setCharacterInPosition(pacman);

		for (Ghost ghost : ghosts) {
			maze.setCharacterInPosition(ghost);
			ghost.move(deltaTime);
		}

		var collisions = collisionDetector.DetectCollisions(dynamicCharacters);
		collisionDetector.ExecuteOnCollisionEnters(collisions);
		ghosts.stream()
				.forEach(ghost -> {
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
		g.drawOval(pacman.getPosition().x, pacman.getPosition().y,
				   pacman.getDimension().width, pacman.getDimension().height);
		g.fillOval(pacman.getPosition().x, pacman.getPosition().y,
				   pacman.getDimension().width,
				   pacman.getDimension().height);

		g.setColor(Color.CYAN);
		g.drawRect(pacman.getCollider().getBounds().x,
				pacman.getCollider().getBounds().y,
				pacman.getCollider().getBounds().width,
				pacman.getCollider().getBounds().height
				);
	}

	// TODO may need to move to GameData class or Pacman class

	public void drawGhosts(Graphics g) {
		g.setColor(Color.GREEN);

		for (Ghost ghost : ghosts) {

			int x , y, dw,dh;
			
			x = ghost.point.x;
			y = ghost.point.y;
			dw = ghosts.get(i).getDimension().width;
			dh = ghosts.get(i).getDimension().height;

			g.drawRect(x, y, dw, dh);
			g.fillRect(x, y, dw, dh);
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

		g.drawString( levelTxt, Game.WIDTH/Maze.MAZE_COL, 515 );
		g.drawString( scoreTxt, Game.WIDTH/2 - scoreTxtWidth, 515 );
		g.drawString( lifeTxt,Game.WIDTH - lifeTxtWidth*3,515 );

	}

	public Object memento() {
		return this;
	}

}
