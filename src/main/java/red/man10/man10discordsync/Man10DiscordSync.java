package red.man10.man10discordsync;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import red.man10.man10discordsync.chatapis.Chat_reception;
import red.man10.man10discordsync.loginapis.Login;

import javax.security.auth.login.LoginException;

public class Man10DiscordSync extends JavaPlugin implements Listener {
    FileConfiguration config1;
    Discorddata discord;
    Chat_reception cr;
    Login login;
    DiscordAPI api;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        config1 = getConfig();
        getServer().getPluginManager().registerEvents(this,this);
        cr = new Chat_reception();
        login = new Login();
        api = new DiscordAPI(this);
        try {
            discord = new Discorddata(this, config1.getString("token"));
        }catch (LoginException e){
        }catch (InterruptedException e1){
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        discord.jda.shutdown();
    }

    public DiscordAPI getApi(){
        return api;
    }

    public Chat_reception getChatreception(){
        return cr;
    }

    public Discorddata getData(){
        return discord;
    }

    public Login getLogin() { return login;}
}
