package wifeybot.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import wifeybot.utils.CommandContext;
import wifeybot.utils.MyCommand;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import static wifeybot.utils.DelayedMessage.sendMessageDelayedDelete;

public class QueueCommand implements MyCommand {

    @Override
    public void handle(CommandContext ctx) {

        if (ctx.getChannel().getName().equals("music-bot")) {
            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
            final BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;

            if (queue.isEmpty()) {
                EmbedBuilder inVoiceChannel = new EmbedBuilder();
                inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>The queue is currently empty!**");
                inVoiceChannel.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
                inVoiceChannel.clear();
                return;
            }

            final int trackCount = Math.min(queue.size(), 10);
            final ArrayList<AudioTrack> trackList = new ArrayList<>(queue);
            final AudioTrackInfo nowPlayingInfo = musicManager.audioPlayer.getPlayingTrack().getInfo();
            final AudioTrackInfo nextTrackInfo = trackList.get(0).getInfo();

            long totalTime = 0;

            for (int i = 0; i < queue.size(); i++) {
                final AudioTrack track = trackList.get(i);
                final AudioTrackInfo info = track.getInfo();

                totalTime = totalTime + info.length;
            }

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("**<:embedlogo:842456943997747260>Current Queue:**");
            builder.addField("__Now Playing:__", "[" + nowPlayingInfo.title + "](" + nowPlayingInfo.uri + ") | `" + formatTime(nowPlayingInfo.length) + "`", false);
            builder.addField("__Up Next:__", "`1.` [" + nextTrackInfo.title + "](" + nextTrackInfo.uri + ") | `" + formatTime(nextTrackInfo.length) + "`", false);
            builder.setFooter(queue.size() + " songs in queue | " + formatTime(totalTime) + " total length");
            builder.setColor(Color.decode("#ffa2fc"));

            for (int i = 1; i < trackCount; i++) {
                final AudioTrack track = trackList.get(i );
                final AudioTrackInfo info = track.getInfo();

                builder.addField("", "`" + (i + 1) + ".` [" + info.title + "](" + info.uri + ") | `" + formatTime(info.length) + "`", false);
            }

            sendMessageDelayedDelete(ctx.getEvent(), builder.build(), 30);
            builder.clear();
        } else {
            EmbedBuilder inVoiceChannel = new EmbedBuilder();
            inVoiceChannel.setTitle("**<:embedlogo:842456943997747260>You cannot use this command here!**");
            inVoiceChannel.setColor(Color.decode("#ffa2fc"));

            sendMessageDelayedDelete(ctx.getEvent(), inVoiceChannel.build(), 15);
            inVoiceChannel.clear();
        }

    }

    public static String formatTime(long timeInMillis) {
        final long hours = timeInMillis / 3600000;
        final long minutes = (timeInMillis % 3600000) / 60000;
        final long seconds = ((timeInMillis % 3600000) % 60000) / 1000;

        if (hours < 1) {
            if (seconds < 10) {
                return minutes + ":0" + seconds;
            }
            return minutes + ":" + seconds;
        }
        if (minutes < 10) {
            if (seconds < 10) {
                return hours + ":0" + minutes + ":0" + seconds;
            }
            return hours + ":0" + minutes + ":" + seconds;
        }
        return hours + ":" + minutes + ":" + seconds;
    }

    @Override
    public String getName() {
        return "queue";
    }

    @Override
    public String getHelp() {
        return "Shows the songs in the current queue";
    }
}
