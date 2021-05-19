package wifeybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class WifeyBot {
    public static JDA jda;
    public static String prefix = "!";

    // Main method
    public static void main(String[] args) throws LoginException {

        String token = "ODQxNzk3MDQ4MDE2OTYxNTg3.YJr-mA.GmNRhbHHUhMPAcTHXph7nSN6z1U";
        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("42 LIFEHACKS TO MAKE YOUR LIFE EASIER!"));

        jda.addEventListener(new GuildMessageReceived());
        jda.addEventListener(new GuildMessageReactionAdd());

    }

}
