package ru.mail.guice;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ConsoleOutputImpl implements Output {

    @Inject
    private @NotNull Logger logger;

    @Override
    public void output(@NotNull ArrayList<String> lines) {

        for (String s : lines) {
            logger.info(s);
        }
    }
}
