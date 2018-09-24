package chatBot;

import java.util.Scanner;
import java.util.Random;

public class Skeleton 
{
	private static String[] s_questions = {
			"��� ���� �����?",
			"��� ������ �� �����?",
			"��� � ���� ����������?",
			"��� �� ������?",
			"������� ���� ���?",
			"��� ����� �� ��������?",
			"��� �� ���������� ������?",
			"���� ����� ����������� ������, ��� ����� ���?",
			"������� ������� �� �����?",
			"�������� � ���� �����",
			"������ �� �����?",
			"����� �������� �� ������?",
			"��� ������ ��������� ��������?",
			"��� ����� �� ���������?",
			"� ��� �� �����?",
			"�� ��������, ��������� ��� ����� ���� �������?",
			"����� � ���� ���������?",
			"����� ����� ���������� ������ � ����� �����?",
			"����� ������ �������� ��������� �����?",
			"�����, � ����� ��� ������� ����� ����?",
			"����� ���� ������� �����?"
	};
	
	private static String[] s_phrases = {
			"��, ��� ���������!",
			"������������!",
			"�����������!", 
			"������ �������!", 
			"�����������!",
			"������ ����������!",
			"�� ���� ������!", 
			"� ��������!", 
			"������ �� �����...",
			"��� ��������...",
			"��������!",
			"�����!",
			"��������� ������:)",
			"����������!", 
			"������, ��-��:)",
			"������!",
			"����.",
			"��������� ���������!"
	};
	
    private static String s_aboutMe = "� ��� ��������, ���������� ������ � �����:))\n"
			+ "���� ��� � ����� �����: \n"
			+ "   help \n"
			+ "   ������ \n"
			+ "   ������ \n"
			+ "   �������� � ����\n\n"
			+ "� � ������� ��� �� ���� ��������.\n\n"
			+ "� ���� ���������� ���� �� ����� ������ ����,\n"
			+ "� ���� ������ ������� ���� � ������������:))\n\n"
			+ "����� ������� ���� ������ ���: \n"
			+ "   \"��\" ��� \"�������\" \n\n"
			+ "����� ���������� �� ���� ������ ������ ���:\n"
			+  "   \"���\" ��� \"����� ������\" \n";
    
	public static void main(String[] args) 
	{
		System.out.println("����������� ����, ��� ������� ����!\n");
		help("help");
        start();
	}
	
	public static void start()
	{
		Scanner input = new Scanner(System.in);	
		String s_useResponse  = "";
		int count;
		Random randomer1 = new Random();
		while (true) 
		{		
			System.out.println(" ��������?");
			s_useResponse = input.nextLine();
			s_useResponse = s_useResponse.toLowerCase();
			help(s_useResponse);
			if (s_useResponse.equals("yes")
					|| s_useResponse.equals("��")
					|| s_useResponse.equals("�������")
					|| s_useResponse.equals("�����")
					|| s_useResponse.equals("�������"))  
			{				
				System.out.println("�� ��� ����� ������?");
				System.out.println("� ���� ���� ���������� ���� � \"������\"");
			    Towns.gameOfCities();
			} 
			else if (s_useResponse.equals("no")
					|| s_useResponse.equals("���")
					|| s_useResponse.equals("����� ������")) 
			{
				count = randomer1.nextInt(s_questions.length);				
				System.out.println(s_questions[count]);
				s_useResponse = input.nextLine();
				help(s_useResponse);
				count = randomer1.nextInt(s_phrases.length);				
				System.out.print(s_phrases[count] + " ");
			} 
			else
			{
				System.out.println("��������, � ��� �� �����:((");
			}			
		}		
	}
	
	public static boolean help(String s_Check) 
	{
		s_Check = s_Check.toLowerCase();
		if (s_Check.equals("help")
				|| s_Check.equals("������")
				|| s_Check.equals("������")
				|| s_Check.equals("�������� � ����")) 
		{
			System.out.print(s_aboutMe);
			return true;
		}
		return false;
	}
}
