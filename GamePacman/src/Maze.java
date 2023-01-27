import java.awt.*;
import java.util.Optional;
import javax.swing.JPanel;

public class Maze extends JPanel {
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
					this.mazeData[i][j]
							.getCollisionals()
							.add(new MazeWall( (int)(j * GameConsts.BLOCK_WIDTH),(int)(i * GameConsts.BLOCK_HEIGHT) ) );
					this.mazeData[i][j].setWall(true);
				}else {
					int yCoin = (int)((i * GameConsts.BLOCK_HEIGHT) + (GameConsts.BLOCK_HEIGHT / 2) - (Coin.DIMENSION / 1f));
					int xCoin = (int)((j * GameConsts.BLOCK_WIDTH)  + (GameConsts.BLOCK_WIDTH / 2) - (Coin.DIMENSION / 1f));
					Coin coin = new Coin(xCoin, yCoin);
					this.mazeData[i][j].getCollisionals().add(coin);
				}
			}
		}
	}

	public void render(Graphics graphics) {
		var maze = getMap(); // TODO

		for (int row = 0; row < mazeData.length; row++) {
			for (int col = 0; col < mazeData[row].length; col++) {
				if (mazeData[row][col].isWall()) {
					drawWall(graphics, row, col);

					var wall = mazeData[row][col].getCollisionals()
							.stream()
							.filter(c -> c.getCharacter().getStereotype().equals(Stereotype.eWall))
							.findFirst()
							.get();

					graphics.setColor(Color.GREEN);
					graphics.drawRect(wall.getCollider().getBounds().x,
							wall.getCollider().getBounds().y,
							wall.getCollider().getBounds().width,
							wall.getCollider().getBounds().height
					);

				}
				else {
					Optional<ICollisional> optionalCoin = getCoinIfExist(mazeData[row][col]);
					if(!optionalCoin.isEmpty())
					{
						var coin = optionalCoin.get().getCharacter();
						drawCoin(graphics, coin);
					}

				}
			}
		}
	}

	private Optional<ICollisional> getCoinIfExist(MazeData mazeData) {
		var optionalCoin = mazeData
				.getCollisionals()
				.stream()
				.filter(c -> c.getCharacter() != null &&
							 c.getCharacter().isActive() &&
							 c.getCharacter().getStereotype().equals(Stereotype.eCoin))
				.findFirst();
		return optionalCoin;
	}

	private void drawCoin(Graphics g, Character coin) {
		g.setColor(Color.PINK);
		g.drawOval(coin.getPosition().x, coin.getPosition().y, coin.dimension.width, coin.dimension.height);
		g.fillOval(coin.getPosition().x, coin.getPosition().y, coin.dimension.width, coin.dimension.height);

		g.setColor(Color.ORANGE);
		g.drawRect(coin.getCollider().getBounds().x,
					coin.getCollider().getBounds().y,
				coin.getCollider().getBounds().width,
				coin.getCollider().getBounds().height);

	}

	private void drawWall(Graphics g, int row, int col) {
		g.setColor(Color.BLACK);
		g.fillRect(col * (int) (GameConsts.BLOCK_WIDTH), row * (int) (GameConsts.BLOCK_HEIGHT),
				(int) (GameConsts.BLOCK_WIDTH), (int) (GameConsts.BLOCK_HEIGHT));
		g.setColor(Color.WHITE);
		g.drawRect(col * (int) (GameConsts.BLOCK_WIDTH), row * (int) (GameConsts.BLOCK_HEIGHT),
				(int) (GameConsts.BLOCK_WIDTH),	(int) (GameConsts.BLOCK_HEIGHT));


	}

	public MazeData[][] getMap() {
		return mazeData;
	}

	public MazeData getMazeDataAtPosition (Point position) {
		return mazeData[position.x][position.y];
	}



	public void setCharacterInPosition(ICollisional character){

		int col = character.getPosition().x / ((int) GameConsts.BLOCK_WIDTH);
		int row = character.getPosition().y / ((int) GameConsts.BLOCK_HEIGHT);

		if(!mazeData[row][col].getCollisionals().contains(character)) {
			mazeData[row][col]
					.getCollisionals()
					.add(character);
		}

	}
}
