package chatBot;

public class AskMillionaire 
{
	String question;
	int trueAnswer;
    String[] answers = new String[4];
    
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
    public void PrintAsk()
    {
    	InOut.printer.writeDataString(question); 
    	for (int i = 0; i < 4; i++) 
    	{
    		int k = i + 1;
    		InOut.printer.writeDataString(k + " вариант ответа: " + answers[i]); 
    	}
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
