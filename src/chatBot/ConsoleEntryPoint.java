package chatBot;

import java.util.Scanner;

public class ConsoleEntryPoint
{
	private static final Scanner input = new Scanner(System.in);
	public static Bot bot = new Bot();
	
	public static void main(String[] args)
	{
		String s_userInput  = "";
		String s_botResponse = "";
		while(true)
		{
			s_userInput = input.nextLine();
			if (s_userInput.equals("помощь"))
			{
				System.out.println(PhrasesBot.s_aboutMe);
				continue;
			}
			if (s_userInput.equals("устал"))
			{				
				bot.fsm.stackReboot();
			}
			if (s_userInput.equals("пока"))
			{				
				bot.fsm.stackReboot();
				System.out.println("До скорого! Я всегда к твоим услугам :) \n");
				continue;
			}
			s_botResponse = bot.update(s_userInput);
			System.out.println(s_botResponse);
		}
	}
}