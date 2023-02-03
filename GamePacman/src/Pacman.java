import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pacman extends DynamicCharacter {
	private int life;
	private List<Coin> coins = new ArrayList<>();
	private boolean isObscure;


	public Pacman(float speed, int x, int y,CharacterStateMachine stateMachine) {
		super(speed, x, y, stateMachine);
		this.life = GameConsts.PACMAN_LIFE;
		setSpeed(speed);
		setActive(true);
		setStereotype(Stereotype.ePacman);

		setDimension(new Dimension(GameConsts.PACMAN_D, GameConsts.PACMAN_D));
		lastPosition = new Point(getPosition());

		setDirection(GameConsts.STOP);
		lastDirection = direction;
		setColor(GameConsts.PACMAN_COLOR);
	}

	public void move(long deltaTime) {

		int vt = (int) (speed*deltaTime);
		setLastPosition(new Point(this.getPosition()));

		dx = 0;
		dy = 0;

		if (nextMoveCounterX > 0)
			accuracyMoveX(vt);
		else if (nextMoveCounterY > 0)
			accuracyMoveY(vt);
		else if (isDirectionOnAxisX())
			moveOnAxisX(vt);
		else if (isDirectionOnAxisY())
			moveOnAxisY(vt);

		translatePosition(dx, dy);
	}

	@Override
	public void onCollisionEnter(ICollisional other) {

		Character c = other.getCharacter();

		if (c.getStereotype().equals(Stereotype.eWall))
			handleWallCollision();

		if (c.getStereotype().equals(Stereotype.eCoin)) {
			if (c.isActive() && !this.isObscure()) {
				coins.add( (Coin)c);
			}
		}

		else if(c.getStereotype().equals(Stereotype.eSpecCoin)){
			if (c.isActive() && !this.isObscure()) {
				coins.add((SpecialCoin)c);
			}
		}

		if (c.getStereotype().equals(Stereotype.eGhost))
			handleGhostCollision();

	}

	private void handleGhostCollision() {
		var transitionArguments = new HashMap<String,Object>();
		transitionArguments.put("character",this);
		System.out.println("Sent ghost hit trigger");
		this.getStateMachine().MakeTransition(transitionArguments,"ghostHit");
	}

	private void handleWallCollision() {

		if (position.y == lastPosition.y && position.x == lastPosition.x)
			return;

		if (position.y != lastPosition.y && position.x != lastPosition.x) {
			nextMoveCounterY = 0;
			nextMoveCounterX = 0;
		}

		if (position.y != lastPosition.y)
			nextMoveCounterY = 0;

		if (position.x != lastPosition.x)
			nextMoveCounterX = 0;

		direction = GameConsts.STOP;
		setPosition(new Point(this.getLastPosition()));
	}

	@Override
	public Character getCharacter() {
		return this;
	}

	@Override
	public Shape getCollider() {

		int relativeX = position.x + (dimension.width - GameConsts.BLOCK_WIDTH)/2 + 1;
		int relativeY = position.y + (dimension.height - GameConsts.BLOCK_HEIGHT)/2 + 1;

		int dimX = GameConsts.BLOCK_WIDTH  - 2;
		int dimY = GameConsts.BLOCK_HEIGHT - 2;

		return new Rectangle( new Point(relativeX, relativeY), new Dimension(dimX, dimY) );
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

	@Override
	public void executeStateBehavior(long deltaTime) {

		var handlerArguments = new HashMap<String,Object>();
		handlerArguments.put("deltaTime",deltaTime);
		handlerArguments.put("character",this);

		stateMachine.getCurrentState().handleState(handlerArguments);
	}

	public int getCoinsSize() {
		return coins.size();
	}

	public int checkScore(){
		int s = 0;

		for(Coin c : coins){
			s += c.getCoinVal();
		}
		return s;
	}
	public void draw(Graphics g) {

		g.setColor(this.color);

		g.drawOval(position.x, position.y, dimension.width, dimension.height);
		g.fillOval(position.x, position.y, dimension.width, dimension.height);
	}

	public int getLife() {
		return life;
	}

	public void decreaseLife() {
		this.life++;
	}

	public boolean isObscure() {
		return isObscure;
	}

	public void setObscure(boolean obscure) {
		this.isObscure = obscure;
	}
}
