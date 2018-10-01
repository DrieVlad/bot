package chatBot;

public interface DataReader 
{
    String scanData();
    default public boolean checkData(String userData) 
	{
		if (userData.equals("")) 
		{
			return true;
		}
		return false;
	}
}
