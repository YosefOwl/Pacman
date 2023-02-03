import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class ResultState extends GameState {

	final String PLAY_AGAIN = "PRESS 'P' TO PLAY AGAIN";
	final String EXIT = "PRESS 'Esc' TO EXIT";
	final String GAME_OVER = "GAME OVER";
	final String GAME_WIN = "GAME WIN";

	private String scores = "Scores : ";
	private String lastLevel = "Last level : ";
	private String statusGame;

	private String nextState;
	private boolean active;

	public ResultState() {

	}
	
	public void enter(Object memento) {

		if (memento.getClass().equals(PlayState.class)) {
			GameData data = ((PlayState)memento).getData();

			scores = scores + data.getScore()*data.getLevel();
			lastLevel = lastLevel + data.getLife();
			if (data.getLife() == GameData.LIFE && !data.hasNextLevel())
				statusGame = GAME_WIN;
			else
				statusGame = GAME_OVER;
		}


		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {

		if (aKeyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
			active = false;
		}

		if (aKeyCode == KeyEvent.VK_P) {
			nextState = "Play";
			active = false;
		}

		if (aKeyCode == KeyEvent.VK_M) {
			nextState = "Welcome";
			active = false;
		}

	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return nextState;
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();

		int widthFrame = aGameFrameBuffer.getWidth();
		int heightFrame = aGameFrameBuffer.getHeight();

		int textWidth;
//		g.drawRect(widthFrame/2 , 1, 1, heightFrame);
//		g.drawRect(1 , heightFrame/2, widthFrame, 1);

		// draw statusGame
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 48));
		textWidth = g.getFontMetrics().stringWidth(statusGame);
		g.drawString(statusGame, (widthFrame - textWidth)/2, heightFrame/4);


		g.setColor(Color.CYAN);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
		int fontHeight = g.getFontMetrics().getHeight();

		// draw scores
		textWidth = g.getFontMetrics().stringWidth(lastLevel);
		g.drawString(lastLevel, (widthFrame - textWidth)/2, heightFrame/2 + fontHeight);
		textWidth = g.getFontMetrics().stringWidth(scores);
		g.drawString(scores, (widthFrame - textWidth)/2, heightFrame/2 + fontHeight*2);

		g.setColor(Color.WHITE);
		textWidth = g.getFontMetrics().stringWidth(PLAY_AGAIN);
		g.drawString(PLAY_AGAIN, (widthFrame - textWidth)/2, heightFrame - fontHeight*2);
		textWidth = g.getFontMetrics().stringWidth(EXIT);
		g.drawString(EXIT, (widthFrame - textWidth)/2, heightFrame - fontHeight/2);

	}

}
