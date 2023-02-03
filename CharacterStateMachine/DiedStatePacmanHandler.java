import java.awt.*;
import java.util.Map;

public class DiedStatePacmanHandler extends StateHandler {
    @Override
    public void handleState(Map<String, Object> handlerArguments) {
        Pacman pacman = (Pacman) handlerArguments.get("character");
        pacman.decreaseLife();
        pacman.setPosition(new Point(GameConsts.PACMAN_X,GameConsts.PACMAN_Y));
        pacman.stateMachine.MakeTransition("explorer");
    }
}
