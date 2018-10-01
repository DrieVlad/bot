package chatBot;

import java.util.ArrayList;
import java.util.HashSet;


public class AccessoryTowns {
	
	public String check(String lastLetter, String line)
	{
		//���� ����� ������������ �� �����, �� ����� ������� ����� �� ������������� �����
		//���� ����� ������������ �� ��, �� ����� ������� ����� �� ���������������� �����
		
		
		if (line.substring(line.length() - 2, line.length()).endsWith("��") && line.length() > 2) 
		{
			lastLetter = line.substring(line.length() - 3, line.length() - 2);
		}	
		else if ((lastLetter.endsWith("�")
				|| lastLetter.endsWith("�")
				|| lastLetter.endsWith("�")
				|| lastLetter.endsWith("�")
				|| lastLetter.endsWith("�")) 
				&& line.length() > 1)
		{
        	lastLetter = line.substring(line.length() - 2, line.length() - 1);
		}

		return lastLetter.toLowerCase();
	}
	
	public boolean checkCity(FileTownsReader reader, String userCity, String firstLetter)
	{
		//���� ����� ��������� ��, ��� �������� ������������� ������ �������� �������.
		userCity = userCity.toLowerCase();

		ArrayList<String> allTownsOnLetter = reader.dictTowns.get(firstLetter);
		for (String town : allTownsOnLetter)
		{
			if (town.toLowerCase().equals(userCity))
			{
				return true;
			}
		}
		return false;      	
	}

	public Boolean checkWordDictionary(String city, HashSet<String> usedCities)
	{
		//�������� �� ��, ��� ����� �� �����������. 
		city = city.toLowerCase();
		if (!usedCities.contains(city))
     	{  	            	
            usedCities.add(city);                       
            return true;
     	}
        return false;
	}
}
