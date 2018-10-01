package chatBot;

public interface StringDataReader 
{
    String readDataString();
    default public boolean checkDataString(String userData) 
	{
		if (userData.equals("")) 
		{
			return true;
		}
		return false;
	}
}
