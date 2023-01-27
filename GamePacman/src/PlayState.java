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
		//ghost = new Ghost(0.1f, 20, 20);
		ghosts = new ArrayList<>();
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
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_Q)
			active = false;

		if (aKeyCode == KeyEvent.VK_UP)
			pacman.changeDirection(GameConsts.UP);

		if (aKeyCode == KeyEvent.VK_DOWN)
			pacman.changeDirection(GameConsts.DOWN);

		if (aKeyCode == KeyEvent.VK_LEFT)
			pacman.changeDirection(GameConsts.LEFT);

		if (aKeyCode == KeyEvent.VK_RIGHT)
			pacman.changeDirection(GameConsts.RIGHT);

	}
	
	public void update(long deltaTime) {

		pacman.move(deltaTime);
		//maze.setCharacterInPosition(pacman);
		//maze.setCharacterInPosition(ghost);
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

		for(int i = 0; i < ghosts.size(); i++) {

			int x , y, dw,dh;

			x = ghosts.get(i).getPosition().x;
			y = ghosts.get(i).getPosition().y;
			dw = ghosts.get(i).getDimension().width;
			dh = ghosts.get(i).getDimension().height;

			g.drawRect(x, y, dw, dh);
			g.fillRect(x, y, dw, dh);
		}
	}

	public void drawStatusBar(Graphics g) {
		g.setColor(Color.RED);

		String scoreTxt = "SCORES:  " + 0;//data.getScore();
		String levelTxt = "LEVEL:  " + 0;//data.getGameLevel();
		String lifeTxt = "LIFE:  " + 0;//data.getGameLife();

		int scoreTxtWidth = g.getFontMetrics().stringWidth(scoreTxt);
		int lifeTxtWidth = g.getFontMetrics().stringWidth(lifeTxt);
		//int scoreTxtWidth = g.getFontMetrics().stringWidth(scoreTxt);

		g.setFont(new Font("Serif", Font.BOLD, 28) );

		g.drawString(levelTxt, (Game.WIDTH/ GameConsts.MAZE_COL) , 515);
		g.drawString(scoreTxt, (Game.WIDTH/2) -scoreTxtWidth , 515);

		g.drawString(lifeTxt, (int) GameConsts.BLOCK_WIDTH * GameConsts.MAZE_COL - lifeTxtWidth*3 , 515);

	}

	public Object memento() {
		return this;
	}

}
