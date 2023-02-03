import java.util.Map;

public interface ICharacterState {

    void onStateEnter(Map<String, Object> handlerArguments);
    boolean onStateExit(Map<String, Object> handlerArguments);
    void handleState(Map<String, Object> handlerArguments);
 }

