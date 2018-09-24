package testing;

import static org.junit.Assert.*;
import  org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;



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
	public void testGetCityFromFile() 
	{
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		Random ran = new Random();
		String check_input = accessoryTowns.getCityFromFile(ran, "�");
		assertEquals(accessoryTowns.checkCity(check_input, "�"), false);
		
		check_input = accessoryTowns.getCityFromFile(ran, "�");
		assertEquals(accessoryTowns.checkCity(check_input, "�"), true);
		
		check_input = accessoryTowns.getCityFromFile(ran, "�");
		assertEquals(accessoryTowns.checkCity(check_input, "�"), true);
		
		check_input = accessoryTowns.getCityFromFile(ran, "�");
		assertEquals(accessoryTowns.checkCity(check_input, "�"), true);
		
	}
	
	@Test
	public void testCheckCity() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		
		assertEquals(accessoryTowns.checkCity("�����", "�"), true);
		assertEquals(accessoryTowns.checkCity("�����", "�"), true);
		assertEquals(accessoryTowns.checkCity("�����", "�"), true);
		assertEquals(accessoryTowns.checkCity("���������", "�"), false);
		assertEquals(accessoryTowns.checkCity("����������", "�"), false);
		assertEquals(accessoryTowns.checkCity("a����", "�"), false);
	}
	
	@Test
	public void testCheckWordDictionary() {
		AccessoryTowns accessoryTowns = new AccessoryTowns();
		ArrayList<String> usedCities = new ArrayList<String>();
		
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
