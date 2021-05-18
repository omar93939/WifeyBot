package wifeybot.utils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sendMessageDelayed(GuildMessageReceivedEvent event, CharSequence message, int delaySeconds) {
        RestAction<Message> action = event.getChannel().sendMessage(message);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));

        deleteAfter(event.getMessage(), delaySeconds);
    }
    public static void sendMessageDelayed(GuildMessageReceivedEvent event, MessageEmbed message, int delaySeconds) {
        RestAction<Message> action = event.getChannel().sendMessage(message);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));

        deleteAfter(event.getMessage(), delaySeconds);
    }
    public static void sendMessageDelayed(GuildMessageReceivedEvent event, Message message, int delaySeconds) {
        RestAction<Message> action = event.getChannel().sendMessage(message);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));

        deleteAfter(event.getMessage(), delaySeconds);
    }

    public static void deleteAfter(Message message, int delay) {
        message.delete().queueAfter(delay, TimeUnit.SECONDS);
    }



}
