package boostbrain;

import java.util.ArrayList;


public class Bot {
    public final FSMStack fsm = new FSMStack();
    public boolean flagMillionaire = false;
    public Message message = new Message();
    private static Statistic stats;
    private Firebase firebase;
    private CriticismReaction critic = new CriticismReaction(stats);

    public Bot(Statistic stat) {
        fsm.pushState(this::start);
        stats = stat;
        firebase = stats.firebase;
    }

    public Message start(Message userInput) {
        ArrayList<String> row = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        fsm.popState();
        fsm.pushState(this::launch);
        //message.setTextMessage("🦆Приветствую тебя, мой дорогой друг!👋\n" + PhrasesBot.s_aboutMe);
        message.setTextMessage(firebase.getPhraseFromDatabase("фразы","привет"));
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
                //message.setTextMessage("У меня есть две игры на выбор: \"Города\"🏘 и \"Миллионер\"💰. \n"
                //       + "Пиши название той игры, в которую хочешь сыграть😊. \n"
                //      + "Во что будем играть❔");
                message.setTextMessage(firebase.getPhraseFromDatabase("фразы","выборигра"));
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
                //message.setTextMessage("Скажи что-нибудь☺");
                message.setTextMessage(firebase.getPhraseFromDatabase("фразы","призывы"));
                return message;
            default:
                fsm.popState();
                fsm.pushState(this::launch);
                //message.setTextMessage("Извините, я вас не понял☹");
                message.setTextMessage(firebase.getPhraseFromDatabase("фразы","непонимание"));

                return message;
        }
    }

    private Message dialogueQuestion(Message userInput) {
        String count1;
        String count2;
        count1 = firebase.getDialogFromDatabase("ответ");
        count2 = firebase.getDialogFromDatabase("вопрос");
        message.setTextMessage(count1 + " " + count2);
        return message;
    }

    private Message twoGame(Message userInput) {
        Game game = null;
        switch (userInput.getTextMessage()) {
            case ("города"):
                game = new Towns(this, stats.firebase);
                break;
            case ("миллионер"):
                game = new Millionaire(this, stats);
                break;
            default:
                fsm.popState();
                fsm.pushState(this::twoGame);
                //message.setTextMessage("Извините, я вас не понял☹");
                message.setTextMessage(firebase.getPhraseFromDatabase("фразы","непонимание"));
                return message;
        }
        fsm.popState();
        fsm.pushState(game::reply);
        return reply(userInput);
    }

    public void setHelpAndTired(ArrayList<String> row, ArrayList<ArrayList<String>> keyboard) {
        row.add("Помощь");
        row.add("Устал");
        keyboard.add(row);
        message.setKeyboard(keyboard);
    }

    public Message reply(Message userInput) {
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        message = fsm.update(userInput);

        if (critic.isCritics(userInput.getChatId(), userInput.getTextMessage())){
            message.setTextMessage(firebase.getPhraseFromDatabase("фразы","ругань"));
        }
        else {
            switch (userInput.getTextMessage()) {
                case ("помощь"):
                    message.setTextMessage("ℹ" + PhrasesBot.s_aboutMe);
                    break;
                case ("статистика"):
                    String in = Long.toString(userInput.getChatId());
                    message = stats.get(in);
                    break;
                case ("устал"):
                    fsm.stackReboot(this::launch);
                    //message.setTextMessage("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨");

                    message.setTextMessage(firebase.getPhraseFromDatabase("фразы","выбор"));
                    rowButtons.add("Игра");
                    rowButtons.add("Диалог");
                    keyboard.add(rowButtons);
                    rowButtons = new ArrayList<>();
                    setHelpAndTired(rowButtons, keyboard);
                    break;
                case ("пока"):
                    fsm.stackReboot(this::start);
                    message.setTextMessage(firebase.getPhraseFromDatabase("фразы","пока"));
                    //message.setTextMessage("До скорого!👋 Я всегда к твоим услугам🦆 \n");
                    rowButtons.add("Я скучаю!");
                    keyboard.add(rowButtons);
                    message.setKeyboard(keyboard);
            }
        }


        return message;
    }
}