package chatBot;

import java.util.Scanner;

public class Skeleton {
	private static String s_aboutMe = "� ���, ���������� ������ � �����";

	public static void main(String[] args) {
		System.out.println("����������� ����!");
		System.out.println(s_aboutMe);
		while (true) {

			System.out.println("������ �� �� �������� �� ����?");
			Scanner input = new Scanner(System.in);
			String s_useResponse = input.nextLine();
			if (s_useResponse.equals("yes")) 
			{
				System.out.println("������ ��������");
				System.out.println("�� ��� ����� ������?");
				System.out.println("� ���� ���� ���������� ���� � \"������\"");
				//Towns.main("f");

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

}
