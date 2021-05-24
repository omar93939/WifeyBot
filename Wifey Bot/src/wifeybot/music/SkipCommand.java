package wifeybot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import wifeybot.utils.CommandContext;
import wifeybot.utils.MyCommand;

import java.awt.*;

import static wifeybot.utils.DelayedMessage.sendMessageDelayedDelete;

public class SkipCommand implements MyCommand {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) {

        if (ctx.getChannel().getName().equals("music-bot")) {
            final Member self = ctx.getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();

            if (!selfVoiceState.inVoiceChannel()) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>WifeyBot needs to be in a voice channel for you to use this command!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            final Member member = ctx.getSelfMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();

            if (!memberVoiceState.inVoiceChannel()) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>You need to be in a Voice Channel to use this command!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>You need to be in the same Voice Channel as WifeyBot to use this command!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
            final AudioPlayer audioPlayer = musicManager.audioPlayer;

            if (audioPlayer.getPlayingTrack() == null) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>There is no Track playing currently!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            musicManager.scheduler.nextTrack();
            EmbedBuilder inVoiceChannel = new EmbedBuilder();
            inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>Skipped the Track!**");
            inVoiceChannel.setColor(Color.decode("#ffa2fc"));

            sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
            inVoiceChannel.clear();
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
        return "skip";
    }

    @Override
    public String getHelp() {
        return "Skips the current track";
    }
}
