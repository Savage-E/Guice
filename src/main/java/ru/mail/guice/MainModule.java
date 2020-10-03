package ru.mail.guice;

import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {



    @Override
    protected void configure() {
    bind(ConsoleOutput.class).toInstance(new ConsoleOutputImpl());
    bind(FileOutput.class).toInstance(new FileOutputImpl());

    }
}
