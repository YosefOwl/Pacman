import java.util.Map;

public abstract class StateHandler {
    abstract void handleState(Map<String,Object> handlerArguments);
}
