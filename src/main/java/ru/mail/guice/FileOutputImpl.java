package ru.mail.guice;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileOutputImpl implements Output {

    @Inject
    private @NotNull Logger logger;

    @Override
    public void output(ArrayList<String> lines) {
        try {
            FileHandler fh = new FileHandler("src/main/resources/LogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);

        } catch (SecurityException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : lines) {
            logger.info(s);

        }

    }
}
