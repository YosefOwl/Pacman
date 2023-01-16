
public class PacmanGame extends Game {

	public PacmanGame() {
		GameState welcome = new WelcomeState();
		GameState play = new PlayState();
		GameState result = new ResultState();

		stateMachine.installState("Play", play);
		stateMachine.installState("Welcome", welcome);
		stateMachine.installState("Result", result);

		stateMachine.setStartState(welcome);
	}

	public static void main( String[] args ) {
		Game app = new PacmanGame();
		app.setTitle( "Pacman Game" );
		app.setVisible( true );
		app.run();
		System.exit( 0 );
	}
}
