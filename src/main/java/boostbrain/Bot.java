package boostbrain;

import java.util.ArrayList;
import java.util.Random;


public class Bot {
    public final FSMStack fsm = new FSMStack();
    public boolean flagMillionaire = false;
    public Message message = new Message();
    private static Statistic stats;

    public Bot(Statistic stat) {
        fsm.pushState(this::start);
        stats = stat;
    }
    
    public Message start(Message userInput) {
        ArrayList<String> row = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        fsm.popState();
        fsm.pushState(this::launch);
        message.setTextMessage("🦆Приветствую тебя, мой дорогой друг!👋\n" + PhrasesBot.s_aboutMe);
        row.add("Игра");
        row.add("Диалог");
        keyboard.add(row);
        row = new ArrayList<>();
        setHelpAndTired(row, keyboard);
        return message;
    }

    private Message launch(Message userInput) {
        ArrayList<String> row = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();        
        switch (userInput.getTextMessage()) {
        case ("игра"):
            fsm.popState();
            fsm.pushState(this::twoGame);
            message.setTextMessage("У меня есть две игры на выбор: \"Города\"🏘 и \"Миллионер\"💰. \n"
                    + "Пиши название той игры, в которую хочешь сыграть😊. \n"
                    + "Во что будем играть❔");
            row.add("Города");
            row.add("Миллионер");
            keyboard.add(row);
            row = new ArrayList<>();
            setHelpAndTired(row, keyboard);
            return message;
        case ("диалог"):
            fsm.popState();
            fsm.pushState(this::dialogueQuestion);
            message.setTextMessage("Как тебя зовут?👤");
            setHelpAndTired(row, keyboard);
            return message;
        case (""):
            fsm.popState();
            fsm.pushState(this::launch);
            message.setTextMessage("Скажи что-нибудь☺");
            return message;
        default:
            fsm.popState();
            fsm.pushState(this::launch);
            message.setTextMessage("Извините, я вас не понял☹");
            return message;        
        }
    }

    private Message dialogueQuestion(Message userInput)
    {        
        int count1;
        int count2;
        Random randomer = new Random();
        count1 = randomer.nextInt(PhrasesBot.s_questions.length);
        count2 = randomer.nextInt(PhrasesBot.s_phrases.length);
        message.setTextMessage(PhrasesBot.s_phrases[count2] + " " + PhrasesBot.s_questions[count1]);
        return message;        
    }
        
    private Message twoGame(Message userInput) {
        Game game = null;
        switch (userInput.getTextMessage()) {
        case ("города"):
            game = new Towns(this);
            break;
        case ("миллионер"):        
            game = new Millionaire(this, stats);
            break;
        default:            
            fsm.popState();
            fsm.pushState(this::twoGame);
            message.setTextMessage("Извините, я вас не понял☹");
            return message;
        }
        fsm.popState();
        fsm.pushState(game::reply);
        return reply(userInput);            
    }
    
    public void setHelpAndTired(ArrayList<String> row, ArrayList<ArrayList<String>> keyboard)
    {
        row.add("Помощь");
        row.add("Устал");
        keyboard.add(row);
        message.setKeyboard(keyboard);
    }

    public Message reply(Message userInput)
    {
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        message = fsm.update(userInput);
        
        switch(userInput.getTextMessage()) 
        {
            case("помощь"):
                message.setTextMessage("ℹ" + PhrasesBot.s_aboutMe);
                break;
            case("статистика"):
                String in = Long.toString(userInput.getChatId());
                message = stats.get(in);
                break;
            case("устал"):
                fsm.stackReboot(this::launch);
                message.setTextMessage("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨");
                rowButtons.add("Игра");
                rowButtons.add("Диалог");
                keyboard.add(rowButtons);
                rowButtons = new ArrayList<>();
                setHelpAndTired(rowButtons, keyboard);
                break;
            case("пока"):
                fsm.stackReboot(this::start);
                message.setTextMessage("До скорого!👋 Я всегда к твоим услугам🦆 \n");
                rowButtons.add("Я скучаю!");
                keyboard.add(rowButtons);
                message.setKeyboard(keyboard);               
        }    
        return message;        
    }    
}