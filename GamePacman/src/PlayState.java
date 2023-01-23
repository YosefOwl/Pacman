import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

public class PlayState extends GameState {

	private boolean active;
	private int scores;
	private Ghost ghost;
	private Pacman pacman;
	private Maze maze;
	private List<Coin> coins;

	public PlayState() {
		ghost = new Ghost(0.1f, 20, 20);
		pacman = new Pacman(0.1f, 26, 35);
		maze = new Maze();
		coins = new Vector<Coin>();
		drawCoins();
		scores = 0;
		
	}

	private void drawCoins() {
		int[][] m = maze.getMap();
		float xCoin, yCoin;
		for(int row=0; row < m.length; row++) {
			for(int col =0; col < m[row].length; col++) {
				if(m[row][col]==0) {
					xCoin = (col * maze.BLOCK_WIDTH) + (maze.BLOCK_WIDTH / 2) - (Coin.DIMENSION / 1f);
					yCoin = (row * maze.BLOCK_HEIGHT)  + (maze.BLOCK_HEIGHT / 2) - (Coin.DIMENSION / 1f);
					coins.add(new Coin(0, xCoin, yCoin));
				}
			}
		}
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
		putCoins(g);
		drawPacman(g);
	//	g.setColor(Color.white);
	//	g.drawOval((int)pacman.getX(), (int)pacman.getY(), pacman.getDimension(), pacman.getDimension());
	//	g.drawRect((int)ghost.getX(), (int)ghost.getY(), ghost.getDimension(), ghost.getDimension());
	//	String message = "Scores : " + scores;
	//	g.drawString(message, 10, 10);
	}

	private void drawPacman(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawOval((int)pacman.getX(), (int)pacman.getY(), pacman.getDimension(), pacman.getDimension());
		g.fillOval((int)pacman.getX(), (int)pacman.getY(), pacman.getDimension(), pacman.getDimension());
	}

	private void putCoins(Graphics g) {
		g.setColor(Color.PINK);
		for(int i=0; i<coins.size(); i++) {
			g.drawOval((int)coins.get(i).getX(), (int)coins.get(i).getY(), coins.get(i).dimension, coins.get(i).dimension);
			g.fillOval((int)coins.get(i).getX(), (int)coins.get(i).getY(), coins.get(i).dimension, coins.get(i).dimension);
		}		
	}

	public Object memento() {
		return this;
	}

	public int getScores() {
		return scores;
	}
}
