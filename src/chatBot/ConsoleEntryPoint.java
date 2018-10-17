package chatBot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleEntryPoint
{
	private static final Scanner input = new Scanner(System.in);
	private static Map<String,Bot> dictionaryUser = new HashMap<String,Bot>();
	public static Bot bot = new Bot();
	private static String[] parseUserInput;
	
	public static void main(String[] args)
	{
		while(true)
		{
			String s_userInput = input.nextLine();
			try
			{
			    parseUserInput = s_userInput.split(",");
			    if (dictionaryUser.containsKey(parseUserInput[0]))
			    {
				    System.out.println(dictionaryUser.get(parseUserInput[0]).reply(parseUserInput[1]));
			    }
			    else
			    {
				    dictionaryUser.put(parseUserInput[0], new Bot());
				    System.out.println(dictionaryUser.get(parseUserInput[0]).reply(parseUserInput[1]));
			    }
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Введите id пользователя и сообщение для бота через запятую!");
			}
		}
	}
}