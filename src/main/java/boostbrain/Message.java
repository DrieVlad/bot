package boostbrain;

import java.util.ArrayList;

public class Message
{
    private String textMessage;
    private ArrayList<ArrayList<String>> keyboard;
    public boolean isKeyboardNotEmpty = false;
    private String userName;
    private long chatId;
    
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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public long getChatId()
    {
        return chatId;
    }

    public void setChatId(long chatId)
    {
        this.chatId = chatId;
    }
}
