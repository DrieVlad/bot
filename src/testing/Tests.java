package testing;


import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import chatBot.AccessoryTowns;
import chatBot.Bot;
import chatBot.PhrasesBot;
import chatBot.TownsContent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		Bot bot = new Bot();
		botAnswer = bot.reply(" ");
		assertEquals("Приветствую тебя, мой дорогой друг!\n" + PhrasesBot.s_aboutMe, botAnswer);
		botAnswer = bot.reply("игра");
		assertEquals("У меня есть две игры на выбор: \"Города\" и \"Миллионер\". \n"
				+ "Пиши \"1\", если хочешь сыграть в \"Города\" " + "и \"2\", если хочешь сыграть в \"Миллионер\". \n"
				+ "Во что будем играть? ", botAnswer);
		botAnswer = bot.reply("2");
	    botAnswer = bot.reply("помощь");
	    assertEquals(PhrasesBot.s_aboutMe, botAnswer);
	    botAnswer = bot.reply("устал");
	    assertEquals("Приветствую тебя, мой дорогой друг!\n" + PhrasesBot.s_aboutMe, botAnswer);
	    botAnswer = bot.reply("1");
	    botAnswer = bot.reply("игра");
	    botAnswer = bot.reply("1");
	    assertEquals("Хороший выбор! Введите количество игроков!", botAnswer);
	    botAnswer = bot.reply("помощь");
	    assertEquals(PhrasesBot.s_aboutMe, botAnswer);
	    botAnswer = bot.reply("устал");
	    assertEquals("Приветствую тебя, мой дорогой друг!\n" + PhrasesBot.s_aboutMe, botAnswer);
	    botAnswer = bot.reply("диалог");
	    assertEquals("Как тебя зовут?", botAnswer);
	    
		
	}
}
