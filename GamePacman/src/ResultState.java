import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class ResultState extends GameState {

	
	private boolean active;
	private int scores;
	private String message;
	
	public ResultState() {
		scores = 0;
	}
	
	public void enter(Object memento) {
		scores = (int)((PlayState) memento).getScores();
		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		active = false;
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return "Welcome";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		
		String text = "PRESS ANY KEY TO PLAY AGAIN";
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.setColor(Color.white);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth)/2, aGameFrameBuffer.getHeight()/2);

		message = "Scores: " + scores;
		g.drawString(message, 10, 10);
	}
}
