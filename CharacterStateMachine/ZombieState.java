public class ZombieState implements ICharacterState{
    private StateHandler stateHandler;
    public ZombieState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void onStateEnter() {
        System.out.println("In zombie state");
    }

    @Override
    public void onStateExit() {
    }
    @Override
    public StateHandler getStateHandler() {
        return stateHandler;
    }
}
