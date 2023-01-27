import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class MazeData {
    private ArrayList<ICollisional> collisionals = new ArrayList<>();
    private Color color;
    private boolean isWall;

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public ArrayList<ICollisional> getCollisionals() {
        return collisionals;
    }
    public void setCollisionals(ArrayList<ICollisional> collisionals) {
        this.collisionals = collisionals;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
