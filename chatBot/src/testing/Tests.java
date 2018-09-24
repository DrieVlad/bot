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
		check_help = Skeleton.help("������");
		assertEquals(check_help, true);
		check_help = Skeleton.help("������");
		assertEquals(check_help, true);
		check_help = Skeleton.help("������");
		assertEquals(check_help, true);
		check_help = Skeleton.help("������");
		assertEquals(check_help, true);
		check_help = Skeleton.help("�������� � ����");
		assertEquals(check_help, true);
		check_help = Skeleton.help("�������� � ����");
		assertEquals(check_help, true);
		check_help = Skeleton.help("������");
		assertEquals(check_help, false);
		check_help = Skeleton.help("helpwef");
		assertEquals(check_help, false);
	}
	
	@Test
	public void testCheckCity() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		FileTownsReader reader = new FileTownsReader();
		reader.getTowns();
		assertEquals(accessoryTowns.checkCity(reader, "�����", "�"), true);
		assertEquals(accessoryTowns.checkCity(reader, "�����", "�"), true);
		assertEquals(accessoryTowns.checkCity(reader, "�����", "�"), true);
		assertEquals(accessoryTowns.checkCity(reader, "���������", "�"), false);
		assertEquals(accessoryTowns.checkCity(reader, "����������", "�"), false);
		assertEquals(accessoryTowns.checkCity(reader, "a����", "�"), false);
	}
	
	@Test
	public void testCheckWordDictionary() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		HashSet<String> usedCities = new HashSet<String>();
		
		assertEquals(accessoryTowns.checkWordDictionary("�����", usedCities), true);
		assertEquals(accessoryTowns.checkWordDictionary("�����", usedCities), false);
		assertEquals(accessoryTowns.checkWordDictionary("�����", usedCities), false);
		assertEquals(accessoryTowns.checkWordDictionary("������", usedCities), true);
	}
	
	@Test
	public void testCheck() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		
		assertEquals(accessoryTowns.check("�", "�����"), "�");
		assertEquals(accessoryTowns.check("�", "�������"), "�");
		assertEquals(accessoryTowns.check("�", "�����"), "�");
		assertEquals(accessoryTowns.check("�", "�����"), "�");
	}
}
