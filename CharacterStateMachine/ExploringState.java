import java.awt.*;
import java.util.Map;

public class ExploringState implements ICharacterState {
    private boolean readyToTransition = false;
    private long timer;
    @Override
    public void onStateEnter(Map<String, Object> handlerArguments) {
        timer = System.currentTimeMillis() + 5000;
        readyToTransition = false;
        Pacman pacman = (Pacman)handlerArguments.get("character");
        if (pacman != null){
            pacman.setColor(GameConsts.PACMAN_COLOR);
        }
        System.out.println("In exploring state");
    }

    @Override
    public boolean onStateExit(Map<String, Object> handlerArguments) {
        return readyToTransition;
    }

    @Override
    public void handleState(Map<String, Object> handlerArguments) {
        if(System.currentTimeMillis() > timer){
            readyToTransition = true;
        }
        Pacman pacman = (Pacman) handlerArguments.get("character");
        long deltaTime = (Long) handlerArguments.get("deltaTime");

        pacman.setObscure(false);
        pacman.move(deltaTime);
    }
}
