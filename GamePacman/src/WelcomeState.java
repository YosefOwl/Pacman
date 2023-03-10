import java.awt.*;
import java.awt.event.KeyEvent;

public class WelcomeState extends GameState {

	private boolean active;
	public int numState = 0;

	public void enter(Object memento) {
		active = true;
	}

	public void processKeyReleased(int aKeyCode) {

		if (aKeyCode == KeyEvent.VK_DOWN) {
			numState++;
			if (numState > 2)
				numState = 0;
		}

		if (aKeyCode == KeyEvent.VK_UP) {
			numState--;
			if (numState < 0)
				numState = 2;
		}

		if (aKeyCode == KeyEvent.VK_ENTER) {
			if (numState == 0 || numState == 1)
				active = false;
			else
				System.exit(0);
		}
	}
	public boolean isActive() { return active; }

	public String next() {
		return "Play";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {

		Graphics g = aGameFrameBuffer.graphics();
		String text = "WELCOME";
		Font font = new Font("Serif", Font.BOLD, 88);
		g.setFont(font);
		//Shadow
		g.setColor(Color.pink);
		g.drawString(text, (Game.WIDTH/3-140+2), Game.HEIGHT/2+2);
		//Title
		g.setColor(Color.red);
		g.drawString(text, (Game.WIDTH/3-140), Game.HEIGHT/2);

		g.setColor(Color.yellow);
		g.drawOval(280, 300, 60, 60);
		g.fillOval(280, 300, 60, 60);

		//Menu
		String text2 = "START GAME";
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.setColor(Color.white);
		g.drawString(text2, 210, 420);
		if(numState==0){
			g.drawString(">", 185, 420 );
		}

		String text4 = "PACMAN TURBO";
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.setColor(Color.white);
		g.drawString(text4, 270, 465);
		if(numState==1){
			g.drawString(">", 245, 465 );
		}

		String text3 = "EXIT";
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.setColor(Color.white);
		g.drawString(text3, 210, 505);
		if(numState==2){
			g.drawString(">", 185, 505 );
		}
	}


	public Object memento() {
		return this;
	}

	public int getNumState() {
		return numState;
	}
}
