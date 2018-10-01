package chatBot;

import java.util.HashSet;


public class AccessoryTowns {
	
	public String getLastSignificantLetter(String line)
	{
		//Если город оканчивается на ьйыъё, то нужно вводить город на предпоследнюю букву
		//Если город оканчивается на ый, то нужно вводить город на препредпоследнюю букву
		String lastLetter = "";
		if (line.substring(line.length() - 2, line.length()).endsWith("ый") && line.length() > 2) 
		{
			lastLetter = line.substring(line.length() - 3, line.length() - 2);
		}	
		else if (( line.endsWith("ь")
				|| line.endsWith("й")
				|| line.endsWith("ы")
				|| line.endsWith("ъ")
				|| line.endsWith("ё")) 
				&& line.length() > 1)
		{
        	lastLetter = line.substring(line.length() - 2, line.length() - 1);
		}
		else
		{
			lastLetter = line.substring(line.length() - 1, line.length());
		}
		return lastLetter.toLowerCase();	
	}
		
	public boolean checkCity(FileTownsReader reader, String userCity, String firstLetter)
	{
		//Этот метод проверяет то, что вводимая пользователем строка является городом.
		userCity = userCity.toLowerCase();

		HashSet <String> allTownsOnLetter = new HashSet<String>(reader.dictTowns.get(firstLetter));
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
		//Проверка на то, что город не повторяется. 
		city = city.toLowerCase();
		if (!usedCities.contains(city))
     	{  	            	
            usedCities.add(city);                       
            return true;
     	}
        return false;
	}
}
