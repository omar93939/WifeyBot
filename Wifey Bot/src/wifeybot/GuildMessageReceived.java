package wifeybot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wifeybot.utils.DelayedMessage;

import java.awt.*;
import java.util.Objects;

import static wifeybot.GuildMessageReactionAdd.*;
import static wifeybot.suggestions.SuggestionsCommand.onSuggestionsCommandReceived;

public class GuildMessageReceived extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(WifeyBot.prefix + "info")) {
            event.getChannel().sendTyping().queue();

            EmbedBuilder info = new EmbedBuilder();

            info.addField("**<:logo:842453310585045043> Information**", "Wifey Bot is a multi-purpose Discord Bot created for the WifeyTalk " +
                    "subreddit's native Discord server\n\nTo visit the WifeyTalk subreddit, go to reddit.com/r/WifeyTalk", false);
            info.setColor(Color.decode("#ffa2fc"));

            DelayedMessage.sendMessageDelayedDelete(event, info.build(), 20);
            info.clear();

        } else if (args[0].equalsIgnoreCase(WifeyBot.prefix + "suggest")) {
            onSuggestionsCommandReceived(event, args);
        } else if (editSuggestion == 1 && !Objects.requireNonNull(event.getMember()).getUser().equals(event.getJDA().getSelfUser())) {
            alteredSuggestion.clearReactions().queue();

            MessageEmbed embedToChange = alteredSuggestion.getEmbeds().get(0);
            EmbedBuilder altSuggest = new EmbedBuilder();
            altSuggest.addField("**<:logo:842453310585045043> Suggestion:**", String.join(" ", args), false);
            altSuggest.setThumbnail(Objects.requireNonNull(embedToChange.getThumbnail()).getUrl());
            altSuggest.setFooter(Objects.requireNonNull(embedToChange.getFooter()).getText());
            altSuggest.setColor(embedToChange.getColorRaw());

            event.getChannel().sendMessage(altSuggest.build()).queue(message -> {
                message.addReaction("upvote:844408804678434846").queue();
                message.addReaction("downvote:844408842737025054").queue();
                message.addReaction("edit:844562514179391538").queue();
            });

            editSuggestion = 0;
            alteredSuggestion.delete().queue();
            replyWith.delete().queue();
            event.getMessage().delete().queue();
        }


        else {
            //onRulesCommandReceived(event, args);
        }
    }
}
