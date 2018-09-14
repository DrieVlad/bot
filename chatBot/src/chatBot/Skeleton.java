package chatBot;

import java.util.Scanner;

public class Skeleton 
{
    private static String s_aboutMe = "Я бот Евлампий, призванный играть с тобой:))\n"
			+ "Пиши мне \"help\" в любое время и я напомню как со мной общаться.\n"
			+ "Я буду предлагать тебе на выбор разные игры,\n"
			+ "а твоя задача выбрать игру и наслаждаться:))\n"
			+ "Хочешь ли ты поиграть со мной?";
    
	public static void main(String[] args) 
	{
		System.out.println("Приветствую тебя, мой дорогой друг!");
		help("help");
        start();
	}
	
	public static void start()
	{
		while (true) 
		{			
			Scanner input = new Scanner(System.in);
			String s_useResponse = input.nextLine();
			help(s_useResponse);
			if (s_useResponse.equals("yes") || s_useResponse.equals("Да") || s_useResponse.equals("да") || s_useResponse.equals("конечно"))  
			{
				System.out.println("Во что будем играть?");
				System.out.println("У меня есть интересная игра в \"Города\"");
			    Towns.gameOfCities();
			} 
			else if (s_useResponse.equals("no") || s_useResponse.equals("Нет") || s_useResponse.equals("нет")) 
			{
				System.out.println("Извините, я умею только играть:((");
			} 
			else
			{
				System.out.println("Извините, я вас не понял:((");
			}		
			input.close();
		}
	}
	
	public static boolean help(String s_Chek) 
	{
		if (s_Chek.equals("") || s_Chek.equals("help") || s_Chek.equals("помоги")
				|| s_Chek.equals("помощь") || s_Chek.equals("расскажи о себе")) 
		{
			System.out.println(s_aboutMe);
			return true;
		}
		return false;
	}
}