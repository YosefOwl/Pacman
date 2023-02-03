public class ZombieState implements ICharacterState{
    private IStateHandler stateHandler;
    public ZombieState(IStateHandler stateHandler) {
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
    public IStateHandler getStateHandler() {
        return stateHandler;
    }
}
