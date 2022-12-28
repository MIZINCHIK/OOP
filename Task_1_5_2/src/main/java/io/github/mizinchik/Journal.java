package io.github.mizinchik;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static io.github.mizinchik.BookKeeper.*;

@Command(name = "Journal", mixinStandardHelpOptions = true)
public class Journal {
    @Command(name = "add", description = "Add a new record")
    void add(@Parameters(arity = "2") String[] add) {
        addRecord(add[0], add[1]);
    }

    @Command(name = "remove", description = "Remove given records")
    void remove(@Parameters(arity = "0..*") String[] names) {
        if (names == null) {
            removeAll();
        } else {
            removeGiven(names);
        }
    }

    @Command(name = "show", description = "Show records")
    void show(@Parameters(arity = "0..*") String[] show) {
        if (show == null) {
            printAll();
        } else if (show.length == 1) {
            throw new IllegalArgumentException("Not enough arguments");
        } else {
            printGiven(show);
        }
    }

    public static void main(String[] args) {
        new CommandLine(new Journal()).execute(args);
    }
}
