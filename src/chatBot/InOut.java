package chatBot;

import java.util.Scanner;

public class InOut implements DataReader, DataWriter
{
	public String scanData() 
	{
		Scanner input = new Scanner(System.in);
		String s_useResponse  = "";
		while (checkData(s_useResponse)) 
		{
			s_useResponse = input.nextLine();
		}
		return s_useResponse;
	}
	
	public boolean checkData(String userData) 
	{
		if (userData.equals("")) 
		{
			return true;
		}
		return false;
	}
	
	public void printData(String s_user) 
	{
		System.out.println(s_user);
	}

}
