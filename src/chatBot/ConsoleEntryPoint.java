package chatBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class ConsoleEntryPoint
{
    private static final Scanner input = new Scanner(System.in);
    private static Map<String,Bot> dictionaryUser = new HashMap<String,Bot>();
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
                userInput.setTextMessage(parseUserInput[1]);
                botAnswer = dictionaryUser.computeIfAbsent(parseUserInput[0], x -> new Bot()).reply(userInput);    
                System.out.println(botAnswer.getTextMessage());
                if (botAnswer.isKeyboardNotEmpty) 
                {
                    ArrayList<ArrayList<String>> listKeyboards = botAnswer.getKeyboard();
                    for(ArrayList<String> line: listKeyboards) 
                    {
                        for (String button: line)  
                        {
                            System.out.println(button);
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