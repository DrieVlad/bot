package boostbrain;

import java.util.ArrayList;


public class Bot {
    public final FSMStack fsm = new FSMStack();
    public boolean flagMillionaire = false;
    public Message message = new Message();
    private static Statistic stats;
    private Firebase firebase;
    private CriticismReaction critic = new CriticismReaction(stats);
    private static String botPasswd = "крокодил";

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
            case ("я админ"):
                fsm.popState();
                fsm.pushState(this::isAdmin);
                message.setTextMessage("Сколько нужно вертолетов, чтобы научить летать президента?");
                return message;
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

    public Message criticsAdd(Message userInput) {
        message.setTextMessage("Хорошо, мы попробуем это учесть");
        String chatId = Long.toString(userInput.getChatId());
        firebase.saveCriticiszmInDatabase(chatId + userInput.getTextMessage(), userInput.getTextMessage());
        return message;
    }

    public Message critics(Message userInput){
        message.setTextMessage("Хорошо, мы попробуем это учесть");
        fsm.popState();
        return criticsAdd(userInput);
    }

    public Message isAdmin(Message userInput) {
        System.out.println(botPasswd);
        if (userInput.getTextMessage().equals(botPasswd))
        {
            fsm.popState();
            fsm.pushState(this::adminMode);
            message.setTextMessage("Здравствуй, господин, чего желаешь?");
        }
        else {
            message.setTextMessage(firebase.getPhraseFromDatabase("фразы","непонимание"));
            fsm.popState();
            fsm.pushState(this::launch);
        }
        return message;
    }

    public Message adminMode(Message userInput) {
        Admin admin = new Admin(firebase, this);
        fsm.popState();
        fsm.pushState(admin::adminManage);
        return reply(userInput);
    }

    public Message reply(Message userInput) {
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
        message = fsm.update(userInput);

        if (critic.isCritics(userInput.getChatId(), userInput.getTextMessage())){
            fsm.pushState(this::critics);
            message.setTextMessage(firebase.getPhraseFromDatabase("фразы","ругань") + "\nМожет объяснишь что не так?");
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