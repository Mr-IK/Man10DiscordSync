package red.man10.man10discordsync;

import net.dv8tion.jda.core.entities.MessageChannel;

public class DiscordAPI {
    Man10DiscordSync main;
    public DiscordAPI(Man10DiscordSync plugin) {
        main = plugin;
    }

    public boolean sendMessage(String message, MessageChannel channel){
        return main.discord.sendMessage(message,channel);
    }

    public MessageChannel getChannel(String id){
        return main.discord.getChannel(id);
    }

}
