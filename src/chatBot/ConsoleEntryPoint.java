package chatBot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleEntryPoint
{
	private static final Scanner input = new Scanner(System.in);
	public static Map<String,Bot> dictionaryUser = new HashMap<String,Bot>();
	public static String currentUser = "";	
	private static String[] parseUserInput;
	
	public static void main(String[] args)
	{
		while(true)
		{
			String s_userInput = input.nextLine();
			try
			{
			    parseUserInput = s_userInput.split(",");
			    currentUser = parseUserInput[0];
			    if (dictionaryUser.containsKey(currentUser))
			    {
				    System.out.println(dictionaryUser.get(currentUser).reply(parseUserInput[1]));
			    }
			    else
			    {
				    dictionaryUser.put(currentUser, new Bot());
				    System.out.println(dictionaryUser.get(currentUser).reply(parseUserInput[1]));
			    }
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Введите id пользователя и сообщение для бота через запятую!");
			}
		}
	}
}