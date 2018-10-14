package chatBot;

import java.util.ArrayList;


@FunctionalInterface
interface CurrentState {
	public String run(String userInput);
}

public class FSMStack {
	private ArrayList<CurrentState> stack = new ArrayList<CurrentState>();

	public String update(String userInput)
	{
		CurrentState currentStateFunction = getCurrentState();
	    String botResponse = "";
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
	
	public void stackReboot() {
		stack.clear();
		this.pushState(ConsoleEntryPoint.bot::start);
	}
	
	public void stackRebootForTest() {
		stack.clear();
		this.pushState(EntryPointForTest.bot::start);
	}
	
	public CurrentState getCurrentState()
	{
		return stack.size() > 0 ? stack.get(stack.size() - 1) : null;
	}
}