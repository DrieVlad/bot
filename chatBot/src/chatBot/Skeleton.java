package chatBot;

import java.util.Random;


public class Skeleton 
{
	private static InOut inOut = new InOut();
	
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
		inOut.printData("����������� ����, ��� ������� ����!\n");
		help("help");
        start();
	}
	
	public static void start()
	{
		int count;
		Random randomer1 = new Random();
		while (true) 
		{	
			String s_useResponse;
			inOut.printData(" ��������?");
			s_useResponse = inOut.scanData();
			s_useResponse = s_useResponse.toLowerCase();
			help(s_useResponse);
			if (s_useResponse.equals("yes")
					|| s_useResponse.equals("��")
					|| s_useResponse.equals("�������")
					|| s_useResponse.equals("�����")
					|| s_useResponse.equals("�������"))  
			{				
				inOut.printData("�� ��� ����� ������?");
				inOut.printData("� ���� ���� ���������� ���� � \"������\"");
			    Towns.gameOfCities();
			} 
			else if (s_useResponse.equals("no")
					|| s_useResponse.equals("���")
					|| s_useResponse.equals("����� ������")) 
			{
				count = randomer1.nextInt(s_questions.length);				
				inOut.printData(s_questions[count]);
				s_useResponse = inOut.scanData();
				help(s_useResponse);
				count = randomer1.nextInt(s_phrases.length);				
				inOut.printData(s_phrases[count] + " ");
			} 
			else
			{
				inOut.printData("��������, � ��� �� �����:((");
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
			inOut.printData(s_aboutMe);
			return true;
		}
		return false;
	}
}