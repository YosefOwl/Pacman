import javax.swing.*;
import java.awt.*;

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
					this.mazeData[i][j].setWall(true);
				}else {
					float yCoin = (i * BLOCK_HEIGHT) + (BLOCK_HEIGHT / 2) - (Coin.DIMENSION / 1f);
					float xCoin = (j * BLOCK_WIDTH)  + (BLOCK_WIDTH / 2) - (Coin.DIMENSION / 1f);
					Coin coin = new Coin((int)xCoin, (int)yCoin);
					this.mazeData[i][j].getCharacters().add(coin);
				}
			}
		}
	}

	public void render(Graphics g) {
		var maze = getMap();
		for (int row = 0; row < mazeData.length; row++) {
			for (int col = 0; col < mazeData[row].length; col++) {
				if (mazeData[row][col].isWall()) {
					g.setColor(Color.BLACK);
					g.fillRect(col * (int) (BLOCK_WIDTH), row * (int) (BLOCK_HEIGHT), (int) (BLOCK_WIDTH),
							(int) (BLOCK_HEIGHT));
					g.setColor(Color.WHITE);
					g.drawRect(col * (int) (BLOCK_WIDTH), row * (int) (BLOCK_HEIGHT), (int) (BLOCK_WIDTH),
							(int) (BLOCK_HEIGHT));
				}
				else {
					var optionalCoin = mazeData[row][col]
							.getCharacters()
							.stream()
							.filter(c -> c.isActive() && c.getStereotype().equals(Stereotype.eCoin))
							.findFirst();
					if(!optionalCoin.isEmpty())
					{
						var coin = optionalCoin.get();
						g.setColor(Color.PINK);
						g.drawOval(coin.getPosition().x,coin.getPosition().y, coin.dimension, coin.dimension);
						g.fillOval(coin.getPosition().x,coin.getPosition().y, coin.dimension, coin.dimension);
					}

				}
			}
		}
	}

	public MazeData[][] getMap() {
		return mazeData;
	}

	public void setCharacterInPosition(Character character){

		int col = character.getPosition().x / ((int)BLOCK_WIDTH);
		int row = character.getPosition().y / ((int)BLOCK_HEIGHT);

		if(!mazeData[row][col].getCharacters().contains(character)) {
			mazeData[row][col]
					.getCharacters()
					.add(character);
		}

	}
}
