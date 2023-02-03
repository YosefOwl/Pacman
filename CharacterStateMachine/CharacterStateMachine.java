import java.util.HashMap;
import java.util.Map;

public class CharacterStateMachine {
    private ICharacterState currentState;
    private Map<ICharacterState, Map<String,Transition>> transitionMap;

    public CharacterStateMachine(ICharacterState startingState) {
        this.currentState = startingState;
        this.transitionMap = new HashMap<>();
    }

    public void AddTransition(Transition transition){

        var stateTransitions =  transitionMap.get(transition.getFrom());

        if(stateTransitions == null){
            stateTransitions = new HashMap<>();
            stateTransitions.put(transition.getTrigger(),transition);
            transitionMap.put(transition.getFrom(),stateTransitions);
            return;
        }

        var stateOnTriggerTransition = stateTransitions.get(transition.getTrigger());
        if(stateOnTriggerTransition != null){
            throw new RuntimeException("Transition is already set from state : "
                    + transition.getFrom()
                    + "for on trigger : "
                    + transition.getTrigger()
                    + "\n please use overrideTransition instead"
            );
        }

        stateTransitions.put(transition.getTrigger(),transition);

    }

    public void MakeTransition(Map<String, Object> transitionParam, String trigger){
        var stateTransitions =  transitionMap.get(this.currentState);

        if(stateTransitions == null){
            return;
        }

        var transitionOnTrigger = stateTransitions.get(trigger);

        if(transitionOnTrigger == null){
            return;
        }

        boolean canExitFromState = currentState.onStateExit(new HashMap<>());

        if(canExitFromState){
            currentState =  transitionOnTrigger.getTo();

            currentState.onStateEnter(transitionParam);
        }
    }

    public ICharacterState getCurrentState() {
        return currentState;
    }
}
