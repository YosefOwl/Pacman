public class DiedState implements ICharacterState {
    private StateHandler stateHandler;
    public DiedState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void onStateEnter() {
        System.out.println("In died state");
    }

    @Override
    public void onStateExit() {

    }


    @Override
    public StateHandler getStateHandler() {
        return stateHandler;
    }
}
