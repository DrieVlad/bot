package chatBot;

import java.util.HashSet;


public class AccessoryTowns {
    
    public String getLastSignificantLetter(String line)
    {
        int index = line.length() - 1;
        String lastLetter = "";
        String forbiddenLetters = "ыйьъё";
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
        
    public boolean checkCity(TownsContent reader, String userCity, String firstLetter)
    {        
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
        city = city.toLowerCase();
        if (!usedCities.contains(city))
         {                      
            usedCities.add(city);                       
            return true;
         }
        return false;
    }
}