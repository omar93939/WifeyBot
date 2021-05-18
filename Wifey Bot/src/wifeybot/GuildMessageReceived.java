package wifeybot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

import static wifeybot.deprecated.Rules.onRulesCommandReceived;
import static wifeybot.suggestions.SuggestionsCommand.onSuggestionsCommandReceived;
import static wifeybot.utils.Utils.sendMessageDelayed;

public class GuildMessageReceived extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(WifeyBot.prefix + "info")) {
            event.getChannel().sendTyping().queue();

            EmbedBuilder info = new EmbedBuilder();

            info.addField("**<:logo:842453310585045043> Information**", "Wifey Bot is a multi-purpose Discord Bot created for the WifeyTalk " +
                    "subreddit's native Discord server\n\nTo visit the WifeyTalk subreddit, go to reddit.com/r/WifeyTalk", false);
            info.setColor(Color.decode("#ffa2fc"));

            sendMessageDelayed(event, info.build(), 20);
            info.clear();

        } else if (args[0].equalsIgnoreCase(WifeyBot.prefix + "suggest")) {
            onSuggestionsCommandReceived(event, args);
        } else {
            onRulesCommandReceived(event, args);
        }
    }
}
