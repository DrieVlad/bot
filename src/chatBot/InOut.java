package chatBot;

import java.util.Scanner;

public class InOut implements StringDataReader, StringDataWriter
{
	public static final InOut printer = new InOut();
	private static final Scanner input = new Scanner(System.in);
	public String readDataString() 
	{		
		String s_useResponse  = "";
		while (CheckUserDataString.checkDataString(s_useResponse)) 
		{
			s_useResponse = input.nextLine();
		}
		return s_useResponse;
	}	
	
	public void writeDataString(String s_user) 
	{
		System.out.println(s_user);
	}
}