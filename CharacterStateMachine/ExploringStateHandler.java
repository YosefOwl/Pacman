import java.awt.*;
import java.util.Map;

public class ExploringStateHandler extends StateHandler {

    @Override
    public void handleState(Map<String, Object> handlerArguments) {
        DynamicCharacter pacman = (DynamicCharacter) handlerArguments.get("character");
        long deltaTime = (Long) handlerArguments.get("deltaTime");

        int vt = (int) (pacman.getSpeed()*deltaTime);
        pacman.setLastPosition(new Point(pacman.getPosition()));

        pacman.dx = 0;
        pacman.dy = 0;

        if (pacman.nextMoveCounterX > 0)
            pacman.accuracyMoveX(vt);
        else if (pacman.nextMoveCounterY > 0)
            pacman.accuracyMoveY(vt);
        else if (pacman.isDirectionOnAxisX())
            pacman.moveOnAxisX(vt);
        else if (pacman.isDirectionOnAxisY())
            pacman.moveOnAxisY(vt);

        pacman.translatePosition(pacman.dx, pacman.dy);
    }
}
