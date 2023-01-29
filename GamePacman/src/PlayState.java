import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {

	private boolean active;
	private List<Ghost> ghosts;
	private Pacman pacman;
	private Maze maze;
	private CollisionDetector collisionDetector;
	
	private List<DynamicCharacter> dynamicCharacters = new ArrayList<>();

	private GameData gameData;

	public PlayState() {

		gameData = GameData.getInstance();

		initGhosts(GameConsts.NUMBER_OF_GHOST);

		pacman = new Pacman(GameConsts.DEFAULT_SPEED, GameConsts.PACMAN_X, GameConsts.PACMAN_Y);
		maze = new Maze();

		collisionDetector = new CollisionDetector(maze);
		dynamicCharacters.add(pacman);
		dynamicCharacters.addAll(ghosts);
	}

	// TODO not here
	private void initGhosts(int ghostNum) {

		ghosts = new ArrayList<>();

		int x = GameConsts.GHOST_X;
		for (int i = 0; i < ghostNum ; i++) {
			ghosts.add(new Ghost(GameConsts.DEFAULT_SPEED, x, GameConsts.GHOST_Y));
			x += GameConsts.BLOCK_WIDTH;
		}
	}

	public void enter(Object memento) {
		active = true;
	}

	public void processKeyPressed(int aKeyCode) {

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

		var collisions = collisionDetector.DetectCollisions(dynamicCharacters);

		maze.setCharacterInPosition(pacman);
		collisionDetector.ExecuteOnCollisionEnters(collisions);

		pacman.move(deltaTime);
		maze.setCharacterInPosition(pacman);

		collisionDetector.ExecuteOnCollisionEnters(collisions);

		for (Ghost ghost : ghosts) {
			ghost.move(deltaTime);
			maze.setCharacterInPosition(ghost);
		}
		collisionDetector.ExecuteOnCollisionEnters(collisions);

		gameData.setScore(pacman.getCoinsAccount());
	}

	public boolean isActive() { return active; }

	public String next() {
		return "Result";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		
		Graphics g = aGameFrameBuffer.graphics();

		drawStatusBar(g);

		maze.draw(g);
		pacman.draw(g);

		for (Ghost ghost : ghosts)
			ghost.draw(g);

	}

	public void drawStatusBar(Graphics g) {
		g.setColor(Color.RED);

		String scoreTxt = "SCORES:  " + gameData.getScore();
		String levelTxt = "LEVEL:  " + gameData.getGameLevel();
		String lifeTxt = "LIFE:  " + gameData.getGameLife();

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
