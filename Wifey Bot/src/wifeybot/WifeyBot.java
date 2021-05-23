package wifeybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import wifeybot.utils.CommandManagerListener;

import javax.security.auth.login.LoginException;

public class WifeyBot {
    public static JDA jda;
    public static final String prefix = "!";

    // Main method
    public static void main(String[] args) throws LoginException {

        String token = "ODQxNzk3MDQ4MDE2OTYxNTg3.YJr-mA.wS0eIdht7mAn5nnDR0htt4x94Kg";
        jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES).disableCache(
                CacheFlag.CLIENT_STATUS,
                CacheFlag.ACTIVITY,
                CacheFlag.EMOTE
        ).enableCache(CacheFlag.VOICE_STATE).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("42 LIFEHACKS TO MAKE YOUR LIFE EASIER!"));

        jda.addEventListener(new GuildMessageReceived());
        jda.addEventListener(new GuildMessageReactionAdd());
        jda.addEventListener(new CommandManagerListener());

    }

}
