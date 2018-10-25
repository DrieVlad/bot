package chatBot;

public class AskMillionaire 
{
	public String question;
	public Integer trueAnswer;
    public String[] answers = new String[4];
    
    public AskMillionaire()
    {
	}
    
    public AskMillionaire(String question, int trueAnswer, String[] answers)
    {
		question = this.question;
		trueAnswer = this.trueAnswer;
		for (int i = 0; i < 4; i++) 
		{
			answers[i] = this.answers[i];		
		}
	}
    public String stringAsk()
    {
    	String ask = "";
    	ask = question; 
    	for (int i = 0; i < 4; i++) 
    	{
    		int k = i + 1;
    		ask = String.join(" ",ask + "\n" + k + " вариант ответа: " + answers[i] + "\n"); 
    	}
    	return ask;
    }
    public boolean checkAsk(int answerUser)
    {
    	if (answerUser - 1 == trueAnswer) 
    	{
    		return true;
    	}
    	return false;
    }
}
