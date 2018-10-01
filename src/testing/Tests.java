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
	public void testCheckCity() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		FileTownsReader reader = new FileTownsReader();
		reader.getTowns();
		assertEquals(accessoryTowns.checkCity(reader, "абаза", "а"), true);
		assertEquals(accessoryTowns.checkCity(reader, "Абаза", "а"), true);
		assertEquals(accessoryTowns.checkCity(reader, "АбАзА", "а"), true);
		assertEquals(accessoryTowns.checkCity(reader, "вамрвашмт", "а"), false);
		assertEquals(accessoryTowns.checkCity(reader, "авамрвашмт", "а"), false);
		assertEquals(accessoryTowns.checkCity(reader, "aбаза", "а"), false);
	}
	
	@Test
	public void testCheckWordDictionary() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		HashSet<String> usedCities = new HashSet<String>();
		
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
		assertEquals(accessoryTowns.check("ь", "твеРь"), "р");
	}
}
