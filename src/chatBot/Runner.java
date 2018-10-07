package chatBot;

import java.util.Random;


public class Runner {
	public final FSMStack fsm = new FSMStack();
	public final PhrasesBot bot = new PhrasesBot();
	String userInput = "";
	Runner()
	{
		InOut.printer.writeDataString("Приветствую тебя, мой дорогой друг!\n");
		fsm.pushState(this::greeting);
	}
	
	public void greeting()
	{
		InOut.printer.writeDataString(bot.s_aboutMe);
		fsm.popState();
		fsm.pushState(this::launch);
	}
	public void printHelp() {
		InOut.printer.writeDataString(bot.s_aboutMe);
		fsm.popState();
		fsm.pushState(fsm::popState);
	}
	
	public void launch() 
	{		
		userInput = InOut.printer.readDataString();
		switch(userInput)
		{
		    case("да"):
			    fsm.popState();
			    fsm.pushState(this::choiseGame);
			    break;
		    case("нет"):
			    fsm.popState();
		        fsm.pushState(this::dialogue);
			    break;
		    case("помощь"):			    
			    fsm.pushState(this::printHelp);
			    break;
		    case("пока"):
			    fsm.popState();
			    fsm.pushState(this::sayGoodbye);
			    break;
		    default:
			    InOut.printer.writeDataString("Извините, я вас не понял:((");			
			    fsm.popState();
			    fsm.pushState(this::launch);
			    break;				
		}
	}
	
	public void dialogue()
	{		
		int count;
		Random randomer1 = new Random();
		count = randomer1.nextInt(PhrasesBot.s_questions.length);				
		InOut.printer.writeDataString(PhrasesBot.s_questions[count]);
		userInput = InOut.printer.readDataString();
		switch(userInput) 
		{	
			case("помощь"):	
			    fsm.pushState(this::printHelp);
			    break;    
			case("устал"):				
				fsm.popState();
				fsm.pushState(this::choiseGame);
				break;			
			case("пока"):
				fsm.popState();
				fsm.pushState(this::sayGoodbye);
				break;
		    default: 
			    count = randomer1.nextInt(PhrasesBot.s_phrases.length);				
			    InOut.printer.writeDataString(PhrasesBot.s_phrases[count] + " ");
			    break;		
		}
	}
	
	public void choiseGame() 
	{
		InOut.printer.writeDataString("У меня есть две игры на выбор: \"Города\" и \"Миллионер\". \n"
				+ "Пиши \"1\", если хочешь сыграть в \"Города\" "
				+ "и \"2\", если хочешь сыграть в \"Миллионер\". \n"
				+ "Во что будем играть? ");
		userInput = InOut.printer.readDataString();
		switch(userInput)
		{
		    case("1"):
			    fsm.popState();
			    fsm.pushState(this::playTowns);
			    break;
		    case("2"):
		    	fsm.popState();
		        fsm.pushState(this::playMillionaire);
		        break;
		    case("помощь"):
			    //fsm.popState();
			    fsm.pushState(this::printHelp);
			    break;
		    case("устал"):			
			    fsm.popState();
			    fsm.pushState(this::dialogue);
			    break;
		    case("пока"):
			    fsm.popState();
			    fsm.pushState(this::sayGoodbye);
			    break;
		    default:
			    InOut.printer.writeDataString("Извините, я вас не понял:((");			
			    fsm.popState();
			    fsm.pushState(this::choiseGame);
			    break;
		}
	}
		
	public void playTowns()
	{		
		Towns towns = new Towns();
		//towns.flagInterrupt = true;
	    towns.gameOfCities();
	}
	
	public void playMillionaire()
	{		
		Millionaire mill = new Millionaire();
		//towns.flagInterrupt = true;
	    mill.game();
	}
	
	public void whatsNext()
	{
		InOut.printer.writeDataString("Ещё поиграем?");
		fsm.popState();
		fsm.pushState(this::launch);	
	}
	
	public void sayGoodbye()
	{
		InOut.printer.writeDataString("До скорого! Я всегда к твоим услугам :)");
		fsm.popState();
		fsm.pushState(this::waitUser);
	}
	
	public void waitUser() {
		userInput = InOut.printer.readDataString();
		if (!userInput.isEmpty()) 
		{
			fsm.popState();
			fsm.pushState(this::greeting);
		}
		else throw new IllegalStateException();
	}
	public void update()
	{
		fsm.update();
	}
}