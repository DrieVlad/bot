package chatBot;

import java.util.Scanner;

public class InOut implements DataReader, DataWriter
{
	Scanner input = new Scanner(System.in);
	public String scanData() 
	{		
		String s_useResponse  = "";
		while (checkData(s_useResponse)) 
		{
			s_useResponse = input.nextLine();
		}
		return s_useResponse;
	}	
	
	public void printData(String s_user) 
	{
		System.out.println(s_user);
	}
}
