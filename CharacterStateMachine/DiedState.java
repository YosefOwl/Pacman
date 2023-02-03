import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DiedState implements ICharacterState {
    @Override
    public void onStateEnter(Map<String, Object> handlerArguments) {
        System.out.println("In died state");
        Pacman pacman = (Pacman) handlerArguments.get("character");
        pacman.decreaseLife();
        pacman.setPosition(new Point(GameConsts.PACMAN_X,GameConsts.PACMAN_Y));
        pacman.setLastPosition(new Point(pacman.getPosition()));
        pacman.stateMachine.MakeTransition(handlerArguments,"explorer");
    }

    @Override
    public boolean onStateExit(Map<String, Object> handlerArguments) {
        return true;
    }

    @Override
    public void handleState(Map<String, Object> handlerArguments) {
    }
}
