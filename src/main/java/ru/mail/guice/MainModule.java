package ru.mail.guice;

import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
    bind(Output.class).to(ConsoleOutputImpl.class);
    bind(Output.class).annotatedWith(FileOutputAnn.class).to( FileOutputImpl.class);
    }
}
