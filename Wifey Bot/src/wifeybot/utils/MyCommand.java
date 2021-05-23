package wifeybot.utils;

import java.util.Collections;
import java.util.List;

public interface MyCommand {

    void handle(CommandContext ctx);

    String getName();

    String getHelp();

    default List<String> getAliases() {
        return Collections.emptyList();
    }

}
