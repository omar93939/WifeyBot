package wifeybot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import wifeybot.utils.CommandContext;
import wifeybot.utils.MyCommand;

import java.awt.*;

import static wifeybot.music.QueueCommand.formatTime;
import static wifeybot.utils.DelayedMessage.sendMessageDelayedDelete;

public class NowPlayingCommand implements MyCommand {

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
            final AudioTrack track = audioPlayer.getPlayingTrack();

            if (track == null) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>There is no Track playing currently!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            final AudioTrackInfo info = track.getInfo();

            EmbedBuilder builder = new EmbedBuilder();
            builder.addField("**<:logo:842453310585045043> Now playing:**","\u200b\n[" + info.title + "](" + info.uri + ")\n`" + formatTime(info.length) + "`", false);
            builder.setColor(Color.decode("#ffa2fc"));

            sendMessageDelayedDelete(ctx.getEvent(), builder.build(), 15);
            builder.clear();
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
        return "nowplaying";
    }

    @Override
    public String getHelp() {
        return "Shows the song which is currently playing";
    }
}
