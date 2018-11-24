package boostbrain;

public class Chat {
    
    private Bot bot;
    private Object locker;
    
    public Chat(Bot bot, Object locker)
    {
        this.bot = bot;
        this.locker = locker;
    }
    
    public Bot getBot(){ return bot; }
    
    public Object getLocker()
    {
        return locker;
    }
}