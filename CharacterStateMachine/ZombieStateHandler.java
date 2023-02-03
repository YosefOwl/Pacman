import java.awt.*;
import java.util.Map;

public class ZombieStateHandler extends StateHandler {

    @Override
    public void handleState(Map<String, Object> handlerArguments) {
        Pacman pacman = (Pacman) handlerArguments.get("character");

        if(pacman.getColor() == Color.YELLOW){
            pacman.setColor(Color.WHITE);
        }
        else{
            pacman.setColor(Color.YELLOW);
        }
    }
}
