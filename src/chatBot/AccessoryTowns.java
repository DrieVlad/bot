package chatBot;

import java.util.HashSet;


public class AccessoryTowns {
	
	public String getLastSignificantLetter(String line)
	{
		//Если город оканчивается на ьйыъё, то нужно вводить город на предпоследнюю букву
		//Если город оканчивается на ый, то нужно вводить город на препредпоследнюю букву
		int index = line.length() - 1;
		String lastLetter = "";
		String forbiddenLetters = "ьыйъё";
		while (lastLetter.equals(""))
		{
			lastLetter = String.valueOf(line.charAt(index));
			if (!forbiddenLetters.contains(lastLetter))
				return lastLetter.toLowerCase();
			else
				index -= 1;
				lastLetter = "";			
		}
		return lastLetter.toLowerCase();
	}
		
	public boolean checkCity(FileTownsReader reader, String userCity, String firstLetter)
	{
		//Этот метод проверяет то, что вводимая пользователем строка является городом.
		userCity = userCity.toLowerCase();
		HashSet <String> allTownsOnLetter = new HashSet<String>(reader.dictContentForGames.get(firstLetter));
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
