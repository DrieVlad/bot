package boostbrain;

import java.util.ArrayList;

public class Millionaire implements Game
{
    private MillionaireContent reader = new MillionaireContent(this);
    public int level;
    public AskMillionaire ask = new AskMillionaire();
    private boolean flagReturn = false;
    private static String numberQuestions = "";
    private Message message = null;
    private Bot bot;
    public Statistic stats;
    public static Firebase firebase;
    public CriticismReaction critic = new CriticismReaction(stats);

    public Millionaire(Bot bot, Statistic stat)
    {
        level = 1;
        this.bot = bot;
        this.message = bot.message;
        stats = stat;
        firebase = stats.firebase;
    }
            
    public Message reply(Message userInput)
    {

        stats.updateDB(Long.toString(userInput.getChatId()));

        String botAnswer = "";    
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        numberQuestions = "Вопрос №" + level + ":\n";
        if (flagReturn)
        {
            if (userInput.getTextMessage().equals("нет"))
            {
                bot.fsm.stackReboot(bot::start);
                message.setTextMessage(firebase.getPhraseFromDatabase("фразы","возврат"));
                stats.set(Long.toString(userInput.getChatId()), userInput.getUserName(),  false);
                rowButtons.add("Я скучаю!");
                keyboard.add(rowButtons);
                message.setKeyboard(keyboard);
                return message;
            }
            if (userInput.getTextMessage().equals("да"))
               flagReturn = false;
            else
            {
                message.setTextMessage(firebase.getPhraseFromDatabase("фразы","непонимание"));
                return message;
            }
        }
        if (level == 1) 
        {
            ask = reader.nextAsk(level);

            botAnswer = ask.stringAsk();
            level++;
            message.setTextMessage(numberQuestions + botAnswer);    
            setAnswerChoise(rowButtons, keyboard);
            rowButtons = new ArrayList<>();
            bot.setHelpAndTired(rowButtons, keyboard);
            return message;
        }
        else
        {
            return gameNext(userInput);
        }            
    }
    
    public void setAnswerChoise(ArrayList<String> rowButtons, ArrayList<ArrayList<String>> keyboard)
    {
        rowButtons.add("1");
        rowButtons.add("2");
        rowButtons.add("3");
        rowButtons.add("4");
        keyboard.add(rowButtons);
    }
    
    public Message gameNext(Message userInput) throws NumberFormatException
    {
        String botAnswer = "";
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        try 
        {
            if (!(userInput.getTextMessage().equals("1")
                    || userInput.getTextMessage().equals("2") 
                    || userInput.getTextMessage().equals("3")
                    || userInput.getTextMessage().equals("4")))
                throw new NumberFormatException();
            if (ask.checkAsk(Integer.parseInt(userInput.getTextMessage()))) 
            {        
                if (level == 13)
                {
                    flagReturn = true;
                    level = 1;
                    message.setTextMessage("Молодец, ты победил в игре \"Миллионер\"!🏆 Твой выигрыш составил 1200 очков! \nСыграем ещё разок?😏 Отвечай \'да\'👍 или \'нет\'👎");
                    stats.set(Long.toString(userInput.getChatId()), userInput.getUserName(), true);
                    rowButtons.add("Да");
                    rowButtons.add("Нет");
                    keyboard.add(rowButtons);
                    rowButtons = new ArrayList<>();
                    bot.setHelpAndTired(rowButtons, keyboard);
                    return message;
                }
                ask = reader.nextAsk(level);
                botAnswer = ask.stringAsk();
                numberQuestions = "Вопрос №" + level + ":\n";
                level++;
                message.setTextMessage("Молодец, ты выиграл " + 100 * (level - 2) + " очков!✋ Внимание, следующий вопрос!🔔 \n" + numberQuestions + botAnswer);
                setAnswerChoise(rowButtons, keyboard);
            }
            else 
            {                
                if ((level - 1) / 3 != 0) 
                {
                    int point;
                    point = ((level - 1) / 3) * 3 * 100;
                    message.setTextMessage("Вы выиграли " + point + " очков, в следующий раз получится лучше!😉👌 \n Ещё разок?😏  Отвечай \'да\'👍 или \'нет\'👎");
                    stats.set(Long.toString(userInput.getChatId()), userInput.getUserName(), false);
                }
                else 
                {
                    message.setTextMessage(firebase.getPhraseFromDatabase("фразы","поражение"));
                    stats.set(Long.toString(userInput.getChatId()), userInput.getUserName(), false);
                }
                level = 1;
                flagReturn = true;            
                rowButtons.add("Да");
                rowButtons.add("Нет");
                rowButtons.add("Статистика");
                keyboard.add(rowButtons);    
            }
            rowButtons = new ArrayList<>();
            bot.setHelpAndTired(rowButtons, keyboard);
            return message;
        }
        catch(NumberFormatException e)
        {
            message.setTextMessage("Попробуйте ввести цифру от 1⃣ до 4⃣.");
            return message;
        }
    }
}