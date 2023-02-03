public class ExploringState implements ICharacterState {
    private StateHandler stateHandler;
    public ExploringState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void onStateEnter() {
        System.out.println("In exploring state");
    }

    @Override
    public void onStateExit() {
    }

    @Override
    public StateHandler getStateHandler() {
        return stateHandler;
    }

}
