package wifeybot.music;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import wifeybot.utils.CommandContext;
import wifeybot.utils.MyCommand;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static wifeybot.utils.DelayedMessage.deleteAfter;
import static wifeybot.utils.DelayedMessage.sendMessageDelayedDelete;

@SuppressWarnings("ConstantConditions")
public class PlayCommand implements MyCommand {

    @Override
    public void handle(CommandContext ctx) {

        if (ctx.getChannel().getName().equals("music-bot")) {
            final TextChannel channel = ctx.getChannel();

            if (ctx.getArgs().isEmpty()) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>You need to add a YouTube link or search!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            final Member self = ctx.getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();
            final Member member = ctx.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();

            if (selfVoiceState.inVoiceChannel()) {
                if (memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {

                    String link = String.join(" ", ctx.getArgs());

                    if (!isUrl(link)) {
                        link = "ytsearch:" + link;
                    }

                    PlayerManager.getInstance().loadAndPlay(channel, link);
                    deleteAfter(ctx.getMessage(), 15);

                } else {
                    EmbedBuilder inVoiceChannel = new EmbedBuilder();
                    inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>You need to be in the same Voice Channel as WifeyBot to use this command!**");
                    inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                    sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                    inVoiceChannel.clear();
                }
                return;
            } else if (!memberVoiceState.inVoiceChannel()) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>You need to be in a Voice Channel to use this command!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            final AudioManager audioManager = ctx.getGuild().getAudioManager();
            final VoiceChannel memberChannel = memberVoiceState.getChannel();

            audioManager.openAudioConnection(memberChannel);

            EmbedBuilder inVoiceChannel = new EmbedBuilder();
            inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>Connecting to \uD83D\uDD0A-" + memberChannel.getName() + "...**");
            inVoiceChannel.setColor(Color.decode("#ffa2fc"));

            sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
            inVoiceChannel.clear();

            String link = String.join(" ", ctx.getArgs());

            if (!isUrl(link)) {
                link = "ytsearch:" + link;
            }

            PlayerManager.getInstance().loadAndPlay(channel, link);

        } else {
            EmbedBuilder inVoiceChannel = new EmbedBuilder();
            inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>You cannot use this command here!**");
            inVoiceChannel.setColor(Color.decode("#ffa2fc"));

            sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
            inVoiceChannel.clear();
        }
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Plays a song\nUsage: !play <link>";
    }

    private boolean isUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
