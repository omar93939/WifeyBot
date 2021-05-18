package wifeybot.deprecated;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import wifeybot.WifeyBot;

import java.awt.*;

public class Rules {

    public static void onRulesCommandReceived(GuildMessageReceivedEvent event, String[] args) {

        if (args[0].equalsIgnoreCase(WifeyBot.prefix + "announce")) {
            event.getChannel().sendTyping().queue();

            EmbedBuilder rules = new EmbedBuilder();


            rules.addField("**<:logo:842453310585045043> Welcome!**", "Welcome to Wifey Talk, the all-around server for gals and pals to come together and share information that you might use in your " +
                    "everyday life. I created this Discord server in the hopes that women from all walks of life can share their experiences and help others or get " +
                    "their own questions answered. Please, feel free to do as you please within this server, with a few exceptions...", false);
            rules.setColor(Color.decode("#ffa2fc"));

            event.getChannel().sendMessage(rules.build()).queue();
            rules.clear();

            rules.addField("**<:logo:842453310585045043> General Rules**", "As with any Discord server--large or small--we have to set some ground rules in order to create a safe environment " +
                    "for everyone. Breaking any of these rules will result in either a kick, or a ban.", false);
            rules.addField("","1) Don't @\u200bSupreme Wifey, @\u200bSupreme Hubby, or @\u200beveryone, except for during the initial setup phase of the server." +
                    "\n\n2) Don't repeatedly ask the same questions, regardless of which text channel you're in. We'll pin questions and answers in channels, so " +
                    "users can go back and look. We have a subreddit, found in the <#841314465864613928> channel description, where users can post questions and answers " +
                    "through a much more organized center of information.\n\n3) Don't self-promote or promote other Discord servers while you are here. We'll post links to subreddits that may " +
                    "be helpful to our users.\n\n4) We want people to share their experiences from different perspectives, so there " +
                    "is no real age limit. However, you should always be careful whilst navigating the server, especially if you are younger than 16.\n\n5) Don't DM people without their consent. If " +
                    "any individual is reported to the moderators for DM'ing without consent, and continuing to do so after consent has been denied, we will kick or ban them from the server.",false);
            rules.setColor(Color.decode("#ffa2fc"));

            event.getChannel().sendMessage(rules.build()).queue();
            rules.clear();

            rules.addField("**<:logo:842453310585045043> Common Sense**", "\u200b\n1) Be respectful of everyone on this server, i.e. no name calling, belittling, intolerance " +
                    "towards others (including racism, hate speech, sexism, etc).\n\n2) Don't bring drama to the server. Although venting your problems to this server will be allowed " +
                    "in the proper channels, don't start drama or bring your outside drama into this server.\n\n3) We won't tolerate any discussion of political controversies in any channel " +
                    "other than <#841313269686992926>, and any such discussions need to remain civil in there too.\n\n4) Don't publish personal information on this server.\n\n5) Don't share links to " +
                    "charities, donations, GoFundMe, etc.\n\n6) Keep the chat English-only. For everyone to speak on topics they are interested in, it's best to keep the server in English.\n\n7) " +
                    "No NSFW content or language. We will have an NSFW channel for asking any NSFW questions; DON'T post any images--exceptions include sex toys ON THEIR OWN and/or images that " +
                    "do not include nudity.\n\n8) Don't post nude photos.", false);
            rules.setColor(Color.decode("#ffa2fc"));

            event.getChannel().sendMessage(rules.build()).queue();
            rules.clear();

            rules.addField("**<:logo:842453310585045043> Text Chat Rules**", "\u200b\n1) Do not spam! Don't spam @'s, don't spam GIFs or pictures.\n\n2) Do not take over conversations " +
                    "and make it all about you. When there's an existing conversation going on, either join in or wait, instead of interrupting the flow of a general conversation.\n\n3) Don't argue. " +
                    "People here are of different age groups, had different experiences and are from all around the world. People will have different views so be civil and mature and move on when necessary." +
                    "\n\n4) Don't be insensitive.\n\n5) No \"shipping\" people or creating fake online relationships on the server. The jokes aren't worth it as it makes people feel uncomfortable. Just don't. " +
                    "Although you may ship fictional characters, this server isn't built like that, and you should always respect the people in this server.", false);
            rules.setColor(Color.decode("#ffa2fc"));

            event.getChannel().sendMessage(rules.build()).queue();
            rules.clear();

            rules.addField("**<:logo:842453310585045043> Voice Chat Rules**", "\u200b\n1) Use Push to Talk or mute yourself if you aren't speaking. Nobody needs to hear unintentionally " +
                    "broadcast munching, coughing, sneezes, etc. especially when voice channels can already be chaotic as is.\n\n2) Please be respectful in voice channels. Any attempt " +
                    "to hijack conversations with music, ear-raping, etc. is not permissible.", false);
            rules.setColor(Color.decode("#ffa2fc"));

            event.getChannel().sendMessage(rules.build()).queue();
            rules.clear();

            rules.addField("**<:logo:842453310585045043> Moderator Rules**", "\u200b\n1) If a moderator notices any rules to be broken, they have the right to delete the unwanted message(s)." +
                    "\n\n2 A moderator might apply a chat mute for up to 72 hours if they deem it necessary to do so. This means you can read chat, but you're not able to participate.\n\n3 Only trusted " +
                    "mods and server owners can give new roles to people. Please do not ask for a role to be given.", false);
            rules.setColor(Color.decode("#ffa2fc"));

            event.getChannel().sendMessage(rules.build()).queue();
            rules.clear();
        }
    }
}
