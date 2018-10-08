package chatBot;

import java.util.Random;

public class Bot {
	public final FSMStack fsm = new FSMStack();
	public final PhrasesBot phrases = new PhrasesBot();
	String userInput = "";

	Bot() {
		fsm.pushState(this::start);
	}
	
	public String start(String userInput) {
		fsm.popState();
		fsm.pushState(this::launch);
		return "Приветствую тебя, мой дорогой друг!\n" + phrases.s_aboutMe;
	}

	public String launch(String userInput) {
		switch (userInput) {
		case ("игра"):
			fsm.popState();
		    fsm.pushState(this::twoGame);
			//botAnswer = choiseGame(userInput);
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
			return "Извините, я вас не понял:((";		
		}
	}

	public String dialogueQuestion(String userInput)
	{
		int count1;
		int count2;
		Random randomer = new Random();
		count1 = randomer.nextInt(PhrasesBot.s_questions.length);
		count2 = randomer.nextInt(PhrasesBot.s_phrases.length);
		return PhrasesBot.s_phrases[count2] + " " + PhrasesBot.s_questions[count1];		
	}
		
	public String twoGame(String userInput) {
		switch (userInput) {
		case ("1"):
			fsm.popState();
			fsm.pushState(this::playTowns);
			Towns towns = new Towns();
			towns.gameOfCities();
			return "Хороший выбор! Введи кол-во игроков!";
		case ("2"):
			fsm.popState();
			fsm.pushState(this::playMillionaire);
			Millionaire mill = new Millionaire();
			mill.game();
			return "Хороший выбор! Стартовая фраза!";
		default:			
			fsm.popState();
			fsm.pushState(this::twoGame);
			return "Извините, я вас не понял:((";
		}
	}

	public String playTowns(String userInput) {
		Towns towns = new Towns();
		// towns.flagInterrupt = true;
		towns.gameOfCities();
		return "города";
	}

	public String playMillionaire(String userInput) {
		Millionaire mill = new Millionaire();
		// towns.flagInterrupt = true;
		mill.game();
		return "миллионер";
	}

	public String update(String userInput) {
		String botResponse = "";
		botResponse = fsm.update(userInput);
		return botResponse;
	}
}