import java.awt.*;
import java.util.Map;

public class DyingState implements ICharacterState{
    private long timer;

    @Override
    public void onStateEnter(Map<String, Object> handlerArguments) {
        System.out.println("In dying state");
        timer = System.currentTimeMillis() + 10000;
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

        if(System.currentTimeMillis() < timer){
            if(pacman.getColor() == Color.YELLOW){
                pacman.setColor(Color.RED);
            }
            else{
                pacman.setColor(Color.YELLOW);
            }
        }
        else{
            pacman.getStateMachine().MakeTransition(handlerArguments,"explorer");
        }

    }
}
