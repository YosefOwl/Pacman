import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class MazeData {
    public ArrayList<Character> characters = new ArrayList<>();
    public Color color;
    public Collection<Character> getCharacters() {
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
