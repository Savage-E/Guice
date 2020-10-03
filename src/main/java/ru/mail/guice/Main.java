package ru.mail.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(@NotNull String[] args) {
        final Injector injector = Guice.createInjector(new MainModule());
         injector.getInstance(LogHandler.class).waitForInput();
    }
}
