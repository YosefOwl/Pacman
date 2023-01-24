import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class MazeData {
    private ArrayList<Character> characters = new ArrayList<>();
    private Color color;
    private boolean isWall;
    public ArrayList<Character> getCharacters() {
        return characters;
    }
    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
