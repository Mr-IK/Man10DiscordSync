package red.man10.man10discordsync.loginapis;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.EventObject;

public class Login_Event extends EventObject{
    User user;
    Message m;
    public Login_Event(User loginuser){
        super(loginuser);
        this.user = loginuser;
    }
    public User getuser(){
        return user;
    }

}
