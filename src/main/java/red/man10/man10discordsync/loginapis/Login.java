package red.man10.man10discordsync.loginapis;

import net.dv8tion.jda.core.entities.User;

import javax.swing.*;
import javax.swing.event.EventListenerList;

public class Login extends JFrame {

    public Login(){
    }

    EventListenerList evList = new EventListenerList();
    public void Logins(User user) {
        // マイイベントオブジェクトを作成します
        Login_Event EventObject = new Login_Event(user);

        // イベントリスナーリストから、マイイベントリスナーを取得します
        Login_Listener[] listeners = evList.getListeners(Login_Listener.class);

        // 取得したマイイベントリスナーでループを回し
        for (Login_Listener myEventListener : listeners) {
            // イベントに該当するメソッドを呼び出します
            myEventListener.Login(EventObject);
        }
    }

    public void addLogin_Listener(Login_Listener listener) {
        evList.add(Login_Listener.class,listener);
    }
    public void remove_Login_Listener(Login_Listener listener) {
        evList.remove(Login_Listener.class,listener);
    }
}