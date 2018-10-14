package chatBot;

import java.util.Scanner;

public class EntryPointForTest
{
	public static Bot bot = new Bot();
	public String s_botResponse = "";
	
	public String forTest(String s_userInput)
	{
		if (s_userInput.equals("помощь"))
		{
			return(PhrasesBot.s_aboutMe);
		}
		if (s_userInput.equals("устал"))
		{				
			bot.fsm.stackRebootForTest();
		}
		if (s_userInput.equals("пока"))
		{				
			bot.fsm.stackReboot();
			return "До скорого! Я всегда к твоим услугам :) \n";
		}
		s_botResponse = bot.update(s_userInput);
		return s_botResponse;
	}
}