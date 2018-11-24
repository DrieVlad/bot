package boostbrain;

import java.util.ArrayList;


@FunctionalInterface
interface CurrentState {
    public Message run(Message userInput);
}

public class FSMStack {
    private ArrayList<CurrentState> stack = new ArrayList<CurrentState>();

    public Message update(Message userInput)
    {
        CurrentState currentStateFunction = getCurrentState();
        Message botResponse = null;
        if (currentStateFunction != null) 
        {
            botResponse = currentStateFunction.run(userInput);
        }
        return botResponse;
    }
    
    public CurrentState popState() {
        CurrentState state = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return state;
    }
    
    public void pushState(CurrentState state) {
        if (getCurrentState() != state) 
        {
            stack.add(state);
        }
    }
    
    public void stackReboot(CurrentState start) {
        stack.clear();
        this.pushState(start);
    }
    
    public CurrentState getCurrentState()
    {
        return stack.size() > 0 ? stack.get(stack.size() - 1) : null;
    }
}