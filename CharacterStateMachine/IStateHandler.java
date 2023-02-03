import java.util.Map;

public interface IStateHandler {
    void handleState(Map<String,Object> handlerArguments);
}
