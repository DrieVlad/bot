package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import org.junit.jupiter.api.Test;
import chatBot.AccessoryTowns;
import chatBot.Towns;
import chatBot.TownsContent;

public class TownsTest {
	
	private AccessoryTowns accessoryTowns = new AccessoryTowns();
	private TownsContent reader = new TownsContent("Города");
	private Towns towns = new Towns();
	
	@Test
	public void runPlayerTownTest()
	{
		Towns.lastLetter = "ф";
		assertEquals("Что же вы ничего не ввели?! Говорите город на букву: Ф!", towns.runPlayer(""));
		assertEquals("Ты играешь не по правилам. Назови город на букву Ф!", towns.runPlayer("Касли"));
		assertEquals("Не жульничай! Ты называешь не город :)))", towns.runPlayer("фюзеляж"));
		assertTrue(towns.runPlayer("Фролово").startsWith("Мой ход:"));	
		Towns.lastLetter = "ф";
		assertEquals("Ай-яй-яй! Это слово уже было названо. Попробуй еще раз!", towns.runPlayer("Фролово"));
	}
	
	@Test
	public void checkCityTest() 
	{		
		String[] keywordsTrue = {"абаза", "Абаза", "АбАзА"};
		String[] keywordsFalse = {"вамрвашмт", "авамрвашмт", "aбаза"};
		for (int i = 0; i < keywordsTrue.length; i++)
		{
			assertEquals(true, accessoryTowns.checkCity(reader, keywordsTrue[i], "а"));
		}
		for (int i = 0; i < keywordsFalse.length; i++)
		{
			assertEquals(false, accessoryTowns.checkCity(reader, keywordsFalse[i], "а"));
		}		
	}
	
	@Test
	public void checkWordDictionaryTest()
	{
		HashSet<String> usedCities = new HashSet<String>();
		
		String[] keywordsTrue = {"абаза", "Абакан"};
		String[] keywordsFalse = {"абаза", "Абаза"};
		
		for (int i = 0; i < keywordsTrue.length; i++)
		{
			assertEquals(true, accessoryTowns.checkWordDictionary(keywordsTrue[i], usedCities));
		}
		for (int i = 0; i < keywordsFalse.length; i++)
		{
			assertEquals(false, accessoryTowns.checkWordDictionary(keywordsFalse[i], usedCities));
		}
	}
	
	@Test
	public void getLastSignificantLetterTest()
	{				
		assertEquals(accessoryTowns.getLastSignificantLetter("абаза"), "а");
		assertEquals(accessoryTowns.getLastSignificantLetter("грозный"), "н");
		assertEquals(accessoryTowns.getLastSignificantLetter("тверь"), "р");
		assertEquals(accessoryTowns.getLastSignificantLetter("твеРь"), "р");
	}
}