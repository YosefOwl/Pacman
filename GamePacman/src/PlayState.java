import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

public class PlayState extends GameState {

	private boolean active;
	private GameData data;
	public PlayState() {
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
			data.getPacman().changeDirection(GameData.UP);

		if (aKeyCode == KeyEvent.VK_DOWN)
			data.getPacman().changeDirection(GameData.DOWN);

		if (aKeyCode == KeyEvent.VK_LEFT)
			data.getPacman().changeDirection(GameData.LEFT);

		if (aKeyCode == KeyEvent.VK_RIGHT)
			data.getPacman().changeDirection(GameData.RIGHT);

	}
	
	public void update(long deltaTime) {

		data.getPacman().move(deltaTime);

		for(int i = 0; i < data.getGhostList().size(); i++) {
			data.getGhost(i).move(deltaTime);
		}

	}

	public boolean isActive() {
			return active;
	}

	public String next() {
		return "Result";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		
		Graphics g = aGameFrameBuffer.graphics();
		data.getMaze().render(g);

		drawCoins(g);
		drawPacman(g);
		drawGhosts(g);
		drawStatusBar(g);
	}

	// TODO may need to move to GameData class or Pacman class
	private void drawPacman(Graphics g) {

		g.setColor(Color.YELLOW);

		int x , y, d;
		x = (int)data.getPacman().getX();
		y = (int)data.getPacman().getY();
		d = data.getPacman().getDimension();

		g.drawOval(x, y, d, d);
		g.fillOval(x, y, d, d);
	}


	// TODO may need to move to GameData class or Pacman class
	private void drawCoins(Graphics g) {
		g.setColor(Color.PINK);

		for(int i = 0; i < data.getCoins().size(); i++) {

			int x , y, d;
			x = (int)data.getCoin(i).getX();
			y = (int)data.getCoin(i).getY();
			d = data.getCoin(i).getDimension();

			g.drawOval(x, y, d, d);
			g.fillOval(x, y, d, d);
		}

	}
	// TODO may need to move to GameData class or Pacman class

	public void drawGhosts(Graphics g) {
		g.setColor(Color.GREEN);

		for(int i = 0; i < data.getGhostList().size(); i++) {

			int x , y, d;

			x = (int)data.getGhost(i).getX();
			y = (int)data.getGhost(i).getY();
			d = data.getGhost(i).getDimension();

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
