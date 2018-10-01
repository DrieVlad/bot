package chatBot;

import java.util.Random;


public class Skeleton 
{
	private static InOut inOut = new InOut();
	
	private static String[] s_questions = {
			"Как тебя зовут?",
			"Как погода за окном?",
			"Как у тебя настроение?",
			"Что ты любишь?",
			"Сколько тебе лет?",
			"Как часто ты грустишь?",
			"Что ты ненавидишь делать?",
			"Меня давно интересовал вопрос, что такое ОПП?",
			"Сколько времени на часах?",
			"Расскажи о твоём хобби",
			"Почему не спишь?",
			"Каких животных ты любишь?",
			"Как любишь проводить выходные?",
			"Что делал на каникулах?",
			"В чём ты спишь?",
			"Ты оптимист, пессимист или может быть реалист?",
			"Какие у тебя увлечения?",
			"Какой самый счастливый момент в твоей жизни?",
			"Какую музыку слушаешь последнее время?",
			"Скажи, а какое твоё любимое время года?",
			"Какая твоя любимая книга?"
	};
	
	private static String[] s_phrases = {
			"Оо, как интересно!",
			"Замечательно!",
			"Удивительно!", 
			"Просто чудесно!", 
			"Великолепно!",
			"Весьма впечатляет!",
			"Ты меня удивил!", 
			"Я ошарашен!", 
			"Весьма не дурно...",
			"Это серьёзно...",
			"Идеально!",
			"Круто!",
			"Любопытно однако:)",
			"Потрясающе!", 
			"Смешно, ха-ха:)",
			"Хорошо!",
			"Ясно.",
			"Чертовски остроумно!"
	};
	
    private static String s_aboutMe = "Я бот Евлампий, призванный играть с тобой:))\n"
			+ "Пиши мне в любое время: \n"
			+ "   help \n"
			+ "   помоги \n"
			+ "   помощь \n"
			+ "   расскажи о себе\n\n"
			+ "И я напомню как со мной общаться.\n\n"
			+ "Я буду предлагать тебе на выбор разные игры,\n"
			+ "а твоя задача выбрать игру и наслаждаться:))\n\n"
			+ "Чтобы выбрать игру напиши мне: \n"
			+ "   \"да\" или \"погнали\" \n\n"
			+ "Чтобы отказаться от игры просто напиши мне:\n"
			+  "   \"нет\" или \"давай дальше\" \n";
    
	public static void main(String[] args) 
	{
		inOut.writeData("Приветствую тебя, мой дорогой друг!\n");
		help("help");
        start();
	}
	
	public static void start()
	{
		int count;
		Random randomer1 = new Random();
		boolean flagInterrupt = true;
		while (flagInterrupt) 
		{	
			String s_useResponse;
			inOut.writeData(" Поиграем?");
			s_useResponse = inOut.readDataString();
			s_useResponse = s_useResponse.toLowerCase();
			help(s_useResponse);
			if (s_useResponse.equals("yes")
					|| s_useResponse.equals("да")
					|| s_useResponse.equals("конечно")
					|| s_useResponse.equals("давай")
					|| s_useResponse.equals("погнали"))  
			{				
				inOut.writeData("Во что будем играть?");
				inOut.writeData("У меня есть интересная игра в \"Города\"");
				Towns towns = new Towns();
				towns.gameOfCities();
			} 
			else if (s_useResponse.equals("no")
					|| s_useResponse.equals("нет")
					|| s_useResponse.equals("давай дальше")) 
			{
				count = randomer1.nextInt(s_questions.length);				
				inOut.writeData(s_questions[count]);
				s_useResponse = inOut.readDataString();
				help(s_useResponse);
				count = randomer1.nextInt(s_phrases.length);				
				inOut.writeData(s_phrases[count] + " ");
			} 
			else
			{
				inOut.writeData("Извините, я вас не понял:((");
			}			
		}		
	}
	
	public static boolean help(String s_Check) 
	{
		s_Check = s_Check.toLowerCase();
		if (s_Check.equals("help")
				|| s_Check.equals("помоги")
				|| s_Check.equals("помощь")
				|| s_Check.equals("расскажи о себе")) 
		{
			inOut.writeData(s_aboutMe);
			return true;
		}
		return false;
	}
}