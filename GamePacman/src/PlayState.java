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

	private GameData data;

	public PlayState() {
		data = GameData.getInstance();
		ghosts = new ArrayList<>();

		int x = GameConsts.GHOST_X;

		for (int i = 0; i < GameConsts.NUMBER_OF_GHOST ; i++) {
			ghosts.add(new Ghost(GameConsts.DEFAULT_SPEED, x, GameConsts.GHOST_Y));
			x += GameConsts.BLOCK_WIDTH;
		}
		pacman = new Pacman(GameConsts.DEFAULT_SPEED, GameConsts.PACMAN_X, GameConsts.PACMAN_Y);
		maze = new Maze();

		collisionDetector = new CollisionDetector(maze);
		dynamicCharacters.add(pacman);
		dynamicCharacters.addAll(ghosts);
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
			pacman.setDirection(GameConsts.UP);

		if (aKeyCode == KeyEvent.VK_DOWN)
			pacman.setDirection(GameConsts.DOWN);

		if (aKeyCode == KeyEvent.VK_LEFT)
			pacman.setDirection(GameConsts.LEFT);

		if (aKeyCode == KeyEvent.VK_RIGHT)
			pacman.setDirection(GameConsts.RIGHT);
	}
	
	public void update(long deltaTime) {

		pacman.move(deltaTime);
		maze.setCharacterInPosition(pacman);

		for (Ghost ghost : ghosts) {
			ghost.move(deltaTime);
			maze.setCharacterInPosition(ghost);
		}

		var collisions = collisionDetector.DetectCollisions(dynamicCharacters);
		collisionDetector.ExecuteOnCollisionEnters(collisions);
//		ghosts.stream()
//				.forEach(ghost -> {
//					if(ghost.isActive){
//						ghost.move(deltaTime);
//					}
//				});
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

		int x , y, dw,dh;

		x = pacman.position.x;
		y = pacman.position.y;
		dw = pacman.getDimension().width;
		dh = pacman.getDimension().height;

		g.drawRect(x, y, dw, dh);
		g.fillRect(x, y, dw, dh);

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
			
			x = ghost.position.x;
			y = ghost.position.y;
			dw = ghost.getDimension().width;
			dh = ghost.getDimension().height;

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

		g.drawString( levelTxt, Game.WIDTH/GameConsts.MAZE_COL, 515 );
		g.drawString( scoreTxt, Game.WIDTH/2 - scoreTxtWidth, 515 );
		g.drawString( lifeTxt,Game.WIDTH - lifeTxtWidth*3,515 );

	}

	public Object memento() {
		return this;
	}

}
