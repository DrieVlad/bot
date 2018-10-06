package chatBot;


public class Bot {

	public static Runner bot = new Runner();
	public static void main(String[] args) {
		
		while(true)
		{
			bot.update();
		}
	}
}
