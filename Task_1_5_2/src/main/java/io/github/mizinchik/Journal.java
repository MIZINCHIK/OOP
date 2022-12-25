package io.github.mizinchik;

import static io.github.mizinchik.BookKeeper.addRecord;
import static io.github.mizinchik.BookKeeper.removeGiven;
import static io.github.mizinchik.BookKeeper.print;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "Journal", mixinStandardHelpOptions = true)
public class Journal {
    @Command(name = "add", description = "Add a new record")
    void add(@Option(names = "-add", arity = "2") String[] add) {
        addRecord(add[0], add[1]);
    }

    @Command(name = "remove", description = "Remove given records")
    void remove(@Option(names = "-rm", arity = "1..") String[] names) {
        removeGiven(names);
    }

    @Command(name = "show", description = "Show records")
    void show(@Option(names = "-show", arity = "0..") String[] show) {
        print(show);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Journal()).execute(args);
        System.exit(exitCode);
    }
}
