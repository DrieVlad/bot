package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import chatBot.Skeleton;
import chatBot.AccessoryTowns;

public class Tests 
{

	@Test
	public void testHelp() 
	{
		boolean check_help = Skeleton.help("help");
		assertEquals(check_help, true);
		check_help = Skeleton.help("Help");
		assertEquals(check_help, true);;
		check_help = Skeleton.help("помоги");
		assertEquals(check_help, true);
		check_help = Skeleton.help("Помоги");
		assertEquals(check_help, true);
		check_help = Skeleton.help("помощь");
		assertEquals(check_help, true);
		check_help = Skeleton.help("Помощь");
		assertEquals(check_help, true);
		check_help = Skeleton.help("Расскажи о себе");
		assertEquals(check_help, true);
		check_help = Skeleton.help("расскажи о себе");
		assertEquals(check_help, true);
		check_help = Skeleton.help("ввапва");
		assertEquals(check_help, false);
		check_help = Skeleton.help("helpwef");
		assertEquals(check_help, false);
	}
	
	@Test
	public void testGetCityFromFile() 
	{
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		Random ran = new Random();
		String check_input = accessoryTowns.getCityFromFile(ran, "Я");
		assertEquals(accessoryTowns.checkCity(check_input, "а"), false);
		
		check_input = accessoryTowns.getCityFromFile(ran, "А");
		assertEquals(accessoryTowns.checkCity(check_input, "а"), true);
		
		check_input = accessoryTowns.getCityFromFile(ran, "а");
		assertEquals(accessoryTowns.checkCity(check_input, "а"), true);
		
		check_input = accessoryTowns.getCityFromFile(ran, "Я");
		assertEquals(accessoryTowns.checkCity(check_input, "я"), true);
		
	}
	
	@Test
	public void testCheckCity() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		
		assertEquals(accessoryTowns.checkCity("абаза", "а"), true);
		assertEquals(accessoryTowns.checkCity("Абаза", "а"), true);
		assertEquals(accessoryTowns.checkCity("АбАзА", "а"), true);
		assertEquals(accessoryTowns.checkCity("вамрвашмт", "а"), false);
		assertEquals(accessoryTowns.checkCity("авамрвашмт", "а"), false);
		assertEquals(accessoryTowns.checkCity("aбаза", "а"), false);
	}
	
	@Test
	public void testCheckWordDictionary() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		ArrayList<String> usedCities = new ArrayList<String>();
		
		assertEquals(accessoryTowns.checkWordDictionary("абаза", usedCities), true);
		assertEquals(accessoryTowns.checkWordDictionary("абаза", usedCities), false);
		assertEquals(accessoryTowns.checkWordDictionary("Абаза", usedCities), false);
		assertEquals(accessoryTowns.checkWordDictionary("Абакан", usedCities), true);
	}
	
	@Test
	public void testCheck() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		
		assertEquals(accessoryTowns.check("а", "абаза"), "а");
		assertEquals(accessoryTowns.check("й", "грозный"), "н");
		assertEquals(accessoryTowns.check("ь", "тверь"), "р");
		assertEquals(accessoryTowns.check("ь", "твеРЬ"), "р");
	}
}
