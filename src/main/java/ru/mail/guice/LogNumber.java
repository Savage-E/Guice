package ru.mail.guice;

import com.google.inject.Singleton;

@Singleton
public class LogNumber {
    private int number;

    public int getNumber() {
        return number;
    }

    public LogNumber() {
        number = 1;
    }

    public int nextNumber()
    {
        return number++;
    }
}
