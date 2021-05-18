package wifeybot.suggestions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import wifeybot.WifeyBot;

import java.awt.*;
import java.util.Arrays;

import static wifeybot.utils.Utils.sendMessageDelayed;

public class SuggestionsCommand {

    public static void onSuggestionsCommandReceived(GuildMessageReceivedEvent event, String[] args) {

        if (args.length < 2) {
            event.getChannel().sendTyping().queue();

            EmbedBuilder wrongSuggest = new EmbedBuilder();
            wrongSuggest.addField("**<:logo:842453310585045043> Incorrect Command Usage**", "You need to add a suggestion.", false);
            wrongSuggest.addField("**Usage:**", WifeyBot.prefix + "suggest <suggestion>", false);
            wrongSuggest.setFooter("<> = Required");
            wrongSuggest.setColor(Color.decode("#ffa2fc"));

            //event.getChannel().sendMessage(wrongSuggest.build()).queue();

            sendMessageDelayed(event, wrongSuggest.build(), 30);
            wrongSuggest.clear();
        } else if (args.length > 51 || String.join(" ", args).length() - 9 > 250) {
            event.getChannel().sendTyping().queue();

            EmbedBuilder wrongSuggest = new EmbedBuilder();
            wrongSuggest.addField("**<:logo:842453310585045043> Incorrect Command Usage**", "Your suggestion cannot be more than 50 words or 250 characters long.", false);
            wrongSuggest.addField("**Usage:**", WifeyBot.prefix + "suggest <suggestion>", false);
            wrongSuggest.setFooter("<> = Required");
            wrongSuggest.setColor(Color.decode("#ffa2fc"));

            //event.getChannel().sendMessage(wrongSuggest.build()).queue();

            sendMessageDelayed(event, wrongSuggest.build(), 30);
            wrongSuggest.clear();


        } else {
            EmbedBuilder suggest = new EmbedBuilder();

            String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);

            suggest.addField("**<:logo:842453310585045043> Suggestion:**", String.join(" ", commandArgs), false);
            suggest.setThumbnail(event.getAuthor().getAvatarUrl());
            suggest.setFooter("Suggested By: " + event.getAuthor().getAsTag());
            suggest.setColor(Color.decode("#ffa2fc"));

            TextChannel suggestions = event.getGuild().getTextChannelById("844253622409625670");

            assert suggestions != null;
            suggestions.sendMessage(suggest.build()).queue();
            suggest.clear();


            suggest.addField("**<:logo:842453310585045043> Suggestion Command Success!**", "Thank you for your suggestion, it has been successfully " +
                    "forwarded to the admins and if it is deemed an acceptable suggestion, you will likely see it in <#841314032349872218> shortly!", false);
            suggest.setColor(Color.decode("#ffa2fc"));

            sendMessageDelayed(event, suggest.build(), 30);
            suggest.clear();
        }
    }
}
