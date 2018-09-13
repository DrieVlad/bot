package chatBot;

import java.util.Scanner;

public class Skeleton 
{
    private static String s_aboutMe = 
    		"� ��� ��������, ���������� ������ � �����\n"
			+ "���� ��� help  ����� ����� � � ������� ��� �� ���� ��������\n"
			+ "� ���� ���������� ���� ������ � ������ ����\n"
			+ "���� ������ ������� ���� � ������������";
    
	public static void main(String[] args) 
	{
		System.out.println("����������� ����!");
		help("help");
        start();
	}
	
	public static void start()
	{
		while (true) 
		{
			System.out.println("������ �� �� �������� �� ����?");
			Scanner inputUser = new Scanner(System.in);
			String s_useResponse = inputUser.nextLine();
			help(s_useResponse);
			if (s_useResponse.equals("yes") 
					|| s_useResponse.equals("��") 
					|| s_useResponse.equals("��")) 
			{
				System.out.println("������ ��������");
				System.out.println("�� ��� ����� ������?");
				System.out.println("� ���� ���� ���������� ���� � \"������\"");
			    
			    Towns.cities();

			} 
			else if (s_useResponse.equals("no")) 
			{
				System.out.println("��������, � ���� ������ ������");

			} 
			else
			{
				System.out.println("��������, � ��� �� �����");
			}
			
		}
	}
	
	public static boolean help(String s_Chek) 
	{
		if (s_Chek.equals("") 
				|| s_Chek.equals("help") 
				|| s_Chek.equals("������")
				|| s_Chek.equals("������") 
				|| s_Chek.equals("�������� � ����")) 
		{
			System.out.println(s_aboutMe);
			return true;
		}
		return false;
	}

}
