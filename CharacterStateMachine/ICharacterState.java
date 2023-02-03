public interface ICharacterState {

    void onStateEnter();
    void onStateExit();
    IStateHandler getStateHandler();
 }

