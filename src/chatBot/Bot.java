package chatBot;

import java.util.Random;


public class Bot {
	public final FSMStack fsm = new FSMStack();
	public boolean flagMillionaire = false;

	public Bot() 
	{
		fsm.pushState(this::start);
	}
	
	public String start(String userInput) {
		fsm.popState();
		fsm.pushState(this::launch);
		return "🦆Приветствую тебя, мой дорогой друг!👋\n" + PhrasesBot.s_aboutMe;
	}

	private String launch(String userInput) {
		switch (userInput) {
		case ("игра"):
			fsm.popState();
		    fsm.pushState(this::twoGame);
			return "У меня есть две игры на выбор: \"Города\"🏘 и \"Миллионер\"💰. \n"
					+ "Пиши название той игры, в которую хочешь сыграть😊. \n"
					+ "Во что будем играть❔";
		case ("диалог"):
			fsm.popState();
			fsm.pushState(this::dialogueQuestion);
			return "Как тебя зовут?👤";
		case (""):
			fsm.popState();
		    fsm.pushState(this::launch);
			return "Скажи что-нибудь☺";
		default:
			fsm.popState();
			fsm.pushState(this::launch);
			return "Извините, я вас не понял☹";		
		}
	}

	private String dialogueQuestion(String userInput)
	{		
		int count1;
		int count2;
		Random randomer = new Random();
		count1 = randomer.nextInt(PhrasesBot.s_questions.length);
		count2 = randomer.nextInt(PhrasesBot.s_phrases.length);
		return PhrasesBot.s_phrases[count2] + " " + PhrasesBot.s_questions[count1];		
	}
		
	private String twoGame(String userInput) {
		Game game = null;
		switch (userInput) {
		case ("города"):
			game = new Towns();
            break;
		case ("миллионер"):		
            game = new Millionaire(this);
		    break;
		default:			
			fsm.popState();
			fsm.pushState(this::twoGame);
			return "Извините, я вас не понял☹";
		}
		fsm.popState();
        fsm.pushState(game::reply);
        return reply(userInput);			
	}

	public String reply(String userInput) {
		
		userInput = userInput.toLowerCase();
		switch(userInput) 
		{
		    case("помощь"):
		        return "ℹ" + PhrasesBot.s_aboutMe;
		    case("устал"):
			    fsm.stackReboot(this::launch);
			    return "Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨";
		    case("пока"):
			    fsm.stackReboot(this::start);
		        return "До скорого!👋 Я всегда к твоим услугам🦆 \n";
		}
		return fsm.update(userInput);	    
	}	
}