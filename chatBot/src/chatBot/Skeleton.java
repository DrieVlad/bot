package chatBot;

import java.util.Scanner;

public class Skeleton 
{
    private static String s_aboutMe = "� ��� ��������, ���������� ������ � �����:))\n"
			+ "���� ��� \"help\" � ����� ����� � � ������� ��� �� ���� ��������.\n"
			+ "� ���� ���������� ���� �� ����� ������ ����,\n"
			+ "� ���� ������ ������� ���� � ������������:))\n"
			+ "������ �� �� �������� �� ����?";
    
	public static void main(String[] args) 
	{
		System.out.println("����������� ����, ��� ������� ����!");
		help("help");
        start();
	}
	
	public static void start()
	{
		while (true) 
		{			
			Scanner input = new Scanner(System.in);
			String s_useResponse = input.nextLine();
			help(s_useResponse);
			if (s_useResponse.equals("yes") || s_useResponse.equals("��") || s_useResponse.equals("��") || s_useResponse.equals("�������"))  
			{
				System.out.println("�� ��� ����� ������?");
				System.out.println("� ���� ���� ���������� ���� � \"������\"");
			    Towns.gameOfCities();
			} 
			else if (s_useResponse.equals("no") || s_useResponse.equals("���") || s_useResponse.equals("���")) 
			{
				System.out.println("��������, � ���� ������ ������:((");
			} 
			else
			{
				System.out.println("��������, � ��� �� �����:((");
			}		
			input.close();
		}
	}
	
	public static boolean help(String s_Chek) 
	{
		if (s_Chek.equals("") || s_Chek.equals("help") || s_Chek.equals("������")
				|| s_Chek.equals("������") || s_Chek.equals("�������� � ����")) 
		{
			System.out.println(s_aboutMe);
			return true;
		}
		return false;
	}
}