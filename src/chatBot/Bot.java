package chatBot;


public class Bot {

	public static Runner bot = new Runner();
	public static void main(String[] args) {
		
	    boolean flagBreak = true;
		while(flagBreak)
		{
			bot.update();
		}
	}
}
