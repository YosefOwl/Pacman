import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GameData {

    static final int LIFE = 3;
    private static GameData gameDataInstance;

    private int gameLevel;
    private int score;
    private int gameLife;

    private GameData() {
        // TODO values shall be final
        score = 0;
        gameLife = LIFE;
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
