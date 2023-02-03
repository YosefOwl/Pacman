import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

public class Maze extends JPanel {

	static final int FRAME_W = GameConsts.BLOCK_WIDTH;
	static final int FRAME_H = GameConsts.BLOCK_HEIGHT;
	static final int COIN_CENTER_X = FRAME_W/2 - GameConsts.COIN_DIMENSION;
	static final int COIN_CENTER_Y = FRAME_H/2 - GameConsts.COIN_DIMENSION;

	private MazeData[][] mazeData;
	private int coinCount = 0;

	public Maze(int[][] mazeSketch) {
		initMaze(mazeSketch);
	}
	private void initMaze(int[][] mazeSketch) {

		// int mazeSketch[][]; // 1/0  represent wall/coin
		int xCord, yCord;
		Character character = null;

		mazeData = new MazeData[mazeSketch.length][mazeSketch[0].length];
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[i].length; j++) {

				mazeData[i][j] = new MazeData();
				yCord = i * FRAME_H;
				xCord = j * FRAME_W;

				if (mazeSketch[i][j] == 1) {
					character = new MazeWall( xCord, yCord);
				}
				else if (mazeSketch[i][j] == 0){
					character = new Coin(xCord + COIN_CENTER_X, yCord + COIN_CENTER_Y);
					coinCount++;
				}
				else if (mazeSketch[i][j] == 2){
					character = new SpecialCoin(xCord + COIN_CENTER_X, yCord + COIN_CENTER_Y);
					coinCount++;
				}

				mazeData[i][j].getCollisional().add(character);
			}
		}
	}

	public int getCoinCount() {
		return coinCount;
	}

	public void drawMaze(Graphics graphics) {

		for (MazeData[] md : mazeData) {
			Arrays.asList(md).stream().forEach(ele -> {
				for (ICollisional c : ele.getCollisional()) {
					if (c.getCharacter() != null && c.getCharacter().isActive()) {
						c.getCharacter().draw(graphics);
					}
				}
			});
		}
	}

	public MazeData getMazeDataAtPosition (Point position) {
		return mazeData[position.x][position.y];
	}

	public void setCharacterInPosition(DynamicCharacter character) {

		int col = character.getPosition().x / GameConsts.BLOCK_WIDTH;
		int row = character.getPosition().y / GameConsts.BLOCK_HEIGHT;

		int lastCol = character.getLastPosition().x / GameConsts.BLOCK_WIDTH;
		int lastRow = character.getLastPosition().y / GameConsts.BLOCK_HEIGHT;

		if(!mazeData[row][col].getCollisional().contains(character)) {

			mazeData[lastRow][lastCol].getCollisional().remove(character);
			mazeData[row][col].getCollisional().add(character);
		}
	}

}
