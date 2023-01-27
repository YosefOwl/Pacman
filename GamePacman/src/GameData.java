import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GameData {

    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int UP = 3;
    static final int DOWN = 4;
    static final int LIFE = 3;

    static final float DEFAULT_SPEED = 0.1f;

    static final int NUMBER_OF_GHOST = 6;
    static final int GHOST_D = 15;
    static final int GHOST_X = (int)(Maze.BLOCK_WIDTH*10 + (Maze.BLOCK_WIDTH-GHOST_D)/2);
    static final int GHOST_Y = (int)(Maze.BLOCK_HEIGHT*7 + (Maze.BLOCK_HEIGHT-GHOST_D)/2);

    static final int PACMAN_D = 15;
    static final int PACMAN_X = ((int)(Maze.BLOCK_WIDTH*1) + ((int)Maze.BLOCK_WIDTH-PACMAN_D)/2);
    static final int PACMAN_Y = ((int)(Maze.BLOCK_HEIGHT*1) + ((int)Maze.BLOCK_HEIGHT-PACMAN_D)/2);;


    private static GameData gameDataInstance;

    private int gameLevel;
    private int score;
    private int gameLife;
    private int ghostNum;

    private GameData() {
        // TODO values shall be final
        score = 0;
        gameLife = LIFE;
        ghostNum = NUMBER_OF_GHOST;
        gameLevel = 0;
    }

    public static GameData getInstance() {

        if (gameDataInstance == null)
            gameDataInstance = new GameData();
        return gameDataInstance;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameLife() {
        return gameLife;
    }

    public void setGameLife(int gameLife) {
        this.gameLife = gameLife;
    }

}
