package red.man10.man10discordsync;

import net.dv8tion.jda.core.entities.MessageChannel;
import org.bukkit.entity.Player;

public class DiscordAPI {
    Man10DiscordSync main;
    public DiscordAPI(Man10DiscordSync plugin) {
        main = plugin;
    }
    public boolean ContainPlayer(Player p){
        return main.discord.playercontain(p);
    }

    public boolean getPlayer(Player p){
        return main.discord.playercontain(p);
    }

    public String getDiscordName(Player p){
        return main.discord.getdiscordname(p);
    }

    public String getDiscordID(Player p){
        return main.discord.getdiscordname(p);
    }

    public String getMinecraftName(String discordid){
        return main.discord.getminecraftname(discordid);
    }

    public boolean sendMessage(String message, MessageChannel channel){
        return main.discord.sendMessage(message,channel);
    }

}
