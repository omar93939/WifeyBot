package wifeybot.utils;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import wifeybot.WifeyBot;
import wifeybot.music.*;
//import wifeybot.music.Play;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {

    private final List<MyCommand> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new PlayCommand());
        addCommand(new StopCommand());
        addCommand(new SkipCommand());
        addCommand(new NowPlayingCommand());
        addCommand(new QueueCommand());
        //addCommand(new Play());
    }

    private void addCommand(MyCommand cmd) {
        commands.add(cmd);
    }

    public List<MyCommand> getCommands() {
        return commands;
    }

    @Nullable
    public MyCommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (MyCommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }
        return null;
    }

    void handle(GuildMessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(WifeyBot.prefix), "").split("\\s+");

        String invoke = split[0].toLowerCase();
        MyCommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }
}
