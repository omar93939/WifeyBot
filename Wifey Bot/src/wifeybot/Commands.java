package wifeybot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(WifeyBot.prefix + "info")) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("*Insert Info Here*").queue();
        }
    }
}
