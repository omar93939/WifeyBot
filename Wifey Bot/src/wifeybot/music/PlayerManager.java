package wifeybot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static wifeybot.utils.DelayedMessage.deleteAfter;
import static wifeybot.utils.DelayedMessage.sendMessageDelayedDelete;

public class PlayerManager {
    private static PlayerManager INSTANCE;

    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager() {
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    public void loadAndPlay(TextChannel channel, String trackUrl) {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);

                EmbedBuilder builder = new EmbedBuilder();
                builder.addField("**<:logo:842453310585045043> Added to queue:**","**" + track.getInfo().title + "**", false);
                builder.setColor(Color.decode("#ffa2fc"));

                sendMessageDelayedDelete(channel, builder.build(), 15);
                builder.clear();
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                final List<AudioTrack> tracks = playlist.getTracks();

                if (playlist.isSearchResult()) {
                    trackLoaded(tracks.get(0));
                } else {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.addField("**<:logo:842453310585045043> Added to queue:**","**" + tracks.size() + " tracks from playlist " + playlist.getName() + "**", false);
                    builder.setColor(Color.decode("#ffa2fc"));

                    sendMessageDelayedDelete(channel, builder.build(), 15);
                    builder.clear();

                    for (final AudioTrack track : tracks) {
                        musicManager.scheduler.queue(track);
                    }
                }
            }

            @Override
            public void noMatches() {
                sendMessageDelayedDelete(channel, "No Matches - (Try Again?)", 15);
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                sendMessageDelayedDelete(channel, "Load Failed", 15);
            }
        });
    }

    public static PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }

        return INSTANCE;
    }


}
