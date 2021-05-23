package wifeybot.utils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.concurrent.TimeUnit;

public class DelayedMessage {

    public static void sendMessageDelayedDelete(GuildMessageReceivedEvent event, CharSequence charSequence, int delaySeconds) {
        RestAction<Message> action = event.getChannel().sendMessage(charSequence);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));

        deleteAfter(event.getMessage(), delaySeconds);
    }
    public static void sendMessageDelayedDelete(GuildMessageReceivedEvent event, MessageEmbed messageEmbed, int delaySeconds) {
        RestAction<Message> action = event.getChannel().sendMessage(messageEmbed);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));

        deleteAfter(event.getMessage(), delaySeconds);
    }
    public static void sendMessageDelayedDelete(GuildMessageReceivedEvent event, Message message, int delaySeconds) {
        RestAction<Message> action = event.getChannel().sendMessage(message);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));

        deleteAfter(event.getMessage(), delaySeconds);
    }

    public static void sendMessageDelayedDelete(TextChannel channel, CharSequence charSequence, int delaySeconds) {
        RestAction<Message> action = channel.sendMessage(charSequence);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));
    }
    public static void sendMessageDelayedDelete(TextChannel channel, MessageEmbed messageEmbed, int delaySeconds) {
        RestAction<Message> action = channel.sendMessage(messageEmbed);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));
    }
    public static void sendMessageDelayedDelete(TextChannel channel, Message message, int delaySeconds) {
        RestAction<Message> action = channel.sendMessage(message);
        action.queue((msg) -> msg.delete().queueAfter(delaySeconds, TimeUnit.SECONDS));
    }

    public static void deleteAfter(Message message, int delay) {
        message.delete().queueAfter(delay, TimeUnit.SECONDS);
    }



}
