package chatBot;


public class EntryPointForTest
{
	public static Bot bot = new Bot();
	public String s_botResponse = "";
	
	public String forTest(String s_userInput)
	{

		s_botResponse = bot.reply(s_userInput);
		return s_botResponse;
	}
}