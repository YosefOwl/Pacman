import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ObscureState implements ICharacterState{
    private long timer;
    boolean canExitFromState = false;
    @Override
    public void onStateEnter(Map<String, Object> handlerArguments) {
        System.out.println("In obscure state");
        timer = System.currentTimeMillis() + 5000;
    }

    @Override
    public boolean onStateExit(Map<String, Object> handlerArguments) {
        return canExitFromState;
    }

    @Override
    public void handleState(Map<String, Object> handlerArguments) {

        Pacman pacman = (Pacman) handlerArguments.get("character");
        long deltaTime = (Long) handlerArguments.get("deltaTime");

        pacman.setObscure(true);
        pacman.move(deltaTime);

        if(System.currentTimeMillis() < timer){
            if(pacman.getColor() == GameConsts.PACMAN_COLOR){
                pacman.setColor(Color.WHITE);
            }
            else{
                pacman.setColor(GameConsts.PACMAN_COLOR);
            }
        }
        else{
            canExitFromState = true;
            pacman.getStateMachine().MakeTransition(new HashMap<>(),"dying");
        }
    }

}
