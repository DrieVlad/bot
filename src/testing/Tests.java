package testing;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import chatBot.Skeleton;
import chatBot.AccessoryTowns;
import chatBot.FileTownsReader;


public class Tests 
{

	@Test
	public void testHelp() 
	{
		String[] keywordsTrue = {"help", "помоги",
				"помощь", "Расскажи о себе"};
		String[] keywordsFalse = {"ввапва", "helpwef"};
		for (int i = 0; i < keywordsTrue.length; i++)
		{
			assertEquals(Skeleton.help(keywordsTrue[i]), true);
		}
		for (int i = 0; i < keywordsFalse.length; i++)
		{
			assertEquals(Skeleton.help(keywordsFalse[i]), false);
		}
	}
	
	@Test
	public void testCheckCity() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		FileTownsReader reader = new FileTownsReader();
		reader.getTowns();
		String[] keywordsTrue = {"абаза", "Абаза", "АбАзА"};
		String[] keywordsFalse = {"вамрвашмт", "авамрвашмт", "aбаза"};
		for (int i = 0; i < keywordsTrue.length; i++)
		{
			assertEquals(accessoryTowns.checkCity(reader, keywordsTrue[i], "а"), true);
		}
		for (int i = 0; i < keywordsFalse.length; i++)
		{
			assertEquals(accessoryTowns.checkCity(reader, keywordsFalse[i], "а"), false);
		}
		
	}
	
	@Test
	public void testCheckWordDictionary() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		HashSet<String> usedCities = new HashSet<String>();
		
		String[] keywordsTrue = {"абаза", "Абакан"};
		String[] keywordsFalse = {"абаза", "Абаза"};
		
		for (int i = 0; i < keywordsTrue.length; i++)
		{
			assertEquals(accessoryTowns.checkWordDictionary(keywordsTrue[i], usedCities), true);
		}
		for (int i = 0; i < keywordsFalse.length; i++)
		{
			assertEquals(accessoryTowns.checkWordDictionary(keywordsFalse[i], usedCities), false);
		}
	}
	
	public void testCheck() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		
		assertEquals(accessoryTowns.getLastSignificantLetter("абаза"), "а");
		assertEquals(accessoryTowns.getLastSignificantLetter("грозный"), "н");
		assertEquals(accessoryTowns.getLastSignificantLetter("тверь"), "р");
		assertEquals(accessoryTowns.getLastSignificantLetter("твеРь"), "р");
	}
}
