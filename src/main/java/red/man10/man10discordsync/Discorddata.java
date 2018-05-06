package red.man10.man10discordsync;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.entity.Player;
import javax.security.auth.login.LoginException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        if(!event.getChannel().getId().equalsIgnoreCase(main.config1.getString("botchannel"))){
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
    public boolean playercreate(Player p,String id) {
        String sql = "INSERT INTO "+main.mysql.DB+".discord_link (name,uuid,discord_id) VALUES ('"+p.getName()+"' ,'"+p.getUniqueId().toString()+"' , '"+id+"');";
        boolean done = main.mysql.execute(sql);
        return done;
    }
    public boolean playercontain(Player p) {
        String sql = "SELECT * FROM "+main.mysql.DB+".discord_link WHERE uuid = '"+p.getUniqueId().toString()+"';";
        ResultSet rs = main.mysql.query(sql);
        if(rs==null){
            return false;
        }
        try {
            if(rs.next()) {
                // UUIDが一致するユーザが見つかった
                return true;
            }
            return false;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }
    public String getdiscordname(Player p) {
        String sql = "SELECT * FROM "+main.mysql.DB+".discord_link WHERE uuid = '"+p.getUniqueId().toString()+"';";
        ResultSet rs = main.mysql.query(sql);
        if(rs==null){
            return null;
        }
        try {
            if(rs.next()) {
                // UUIDが一致するユーザが見つかった
                String discord_id = rs.getString("discord_id");
                return jda.getUserById(discord_id).getName();
            }
            return null;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public String getdiscordid(Player p) {
        String sql = "SELECT * FROM "+main.mysql.DB+".discord_link WHERE uuid = '"+p.getUniqueId().toString()+"';";
        ResultSet rs = main.mysql.query(sql);
        if(rs==null){
            return null;
        }
        try {
            if(rs.next()) {
                // UUIDが一致するユーザが見つかった
                String discord_id = rs.getString("discord_id");
                return discord_id;
            }
            return null;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public String getminecraftname(String discordid) {
        String sql = "SELECT * FROM "+main.mysql.DB+".discord_link WHERE discord_id = '"+discordid+"';";
        ResultSet rs = main.mysql.query(sql);
        if(rs==null){
            return null;
        }
        try {
            if(rs.next()) {
                // UUIDが一致するユーザが見つかった
                String discord_id = rs.getString("name");
                return jda.getUserById(discord_id).getName();
            }
            return null;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }
}
