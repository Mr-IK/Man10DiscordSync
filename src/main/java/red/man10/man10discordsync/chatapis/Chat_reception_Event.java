package red.man10.man10discordsync.chatapis;

import net.dv8tion.jda.core.entities.Message;

import java.util.EventObject;

public class Chat_reception_Event extends EventObject{
    String message;
    Message m;
    public Chat_reception_Event(Message message){
        super(message);
        this.message = message.getContentRaw();
        this.m = message;
    }
    public String getcontent(){
        return message;
    }
    public Message getMessage(){
        return m;
    }

}