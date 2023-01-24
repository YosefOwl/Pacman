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

    static final int GHOST_X = (int)(Maze.BLOCK_WIDTH*10 + Maze.BLOCK_WIDTH/4.8);
    static final int GHOST_Y = (int)(Maze.BLOCK_HEIGHT*7 + Maze.BLOCK_HEIGHT/6.4);
    static final int GHOST_D = 15;
    static final int PACMAN_X = (int)(Maze.BLOCK_WIDTH*1 + Maze.BLOCK_WIDTH/4.8);
    static final int PACMAN_Y = (int)(Maze.BLOCK_HEIGHT*1 + Maze.BLOCK_HEIGHT/6.4);;
    static final int PACMAN_D = 15;


    private static GameData gameDataInstance;

    private int gameLevel;
    private int score;
    private int gameLife;
    private int ghostNum;
    private int ghostCollision;

    private ArrayList<Ghost> ghostList;
    private List<Coin> coins;

    private Pacman pacman;
    private Maze maze;


    private GameData() {
        // TODO values shall be final
        score = 0;
        gameLife = LIFE;
        ghostNum = NUMBER_OF_GHOST;
        ghostCollision = 0;
        gameLevel = 0;
        pacman = new Pacman(DEFAULT_SPEED, PACMAN_X, PACMAN_Y, PACMAN_D);
        maze = new Maze();
        coins = new Vector<Coin>();
        putCoins();
        initGhosts();
    }

    public static GameData getInstance() {

        if (gameDataInstance == null)
            gameDataInstance = new GameData();
        return gameDataInstance;
    }

    private void putCoins() {

        int[][] m = maze.getMap();
        float xCoin, yCoin;

        for(int row = 0; row < m.length; row++) {

            for(int col = 0; col < m[row].length; col++) {

                if(m[row][col] == 0) {

                    xCoin = (col * maze.BLOCK_WIDTH) + (maze.BLOCK_WIDTH / 2) - (Coin.DIMENSION / 1f);
                    yCoin = (row * maze.BLOCK_HEIGHT)  + (maze.BLOCK_HEIGHT / 2) - (Coin.DIMENSION / 1f);
                    coins.add(new Coin(0, xCoin, yCoin));

                }
            }
        }
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

    public int getGhostNum() {
        return ghostNum;
    }

    public void setGhostNum(int ghostNum) {
        this.ghostNum = ghostNum;
    }

    public int getGhostCollision() {
        return ghostCollision;
    }

    public void setGhostCollision(int ghostCollision) {
        this.ghostCollision = ghostCollision;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public Coin getCoin(int index){

        if (index < coins.size())
            return coins.get(index);

        Coin emptyCoin = new Coin(0, 0, 0);
        emptyCoin.setDimension(0);
        return emptyCoin;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }



    private void initGhosts() {
        int x = GHOST_X;
        int y = GHOST_Y;

        ghostList = new ArrayList<>();
        for (int i = 0; i < ghostNum ; i++) {



            // add 3 CrazyGhost and 3 Ghost
            if (i > 2)
                ghostList.add(new CrazyGhost(DEFAULT_SPEED, x, y, GHOST_D));
            else
                ghostList.add(new Ghost(DEFAULT_SPEED, x, y, GHOST_D));

            x += Maze.BLOCK_WIDTH;
        }

    }

    public ArrayList<Ghost> getGhostList() {
        return ghostList;
    }

    public void setGhostList(ArrayList<Ghost> ghostList) {
        this.ghostList = ghostList;
    }

    public Ghost getGhost(int index) {

        if (index < ghostList.size())
            return ghostList.get(index);

        Ghost emptyGhost = new Ghost(0, 0, 0, 0);
        return emptyGhost;
    }
}
