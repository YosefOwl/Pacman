import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.JPanel;

public class Maze extends JPanel {
	static final int MAZE_ROW = 15;
	static final int MAZE_COL = 26;
	static final float BLOCK_WIDTH = Game.WIDTH / (float) MAZE_COL;
	static final float BLOCK_HEIGHT = Game.HEIGHT / (float) MAZE_ROW;
	private static MazeData	[][] mazeData;

	public Maze() {
		initMaze();
	}

	private void initMaze(){
		var mazeSketch = new int[][]
				{
						{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
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
						{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
				};

		this.mazeData = new MazeData[mazeSketch.length][mazeSketch[0].length];

		for (int i = 0; i < mazeSketch.length; i++) {
			for (int j = 0; j < mazeSketch[i].length; j++) {

				this.mazeData[i][j] = new MazeData();
				if (mazeSketch[i][j] == 1) {
					this.mazeData[i][j].setColor(Color.BLACK);
				} else {
					this.mazeData[i][j].setColor(Color.WHITE);
					float xCoin = (i * BLOCK_WIDTH) + (BLOCK_WIDTH / 2) - (Coin.DIMENSION / 1f);
					float yCoin = (j * BLOCK_HEIGHT)  + (BLOCK_HEIGHT / 2) - (Coin.DIMENSION / 1f);
					Coin coin = new Coin(0, xCoin, yCoin);
					this.mazeData[i][j].getCharacters().add(coin);

				}
			}
		}
	}

	public void render(Graphics g) {
		var maze = getMap();
		for (int row = 0; row < mazeData.length; row++) {
			for (int col = 0; col < mazeData[row].length; col++) {
				if (mazeData[row][col].getColor().equals(Color.BLACK)) {
					g.setColor(Color.BLACK);
					g.fillRect(col * (int) (BLOCK_WIDTH), row * (int) (BLOCK_HEIGHT), (int) (BLOCK_WIDTH),
							(int) (BLOCK_HEIGHT));
					g.setColor(Color.WHITE);
					g.drawRect(col * (int) (BLOCK_WIDTH), row * (int) (BLOCK_HEIGHT), (int) (BLOCK_WIDTH),
							(int) (BLOCK_HEIGHT));

					var optionalCoin = mazeData[row][col]
							.getCharacters()
							.stream()
							.filter(c -> c.isActive() && c.getStereotip().equals(Stereotip.eCoin))
							.findFirst();
					if(!optionalCoin.isEmpty())
					{
						var coin = optionalCoin.get();

						g.setColor(Color.PINK);
						g.drawOval((int)coin.getX(),(int)coin.getY(), coin.dimension, coin.dimension);
						g.fillOval((int)coin.getX(), (int)coin.getY(), coin.dimension, coin.dimension);
					}
				}
			}
		}
	}

		// gBuffer.setColor(Color.yellow);
		// gBuffer.drawOval(50, 50, 20, 20);
		// String message = "Scores : 3" ;
		// gBuffer.setColor(Color.pink);
		// gBuffer.drawString(message, 100, 100);


	public static boolean collision(int w, int h, float x, float y) {
		int leftRowTop = (int) (y / BLOCK_HEIGHT);
		int leftColTop = (int) (x / BLOCK_WIDTH);
		int leftRowBottom = (int) (((y + h)) / BLOCK_HEIGHT);
		int leftColBottom = (int) (x / BLOCK_WIDTH);
		int rightRowTop = (int) (y / BLOCK_HEIGHT);
		int rightColTop = (int) ((x + w) / BLOCK_WIDTH);
		int rightRowBottom = (int) (((y + h)) / BLOCK_HEIGHT);
		int rightColBottom = (int) ((x + w) / BLOCK_WIDTH);
//		int[][] m = getMap();
//		if (m[leftRowBottom][leftColBottom] == 0 && m[rightRowBottom][rightColBottom] == 0
//				&& m[leftRowTop][leftColTop] == 0 && m[rightRowTop][rightColTop] == 0)
//			return true;
//		else
//			return false;
		return true;
	}

	public MazeData[][] getMap() {
		return mazeData;
	}

	public void setCharacterInPosition(Character character){
		int x = character.getPosition().x%15;
		int y = character.getPosition().y%26;

		try{
			if(!mazeData[x][y].getCharacters().contains(character))
			{
				mazeData[x][y]
						.getCharacters()
						.add(character);
			}

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
