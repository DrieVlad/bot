package chatBot;

import java.util.Scanner;

public class Skeleton 
{
    private static String s_aboutMe = "� ��� ��������, ���������� ������ � �����\n"
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
			Scanner input = new Scanner(System.in);
			String s_useResponse = input.nextLine();
			help(s_useResponse);
			if (s_useResponse.equals("yes")) 
			{
				System.out.println("������ ��������");
				System.out.println("�� ��� ����� ������?");
				System.out.println("� ���� ���� ���������� ���� � \"������\"");
<<<<<<< HEAD
				//Towns.main("f");
=======
			    Towns towns = new Towns();
			    towns.cities();
>>>>>>> 2924e7deb9538de7bded47dde59d6afc10b5eaf5

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
		if (s_Chek.equals("") || s_Chek.equals("help") || s_Chek.equals("������")
				|| s_Chek.equals("������") || s_Chek.equals("�������� � ����")) 
		{
			System.out.println(s_aboutMe);
			return true;
		}
		return false;
	}

}
