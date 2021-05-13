package wifeybot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(WifeyBot.prefix + "info")) {
            event.getChannel().sendTyping().queue();

            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("<:info:842456943997747260>Information");
            info.setDescription("Wifey Bot is a multi-purpose Discord Bot created for the WifeyTalk subreddit's native Discord server\n\nTo visit the WifeyTalk subreddit, go to reddit.com/r/WifeyTalk");


            event.getChannel().sendMessage(info.build()).queue();
        } else if (args[0].equalsIgnoreCase(WifeyBot.prefix + "")) {

        }
    }
}
