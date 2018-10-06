package chatBot;

import java.util.ArrayList;


@FunctionalInterface
interface CurrentState {
	public void run();
}

public class FSMStack {
	private ArrayList<CurrentState> stack = new ArrayList<CurrentState>();

	public void update()
	{
		CurrentState currentStateFunction = getCurrentState();
	 
        if (currentStateFunction != null) 
        {
            currentStateFunction.run();
        }
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
	
	public CurrentState getCurrentState()
	{
		return stack.size() > 0 ? stack.get(stack.size() - 1) : null;
	}
}