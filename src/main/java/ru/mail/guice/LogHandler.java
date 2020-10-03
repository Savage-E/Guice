package ru.mail.guice;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LogHandler {

    private final @NotNull
    ConsoleOutput consoleOutput;

    private final @NotNull FileOutput fileOutput;

    private @NotNull LogNumber logNumber;

    @Inject
    public LogHandler(@NotNull ConsoleOutput consoleOutput, @NotNull FileOutput fileOutput, @NotNull LogNumber logNumber) {
        this.consoleOutput = consoleOutput;
        this.fileOutput = fileOutput;
        this.logNumber = logNumber;
    }


    public void waitForInput() {

        ArrayList<String> lines = new ArrayList<>();
        System.out.println("Сhoose how you want to output log records.Input the following tag\n1-Output " +
                "to Console(tag <b>)\n2-Output to File(tag <a>)\n3- Output to File and Console(tag <c>)");
        String param = "";
        Boolean flag = false;
        String startTag="";
        String endTag="";
        try (Scanner scanner = new Scanner(System.in)) {
            do {
             param = scanner.nextLine().trim();

                switch (param) {
                    case "<a>":
                               startTag="<a>";
                               endTag="</a>";
                               break;
                    case "<b>":
                        startTag="<b>";
                        endTag="</b>";
                        break;
                    case "<c>":
                        startTag="<c>";
                        endTag="</c>";
                        break;
                    default:
                        System.out.println("You entered wrong symbols.Try again");
                        flag = true;
                }
            } while (flag);

            System.out.println("Waiting for new lines. Press Ctrl+D to exit.");
            while (scanner.hasNext()) {
                lines.add(logNumber.nextNumber() + ": "+startTag + scanner.nextLine().trim() + endTag);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println(e);
        }
        if (param.equals("<b>"))
            consoleOutput.print(lines);
        if (param.equals("<a>"))
         fileOutput.fileOutput(lines);
    }


}
