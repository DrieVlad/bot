package testing;


import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import chatBot.AccessoryTowns;
import chatBot.EntryPointForTest;
import chatBot.PhrasesBot;
import chatBot.TownsContent;

public class Tests 
{
	@Test
	public void testCheckCity() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		TownsContent reader = new TownsContent("Города");
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
	
	@Test
	public void testCheck() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		
		assertEquals(accessoryTowns.getLastSignificantLetter("абаза"), "а");
		assertEquals(accessoryTowns.getLastSignificantLetter("грозный"), "н");
		assertEquals(accessoryTowns.getLastSignificantLetter("тверь"), "р");
		assertEquals(accessoryTowns.getLastSignificantLetter("твеРь"), "р");
	}
	
	@Test
	public void testAllLogicBot() 
	{
		String botAnswer = "";
		final EntryPointForTest point = new EntryPointForTest();
		botAnswer = point.forTest(" ");
		assertEquals(botAnswer.equals("Приветствую тебя, мой дорогой друг!\n" + PhrasesBot.s_aboutMe), true);
		botAnswer = point.forTest("игра");
		assertEquals(botAnswer.equals("У меня есть две игры на выбор: \"Города\" и \"Миллионер\". \n"
				+ "Пиши \"1\", если хочешь сыграть в \"Города\" " + "и \"2\", если хочешь сыграть в \"Миллионер\". \n"
				+ "Во что будем играть? "), true);
		botAnswer = point.forTest("2");
	    botAnswer = point.forTest("помощь");
	    assertEquals(botAnswer.equals(PhrasesBot.s_aboutMe), true);
	    botAnswer = point.forTest("устал");
	    assertEquals(botAnswer.equals("Приветствую тебя, мой дорогой друг!\n" + PhrasesBot.s_aboutMe), true);
	    botAnswer = point.forTest("1");
	    botAnswer = point.forTest("игра");
	    botAnswer = point.forTest("1");
	    assertEquals(botAnswer.equals("Хороший выбор! Введите количество игроков!"), true);
	    botAnswer = point.forTest("помощь");
	    assertEquals(botAnswer.equals(PhrasesBot.s_aboutMe), true);
	    botAnswer = point.forTest("устал");
	    assertEquals(botAnswer.equals("Приветствую тебя, мой дорогой друг!\n" + PhrasesBot.s_aboutMe), true);
	    botAnswer = point.forTest("диалог");
	    assertEquals(botAnswer.equals("Как тебя зовут?"), true);
	    
		
	}
}
