public class Transition {
    private ICharacterState From;
    private ICharacterState To;
    private String Trigger;


    public Transition(ICharacterState from, ICharacterState to, String trigger) {
        From = from;
        To = to;
        Trigger = trigger;
    }

    public ICharacterState getFrom() {
        return From;
    }

    public ICharacterState getTo() {
        return To;
    }

    public String getTrigger() {
        return Trigger;
    }
}
