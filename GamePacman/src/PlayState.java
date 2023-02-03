import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlayState extends GameState {

	private boolean active;
	private List<Ghost> ghosts;
	private Pacman pacman;
	private Maze maze;
	private CollisionDetector collisionDetector;
	private List<DynamicCharacter> dynamicCharacters;
	private GameData gameData;

	public PlayState() {
		gameData = GameData.getInstance();
		initGame();
	}

	private void initGame() {
		dynamicCharacters = new ArrayList<>();
		ghosts = new ArrayList<>();

		maze = new Maze(gameData.getSketch());
		collisionDetector = new CollisionDetector(maze);

		initCharacter();

		dynamicCharacters.add(pacman);
		dynamicCharacters.addAll(ghosts);
	}

	private void initCharacter() {

		// speed of pacman and ghosts increasing  each level
		float speed = GameConsts.DEFAULT_SPEED + (gameData.getLevel() - 1)*0.1f;
		int x = GameConsts.GHOST_X;
		int y;

		CharacterStateMachine pacmanStateMachine = buildPacmanStateMachine();
		pacman = new Pacman(speed, GameConsts.PACMAN_X, GameConsts.PACMAN_Y,pacmanStateMachine);
		maze.setCharacterInPosition(pacman);

		// set ghosts position and double the ghosts each level
		for (int i = 1 ; i <= gameData.getLevel(); i++) {
			y = GameConsts.GHOST_Y + GameConsts.BLOCK_HEIGHT *(i - gameData.getLevel());
			for (int j = 0; j < GameConsts.NUMBER_OF_GHOST; j++) {

				Ghost ghost = new Ghost(speed, x, y);
				ghosts.add(ghost);
				maze.setCharacterInPosition(ghost);
				x = x + GameConsts.BLOCK_WIDTH;
			}
			x = GameConsts.GHOST_X;
		}
	}


	private CharacterStateMachine buildPacmanStateMachine() {
		var exploreState = new ExploringState(new ExploringStateHandler());
		var zombieState = new ZombieState(new ZombieStateHandler());
		var diedState = new DiedState(new DiedStatePacmanHandler());

		CharacterStateMachine stateMachine = new CharacterStateMachine(exploreState);

		stateMachine.AddTransition(new Transition(exploreState,zombieState,"ghostHit"));
		stateMachine.AddTransition(new Transition(zombieState,diedState,"ghostHit"));
		stateMachine.AddTransition(new Transition(diedState,exploreState,"explorer"));

		return stateMachine;

	}

	public void processKeyReleased(int aKeyCode) {

		int pDir = pacman.getDirection();

		switch (aKeyCode) {
			case KeyEvent.VK_RIGHT:
				if (GameConsts.RIGHT == pDir)
					pacman.setDirection(GameConsts.STOP);
			case KeyEvent.VK_LEFT:
				if (GameConsts.LEFT == pDir)
					pacman.setDirection(GameConsts.STOP);
			case KeyEvent.VK_DOWN:
				if (GameConsts.DOWN == pDir)
					pacman.setDirection(GameConsts.STOP);
			case KeyEvent.VK_UP:
				if (GameConsts.UP == pDir)
					pacman.setDirection(GameConsts.STOP);
			default:
				// do nothing
		}

	}

	public void processKeyPressed(int aKeyCode) {

		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_Q)
			active = false;

		boolean noDirection = pacman.getDirection() == GameConsts.STOP;

		if (aKeyCode == KeyEvent.VK_RIGHT && noDirection)
			pacman.setDirection(GameConsts.RIGHT);

		if (aKeyCode == KeyEvent.VK_LEFT&& noDirection)
			pacman.setDirection(GameConsts.LEFT);

		if (aKeyCode == KeyEvent.VK_DOWN && noDirection)
			pacman.setDirection(GameConsts.DOWN);

		if (aKeyCode == KeyEvent.VK_UP && noDirection)
			pacman.setDirection(GameConsts.UP);
	}

	public void update(long deltaTime) {

		var collisions = collisionDetector.DetectCollisions(dynamicCharacters);

		collisionDetector.ExecuteOnCollisionEnters(collisions);

		pacman.executeStateBehavior(deltaTime);
		maze.setCharacterInPosition(pacman);

		for (Ghost ghost : ghosts) {
			ghost.move(deltaTime);
			maze.setCharacterInPosition(ghost);
		}
		collisionDetector.ExecuteOnCollisionEnters(collisions);

		gameData.setScore(pacman.checkScore());
		checkLevelStatus();
	}

	private void checkLevelStatus() {
		if (gameData.getScore() >= 40 || gameData.getScore() == maze.getCoinCount()) { // 40 for testing, shall be maze.getCoinCount()
			// TODO display some message or other screen
			if (gameData.hasNextLevel()) {
				// TODO save previous lvl data
				resetGame();
				initNextLevel();
			}
			else {
				this.active = false;
			}
		}

		if(gameData.getLevel() <= 0){
			this.active = false;
		}
	}

	private void initNextLevel() {
		gameData.nextLevel();
		maze = new Maze(gameData.getSketch());
		collisionDetector = new CollisionDetector(maze);
		initCharacter();
		dynamicCharacters.add(pacman);
		dynamicCharacters.addAll(ghosts);
	}

	private void resetGame() {
		// dataGame not rested
		maze = null;
		pacman = null;
		collisionDetector = null;
		dynamicCharacters.removeAll(this.dynamicCharacters);
		ghosts.removeAll(this.ghosts);
	}

	public Object memento() {
		return this;
	}

	public void enter(Object memento) {
		active = true;
	}

	public boolean isActive() { return active; }

	public String next() {
		return "Result";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		
		Graphics g = aGameFrameBuffer.graphics();

		drawStatusBar(g);
		maze.drawMaze(g);
	}

	public void drawStatusBar(Graphics g) {
		g.setColor(Color.RED);

		String scoreTxt = "SCORES:  " + gameData.getScore();
		String levelTxt = "LEVEL:  " + gameData.getLevel();
		String lifeTxt = "LIFE:  " + pacman.getLife();

		int scoreTxtWidth = g.getFontMetrics().stringWidth(scoreTxt);
		int lifeTxtWidth = g.getFontMetrics().stringWidth(lifeTxt);

		g.setFont(new Font("Serif", Font.BOLD, 28) );

		g.drawString( levelTxt, Game.WIDTH/GameConsts.MAZE_COL, 515 );
		g.drawString( scoreTxt, Game.WIDTH/2 - scoreTxtWidth, 515 );
		g.drawString( lifeTxt, Game.WIDTH - lifeTxtWidth*3,515 );

		// for fun
		Point pp = pacman.getPosition();

		String pacmanData = "Pacman Data : ";

		String pacmanPosOnMaze = "real [row, col]=" + "[" + pp.x/GameConsts.BLOCK_WIDTH + ","+ pp.y/GameConsts.BLOCK_HEIGHT + "]";
		String pacmanPosOnMazeRound = "maze [row, col]=" + "[" + Math.round(pp.x/GameConsts.BLOCK_WIDTH) + ","+ Math.round(pp.y/GameConsts.BLOCK_HEIGHT) + "]";
		String posMaze = pacmanPosOnMaze +", " + pacmanPosOnMazeRound;

		String pacmanRealPos = "curr pos [x, y]=" + "[" + pp.x + ","+ pp.y + "]";
		String pacmanLastMovePixels = "step [dx, dy]=" + "["+ pacman.dx + "," + pacman.dy + "]";
		String pos = pacmanRealPos +", " + pacmanLastMovePixels;

		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20) );

		g.setColor(Color.GRAY);

		g.drawString( pacmanData, Game.WIDTH/GameConsts.MAZE_COL, 545 );

		g.drawString( pos, Game.WIDTH/GameConsts.MAZE_COL, 570 );
		g.drawString( posMaze, Game.WIDTH/GameConsts.MAZE_COL , 590 );
	}

	public GameData getData() {
		return gameData;
	}
}
