public final record GameConsts() {
    static final int MAZE_ROW = 15;
    static final int MAZE_COL = 26;
    static final float BLOCK_WIDTH = Game.WIDTH / (float) MAZE_COL;
    static final float BLOCK_HEIGHT = Game.HEIGHT / (float) MAZE_ROW;

    static final float PACMAN_SPEED = 0.1f;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = -1;
    static final int LEFT = -2;
    static final float DEFAULT_SPEED = 0.1f;
    static final int NUMBER_OF_GHOST = 6;
}
