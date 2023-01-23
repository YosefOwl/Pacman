
public class Coin extends Character{

	static final int VALUE = 1;
	private boolean eaten;
	
	public Coin(float speed, float x, float y) {
		super(speed, x, y); //x=0, y=0
	}
	
	public void setState(boolean state) {
		this.eaten = state;
	}
	
	public boolean getState() {
		return this.eaten;
	}
}
