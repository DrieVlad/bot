package chatBot;

import java.util.ArrayList;
import java.util.HashSet;


public class AccessoryTowns {
	
	public String check(String lastLetter, String line)
	{
		//Если город оканчивается на ьйыъё, то нужно вводить город на предпоследнюю букву
		//Если город оканчивается на ый, то нужно вводить город на препредпоследнюю букву
		if (line.substring(line.length() - 2, line.length()).equals("ый")) 
		{
			lastLetter = line.substring(line.length() - 3, line.length() - 2);
		}	
		else if (lastLetter.equals("ь")
				|| lastLetter.equals("й")
				|| lastLetter.equals("ы")
				|| lastLetter.equals("ъ")
				|| lastLetter.equals("ё"))
		{
        	lastLetter = line.substring(line.length() - 2, line.length() - 1);
		}
		return lastLetter.toLowerCase();
	}
	
	public boolean checkCity(FileTownsReader reader, String userCity, String firstLetter)
	{
		//Этот метод проверяет то, что вводимая пользователем строка является городом.
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
