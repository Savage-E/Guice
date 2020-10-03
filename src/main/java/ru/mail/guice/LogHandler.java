package ru.mail.guice;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LogHandler {

    private final @NotNull
    Output consoleOutput;

    private final @NotNull
    Output fileOutput;

    @Inject
    public LogHandler(@NotNull Output consoleOutput, @NotNull @FileOutputAnn Output fileOutput) {
        this.consoleOutput = consoleOutput;
        this.fileOutput = fileOutput;
    }


    public void waitForInput() {

        ArrayList<String> lines = new ArrayList<>();

        String param = "";
        Boolean flag = false;
        String startTag = "";
        String endTag = "";
        Integer n = 1;
        System.out.println("Сhoose how you want to output log records.Input the following tag\n1-Output " +
                "to Console(tag <b>)\n2-Output to File(tag <a>)\n3- Output to File and Console(tag <c>)");
        try (Scanner scanner = new Scanner(System.in)) {
            do {

                param = scanner.nextLine();

                switch (param) {
                    case "<a>":
                        startTag = "<a>";
                        endTag = "</a>";
                        break;
                    case "<b>":
                        startTag = "<b>";
                        endTag = "</b>";
                        break;
                    case "<c>":
                        startTag = "<c>";
                        endTag = "</c>";
                        break;
                    default:
                        System.out.println("You entered wrong symbols.Try again");
                        flag = true;
                }
            } while (flag);

            System.out.println("Waiting for new lines. Press Ctrl+D to exit.");
            while (scanner.hasNext()) {
                lines.add((n + ": " + startTag + scanner.nextLine().trim() + endTag));
                n++;
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println(e);
        }

        if (param.equals("<b>"))
            consoleOutput.output(lines);
        if (param.equals("<a>"))
            fileOutput.output(lines);
        if (param.equals("<c>")) {
            ArrayList<String> arrayList = new ArrayList<>();
            n = 2;

            for (String s : lines) {
                arrayList.add(s.replaceFirst("^[0-9]", n.toString()));
                n++;
            }
            consoleOutput.output(lines);
            fileOutput.output(arrayList);
        }
    }
}
