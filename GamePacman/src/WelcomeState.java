import java.awt.*;
import java.awt.event.KeyEvent;

public class WelcomeState extends GameState {

	private boolean active;

	public void enter(Object memento) {
		active = true;
	}

	public void processKeyReleased(int aKeyCode) {

		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		active = false;
	}

	public boolean isActive() { return active; }

	public String next() {
		return "Play";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {

		Graphics g = aGameFrameBuffer.graphics();
		String text = "PRESS ANY KEY TO PLAY";
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.setColor(Color.white);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth)/2, aGameFrameBuffer.getHeight()/2);
	}

	public Object memento() {
		return this;
	}
}
