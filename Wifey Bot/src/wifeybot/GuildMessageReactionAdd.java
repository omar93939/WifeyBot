package wifeybot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReactionAdd extends ListenerAdapter {

    public static int editSuggestion = 0;
    public static Message alteredSuggestion;
    public static Message replyWith;

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {

        if (event.getChannel().getName().equals("approve-suggestions") && !event.getMember().getUser().equals(event.getJDA().getSelfUser())) {
            if (event.getReactionEmote().getAsReactionCode().equals("upvote:844408804678434846")) {
                TextChannel suggestions = event.getGuild().getTextChannelById("841314032349872218");

                assert suggestions != null;
                suggestions.sendTyping().queue();
                suggestions.sendMessage(event.retrieveMessage().complete()).queue(message -> {
                    message.addReaction("upvote:844408804678434846").queue();
                    message.addReaction("downvote:844408842737025054").queue();
                });

                event.retrieveMessage().complete().delete().queue();
            } else if (event.getReactionEmote().getAsReactionCode().equals("downvote:844408842737025054")) {
                event.retrieveMessage().complete().delete().queue();
            } else if (event.getReactionEmote().getAsReactionCode().equals("edit:844562514179391538")) {
                event.getChannel().sendMessage("Your next message will overwrite the suggestion; please reply with the altered suggestion below.").queue(message -> replyWith = message);
                editSuggestion = 1;
                alteredSuggestion = event.retrieveMessage().complete();
            }
        }
    }
}
