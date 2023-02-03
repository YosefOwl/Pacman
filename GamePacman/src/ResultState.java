import java.awt.*;
import java.awt.event.KeyEvent;

public class ResultState extends GameState {

	private boolean active;
	private GameData gameData;

	public ResultState() {

		this.gameData = GameData.getInstance();

	}
	
	public void enter(Object memento) {
		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		active = false;

		if (aKeyCode == KeyEvent.VK_ENTER)
			System.exit(0);

		active = false;
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		//System.exit(0);
		return "Welcome";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {


		Graphics g = aGameFrameBuffer.graphics();
		String endGameTxt = "GAME ENDED";
		int textWidth = g.getFontMetrics().stringWidth(endGameTxt);
		Font font = new Font("Serif", Font.BOLD, 88);
		g.setFont(font);
		//Shadow
		g.setColor(Color.pink);
		g.drawString(endGameTxt, (Game.WIDTH/3-140+2), Game.HEIGHT/2+2);
		//Title
		g.setColor(Color.red);
		g.drawString(endGameTxt, (Game.WIDTH/3-140), Game.HEIGHT/2);

		g.setColor(Color.yellow);
		g.drawOval(280, 300, 60, 60);
		g.fillOval(280, 300, 60, 60);

		String resultTitleTxt = "GAME RESULT" ;
		String scoreTxt = "SCORES:  " + gameData.getScore();
		String levelTxt = "LEVEL:  " + gameData.getLevel();
		String lifeTxt = "LIFE:  " + gameData.getLife();
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.setColor(Color.white);
		g.drawString(resultTitleTxt, (aGameFrameBuffer.getWidth()-textWidth)/2, aGameFrameBuffer.getHeight()/2);
		g.drawString(scoreTxt, 210, 420);
		g.drawString(levelTxt, 210, 450);
		g.drawString(lifeTxt, 210, 470);


		String exitTxt = "PRESS ESCAPE/ENTER TO EXIT";
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.setColor(Color.white);
		g.drawString(exitTxt, 270, 465);


	}

	public Object memento(){
		return this;
	}
}
