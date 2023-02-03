import java.awt.*;
import java.awt.event.KeyEvent;

public class ResultState extends GameState {

	final String PLAY_AGAIN = "PRESS 'P' TO PLAY AGAIN";
	final String EXIT = "PRESS 'ESC' TO EXIT";
	final String GAME_OVER = "GAME OVER";
	final String GAME_WIN = "GAME WIN";
	final String SCORES_TXT = "Scores : ";
	final String LEVEL_TXT = "Your level : ";

	private String scoreTxt;
	private String lvlTxt;
	private String statusGame;

	private String nextState;
	private boolean active;

	public ResultState() {

	}
	
	public void enter(Object memento) {

		scoreTxt = SCORES_TXT;
		lvlTxt = LEVEL_TXT;

		if (memento.getClass().equals(PlayState.class)) {
			Pacman p = ((PlayState)memento).getPacman();
			GameData data = ((PlayState)memento).getData();

			scoreTxt = scoreTxt + data.getScore()*data.getLevel(); //TODO
			lvlTxt = lvlTxt + data.getLevel();

			if (p.getLife() == 0)
				statusGame = GAME_OVER;
			else
				statusGame = GAME_WIN;
		}

		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {

		if (aKeyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
			active = false;
		}

		if (aKeyCode == KeyEvent.VK_P) {
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

		// draw statusGame
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 48));
		textWidth = g.getFontMetrics().stringWidth(statusGame);
		g.drawString(statusGame, (widthFrame - textWidth)/2, heightFrame/4);


		g.setColor(Color.CYAN);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
		int fontHeight = g.getFontMetrics().getHeight();

		// draw scores
		textWidth = g.getFontMetrics().stringWidth(lvlTxt);
		g.drawString(lvlTxt, (widthFrame - textWidth)/2, heightFrame/2 + fontHeight);

		// draw score
		textWidth = g.getFontMetrics().stringWidth(scoreTxt);
		g.drawString(scoreTxt, (widthFrame - textWidth)/2, heightFrame/2 + fontHeight*2);


		// draw instruction
		g.setColor(Color.WHITE);
		textWidth = g.getFontMetrics().stringWidth(PLAY_AGAIN);
		g.drawString(PLAY_AGAIN, (widthFrame - textWidth)/2, heightFrame - fontHeight*2);
		textWidth = g.getFontMetrics().stringWidth(EXIT);
		g.drawString(EXIT, (widthFrame - textWidth)/2, heightFrame - fontHeight/2);

	}

}
