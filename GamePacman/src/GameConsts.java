import java.awt.*;

public final record GameConsts() {

    // Maze / Wall
    static final int MAZE_ROW = 15;
    static final int MAZE_COL = 26;
    static final int BLOCK_WIDTH = Game.WIDTH/MAZE_COL;
    static final int BLOCK_HEIGHT = Game.HEIGHT/MAZE_ROW;
    static final Color WALL_COLOR = Color.BLUE;


    // Directions
    static final int STOP = 0;
    static final int UP = 1;
    static final int DOWN = 2;
    static final int RIGHT = 3;
    static final int LEFT = 4;
    static final float DEFAULT_SPEED = 0.1f;


    // Pacman
    static final int PACMAN_LIFE = 3;
    static final int PACMAN_D = 16;
    static final int START_ROW_PAC = 1;
    static final int START_COL_PAC = 1;
    static final int PACMAN_X = (BLOCK_WIDTH*START_ROW_PAC + (BLOCK_WIDTH - PACMAN_D)/2) ;
    static final int PACMAN_Y = (BLOCK_HEIGHT*START_COL_PAC + (BLOCK_HEIGHT - PACMAN_D)/2) ;
    static final Color PACMAN_COLOR = Color.YELLOW;

    // Ghost
    static final int NUMBER_OF_GHOST = 6;
    static final int GHOST_D = 16;
    static final int START_ROW_G = 10;
    static final int START_COL_G = 7;
    static final int GHOST_X = (BLOCK_WIDTH*START_ROW_G + (BLOCK_WIDTH - GHOST_D)/2);
    static final int GHOST_Y = (BLOCK_HEIGHT*START_COL_G + (BLOCK_HEIGHT - GHOST_D)/2);

    //Coins
    static final Color COIN_COLOR = Color.ORANGE;
    static final Color S_COIN_COLOR = Color.GREEN;
    static final int COIN_DIMENSION = 4;
    static final int S_COIN_DIMENSION = 7;
    public static final int COIN_VALUE = 1;
    public static final int S_COIN_VALUE = 50;

}
