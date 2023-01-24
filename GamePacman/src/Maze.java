import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Maze extends JPanel {

	static final int MAZE_ROW = 15;
	static final int MAZE_COL = 26;
	static final float BLOCK_WIDTH = Game.WIDTH / (float) MAZE_COL;
	static final float BLOCK_HEIGHT = Game.HEIGHT / (float) MAZE_ROW;
	private static int[][] mazeData;

	public Maze() {
		mazeData = new int[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	}

	public void render(Graphics g) {

		for (int row = 0; row < mazeData.length; row++) {
			for (int col = 0; col < mazeData[row].length; col++) {
				if (mazeData[row][col] == 1) {
					g.setColor(Color.BLACK);
					g.fillRect(col * (int) (BLOCK_WIDTH), row * (int) (BLOCK_HEIGHT), (int) (BLOCK_WIDTH),
							(int) (BLOCK_HEIGHT));
					g.setColor(Color.WHITE);
					g.drawRect(col * (int) (BLOCK_WIDTH), row * (int) (BLOCK_HEIGHT), (int) (BLOCK_WIDTH),
							(int) (BLOCK_HEIGHT));
				}
			}
		}
	}

	public static boolean collision(int w, int h, float x, float y) {
		int leftRowTop = (int) (y / BLOCK_HEIGHT);
		int leftColTop = (int) (x / BLOCK_WIDTH);
		int leftRowBottom = (int) (((y + h)) / BLOCK_HEIGHT);
		int leftColBottom = (int) (x / BLOCK_WIDTH);
		int rightRowTop = (int) (y / BLOCK_HEIGHT);
		int rightColTop = (int) ((x + w) / BLOCK_WIDTH);
		int rightRowBottom = (int) (((y + h)) / BLOCK_HEIGHT);
		int rightColBottom = (int) ((x + w) / BLOCK_WIDTH);
		int[][] m = getMap();
		if (m[leftRowBottom][leftColBottom] == 0 && m[rightRowBottom][rightColBottom] == 0
				&& m[leftRowTop][leftColTop] == 0 && m[rightRowTop][rightColTop] == 0)
			return true;
		else
			return false;

	}

	public static int[][] getMap() {
		return mazeData;
	}
}
