package chatBot;

public class CheckUserDataString 
{
    public static boolean checkDataString(String userData) 
	{
		if (userData.equals("")) 
		{
			return true;
		}
		return false;
	}
}
