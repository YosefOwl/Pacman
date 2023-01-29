import java.awt.*;
import java.util.ArrayList;

public class MazeData {
    private ArrayList<ICollisional> collisional = new ArrayList<>();
    private Color color;
    private boolean isWall;

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public ArrayList<ICollisional> getCollisional() {
        return collisional;
    }
    public void setCollisional(ArrayList<ICollisional> collisional) {
        this.collisional = collisional;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
