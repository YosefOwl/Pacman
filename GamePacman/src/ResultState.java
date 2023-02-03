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
			if (data.getLife() == GameConsts.PACMAN_LIFE && !data.hasNextLevel())
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

//		g.drawRect(widthFrame/2 , 1, 1, heightFrame);
//		g.drawRect(1 , heightFrame/2, widthFrame, 1);

		// draw status
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 48));
		int textWidthStatus = g.getFontMetrics().stringWidth(statusGame);
		g.drawString(statusGame, (widthFrame - textWidthStatus)/2, heightFrame/4);

		// draw result
		g.setColor(Color.CYAN);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 28));

		int textWidthSc = g.getFontMetrics().stringWidth(scores);
		int textWidthL = g.getFontMetrics().stringWidth(lastLevel);
		int fontHeight = g.getFontMetrics().getHeight();

		g.drawString(lastLevel, (widthFrame - textWidthL)/2, heightFrame/2 + fontHeight);
		g.drawString(scores, (widthFrame - textWidthSc)/2, heightFrame/2 + fontHeight*2);
//
//		g.setColor(Color.WHITE);
//		g.drawString(PLAY_AGAIN, widthFrame/2, heightFrame*2 + fontHeight*3);
//		g.drawString(EXIT, widthFrame/2, (int)(heightFrame*0.9f) + fontHeight*4);

	}

}
