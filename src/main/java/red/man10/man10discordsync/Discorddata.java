package red.man10.man10discordsync;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;

public class Discorddata extends ListenerAdapter {
    Man10DiscordSync main;
    JDA jda;
    Guild server;
    public Discorddata(Man10DiscordSync plugin,String token)throws LoginException, InterruptedException{
        main = plugin;
        jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        jda.addEventListener(this);
        server = jda.getGuildById(main.config1.getString("serverid"));
    }
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        main.login.Logins(event.getUser());
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().getId().equalsIgnoreCase(jda.getSelfUser().getId())){
            return;
        }
        main.cr.Chat(event.getMessage());
    }
    public Guild getserver(){
        return server;
    }
    public boolean sendMessage(String message, MessageChannel channel){
        if(channel == null||message == null){
            return false;
        }
        channel.sendMessage(message).queue();
        return true;
    }

    public MessageChannel getChannel(String id){
        if(jda.getTextChannelById(id)==null){
            return null;
        }
        return jda.getTextChannelById(id);
    }
}
