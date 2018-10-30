package chatBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class ConsoleEntryPoint
{
	private static final Scanner input = new Scanner(System.in);
	public static Map<String,Bot> dictionaryUser = new HashMap<String,Bot>();
	private static String[] parseUserInput;
	
	public static void main(String[] args)
	{
		while(true)
		{
			String s_userInput = input.nextLine().toLowerCase();
			try
			{
				Message userInput = new Message();
				Message botAnswer = new Message();				
			    parseUserInput = s_userInput.split(",");
			    if (!dictionaryUser.containsKey(parseUserInput[0]))
			        dictionaryUser.put(parseUserInput[0], new Bot());
			    userInput.setTextMessage(parseUserInput[1]);
				botAnswer = dictionaryUser.get(parseUserInput[0]).reply(userInput);	
				System.out.println(botAnswer.getTextMessage());
				if (botAnswer.isKeyboardNotEmpty) 
				{
					ArrayList<ArrayList<String>> listKeyboards = botAnswer.getKeyboard();
					for(int i = 0; i < listKeyboards.size(); i++) 
					{
						for (int j = 0; j < listKeyboards.get(i).size(); j++) 
						{
							System.out.println(listKeyboards.get(i).get(j));
						}
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Введите id пользователя и сообщение для бота через запятую!");
			}
		}
	}
}