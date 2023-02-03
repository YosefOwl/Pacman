public interface ICharacterState {

    void onStateEnter();
    void onStateExit();
    StateHandler getStateHandler();
 }

