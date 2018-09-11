package chatBot;
import java.util.Scanner;

public class Skeleton {
    private static String s_aboutMe = "Я бот, призванный играть с тобой";
	public static void main(String[] args) {
		System.out.println("Приветствуя тебя!");
		System.out.println(s_aboutMe);
		while (true) 
		{
			
		    System.out.println("Хочешь ли ты поиграть со мной?");
		    Scanner input = new Scanner(System.in);
		    String s_useResponse = input.nextLine();
		    if (s_useResponse == "yes")
		    {
			    System.out.println("Сейчас поиграем");
			    System.out.println("Во что будем играть?");
			    
		    }
		    else if (s_useResponse == "no")
		    {
			    System.out.println("Извините, я умею только играть");
		
	     	}
		    else 
		    {
		    	System.out.println("Извините, я вас не понял");
		    }
		}
		

	}

}
