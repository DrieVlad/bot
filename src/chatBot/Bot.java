package chatBot;

import java.util.Random;


public class Bot {
	public final FSMStack fsm = new FSMStack();
	private Towns towns;
	private Millionaire mill;

	Bot() 
	{
		fsm.pushState(this::start);
	}
	
	public String start(String userInput) {
		fsm.popState();
		fsm.pushState(this::launch);
		return "Приветствую тебя, мой дорогой друг!\n" + PhrasesBot.s_aboutMe;
	}

	private String launch(String userInput) {
		switch (userInput) {
		case ("игра"):
			fsm.popState();
		    fsm.pushState(this::twoGame);
			return "У меня есть две игры на выбор: \"Города\" и \"Миллионер\". \n"
					+ "Пиши \"1\", если хочешь сыграть в \"Города\" " + "и \"2\", если хочешь сыграть в \"Миллионер\". \n"
					+ "Во что будем играть? ";
		case ("диалог"):
			fsm.popState();
			fsm.pushState(this::dialogueQuestion);
			return "Как тебя зовут?";
		case (""):
			fsm.popState();
		    fsm.pushState(this::launch);
			return "Скажи что-нибудь =)";
		default:
			fsm.popState();
			fsm.pushState(this::launch);
			return "Извините, я вас не понял :((";		
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
		switch (userInput) {
		case ("1"):
			fsm.popState();
			fsm.pushState(this::playTowns);
			towns = new Towns();
			return "Хороший выбор! Введите количество игроков!";
		case ("2"):
			fsm.popState();
		    fsm.pushState(this::playMillionaire);
            mill = new Millionaire();
		    return "Хороший выбор! Введи \"старт\", чтобы начать игру!";
		default:			
			fsm.popState();
			fsm.pushState(this::twoGame);
			return "Извините, я вас не понял :((";
		}
	}

	private String playTowns(String userInput) {		
		return towns.game(userInput);
	}

	public String playMillionaire(String userInput) {
		return mill.game(userInput);
	}

	public String update(String userInput) {
		return fsm.update(userInput);
	}
}