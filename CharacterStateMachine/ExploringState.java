import java.awt.*;
import java.util.Map;

public class ExploringState implements ICharacterState {
    @Override
    public void onStateEnter(Map<String, Object> handlerArguments) {

        Pacman pacman = (Pacman)handlerArguments.get("character");
        if (pacman != null){
            pacman.setColor(GameConsts.PACMAN_COLOR);
        }
        System.out.println("In exploring state");
    }

    @Override
    public boolean onStateExit(Map<String, Object> handlerArguments) {
        return true;
    }

    @Override
    public void handleState(Map<String, Object> handlerArguments) {

        Pacman pacman = (Pacman) handlerArguments.get("character");
        long deltaTime = (Long) handlerArguments.get("deltaTime");

        pacman.setObscure(false);
        pacman.move(deltaTime);
    }
}
