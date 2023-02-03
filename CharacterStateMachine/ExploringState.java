public class ExploringState implements ICharacterState {
    private IStateHandler stateHandler;
    public ExploringState(IStateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void onStateEnter() {
    }

    @Override
    public void onStateExit() {
    }

    @Override
    public IStateHandler getStateHandler() {
        return stateHandler;
    }

}
