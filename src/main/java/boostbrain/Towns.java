package boostbrain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class Towns implements Game
{
    private HashSet<String> usedCities = new HashSet<String>();
    private static String firstLetter = "";
    public static String lastLetter = "";
    private static AccessoryTowns helper = new AccessoryTowns();
    private static TownsContent reader = new TownsContent("Города");    
    private Random ran = new Random();    
    private boolean turnBot = true;
    private boolean turnPlayer = false;
    private String alphabet = "абвгдежзиклмнопрстуфхцчшщэюя";    
    private Message message = null;
    private Bot bot;
    private static Firebase firebase;
    public String botTown="";
    
    public Towns(Bot bot, Firebase fire)
    {
        this.bot = bot;
        this.message = bot.message;
        firebase = fire;

    }
    
    public Message reply(Message userInput)
    {
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        bot.setHelpAndTired(rowButtons, keyboard);
        if (turnBot)
        {                            
            int count = ran.nextInt(alphabet.length());
            lastLetter = String.valueOf(alphabet.charAt(count));

            message.setTextMessage(runBot());
            return message;
        }                        
        if (turnPlayer)
        {

            message.setTextMessage(runPlayer(userInput.getTextMessage()));
            return message;
        }
        message.setTextMessage("Игра окончена!");
        return message;
    }
    
    private String runBot()
    {
        synchronized (new Object()){
            firebase.getTownsFromDatabase("города", lastLetter, this);
        }

        System.out.println("sdv"+ botTown);
        if (turnBot == true)
        {
            turnBot = false;
            turnPlayer = true;
        }
        lastLetter = helper.getLastSignificantLetter(botTown);
        return "Мой ход: " + botTown + "\n" + "Твой ход. Тебе слово на букву: " + lastLetter.toUpperCase() + "!";                
    }
    
    public String runPlayer(String userInput)
    {

        if (userInput.trim().isEmpty())
        {
            return "Что же вы ничего не ввели?! Говорите город на букву: " + lastLetter.toUpperCase() + "!";            
        }
        userInput = userInput.toLowerCase();               
        firstLetter = userInput.substring(0, 1);      
        if (!lastLetter.equals(firstLetter))
        {
            return "Ты играешь не по правилам. Назови город на букву " + lastLetter.toUpperCase() + "!";            
        }
        boolean isItACity = helper.checkCity(reader, userInput, firstLetter.toLowerCase());                
        if (!isItACity)
        {
            return "Не жульничай! Ты называешь не город :)))";
        }
            
        if (helper.checkWordDictionary(userInput, usedCities))
        {
            lastLetter = helper.getLastSignificantLetter(userInput);

        } 
        else
            return "Ай-яй-яй! Это слово уже было названо. Попробуй еще раз!";

        return runBot();        
    }    
}
