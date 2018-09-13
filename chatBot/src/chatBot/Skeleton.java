package chatBot;

import java.util.Scanner;

public class Skeleton 
{
    private static String s_aboutMe = 
    		"Я бот Евлампий, призванный играть с тобой\n"
			+ "Пиши мне help  любое время и я напомню как со мной общаться\n"
			+ "Я буду предлагать тебе играть в разные игры\n"
			+ "твоя задача выбрать игру и наслаждаться";
    
	public static void main(String[] args) 
	{
		System.out.println("Приветствуя тебя!");
		help("help");
        start();
	}
	
	public static void start()
	{
		while (true) 
		{
			System.out.println("Хочешь ли ты поиграть со мной?");
			Scanner inputUser = new Scanner(System.in);
			String s_useResponse = inputUser.nextLine();
			help(s_useResponse);
			if (s_useResponse.equals("yes") 
					|| s_useResponse.equals("да") 
					|| s_useResponse.equals("Да")) 
			{
				System.out.println("Сейчас поиграем");
				System.out.println("Во что будем играть?");
				System.out.println("У меня есть интересная игра в \"Города\"");
<<<<<<< HEAD
<<<<<<< HEAD
			    
			    Towns.cities();
=======
			    Towns towns = new Towns();
			    towns.cities();

>>>>>>> ff4d30f089bc94efd01f65fd0d5887c4b634bb6c
=======
			    Towns towns = new Towns();
			    towns.cities();

>>>>>>> ff4d30f089bc94efd01f65fd0d5887c4b634bb6c

			} 
			else if (s_useResponse.equals("no")) 
			{
				System.out.println("Извините, я умею только играть");

			} 
			else
			{
				System.out.println("Извините, я вас не понял");
			}
			
		}
	}
	
	public static boolean help(String s_Chek) 
	{
		if (s_Chek.equals("") 
				|| s_Chek.equals("help") 
				|| s_Chek.equals("помоги")
				|| s_Chek.equals("помощь") 
				|| s_Chek.equals("расскажи о себе")) 
		{
			System.out.println(s_aboutMe);
			return true;
		}
		return false;
	}

}
