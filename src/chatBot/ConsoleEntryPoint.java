package chatBot;

import java.util.Scanner;

public class ConsoleEntryPoint
{
	private static final Scanner input = new Scanner(System.in);
	public static Bot bot = new Bot();
	
	public static void main(String[] args)
	{
		while(true)
		{
			String s_userInput = input.nextLine();			
			String s_botResponse = bot.reply(s_userInput);
			System.out.println(s_botResponse);
		}
	}
}