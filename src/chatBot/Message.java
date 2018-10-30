package chatBot;

import java.util.ArrayList;

public class Message
{
	private String textMessage;
	private ArrayList<ArrayList<String>> keyboard;
	public boolean isKeyboardNotEmpty = false;
    
	public String getTextMessage()
	{
		return textMessage; 
	}
	
	public void setTextMessage(String textMessage)
	{
		this.textMessage = textMessage;
	}
	
	public ArrayList<ArrayList<String>> getKeyboard()
	{
		return keyboard; 
	}
	
	public void setKeyboard(ArrayList<ArrayList<String>> keyboard)
	{
		this.keyboard = keyboard;
		isKeyboardNotEmpty = true;
	}
}
