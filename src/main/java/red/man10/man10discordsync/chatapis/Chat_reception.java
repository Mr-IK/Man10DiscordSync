package red.man10.man10discordsync.chatapis;

import net.dv8tion.jda.core.entities.Message;

import javax.swing.*;
import javax.swing.event.EventListenerList;

public class Chat_reception extends JFrame {

    public Chat_reception(){
    }
    EventListenerList evList = new EventListenerList();
    public void Chat(Message message) {
        // マイイベントオブジェクトを作成します
        Chat_reception_Event EventObject = new Chat_reception_Event(message);

        // イベントリスナーリストから、マイイベントリスナーを取得します
        Chat_reception_Listener[] listeners = evList.getListeners(Chat_reception_Listener.class);

        // 取得したマイイベントリスナーでループを回し
        for (Chat_reception_Listener myEventListener : listeners) {
            // イベントに該当するメソッドを呼び出します
            myEventListener.Chat_reception(EventObject);
        }
    }

    public void addChat_reception_Listener(Chat_reception_Listener listener) {
        evList.add(Chat_reception_Listener.class,listener);
    }
    public void remove_reception_Listener(Chat_reception_Listener listener) {
        evList.remove(Chat_reception_Listener.class,listener);
    }
}
