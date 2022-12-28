package io.github.mizinchik;

import static io.github.mizinchik.BookKeeper.*;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * Handles arguments of the command line for the note-keeper.
 *
 * @author MIZINCHIK
 */
@Command(name = "Journal", mixinStandardHelpOptions = true)
public class Journal {
    /**
     * Adds a note to the book.
     *
     * @param add note to add
     */
    @Command(name = "add", description = "Add a new record")
    public void add(@Parameters(arity = "2") String[] add) {
        addRecord(add[0], add[1]);
    }

    /**
     * Removes notes from the book.
     * If no names provided wipes the book entirely.
     *
     * @param names of the notes to remove
     */
    @Command(name = "remove", description = "Remove given records")
    public void remove(@Parameters(arity = "0..*") String[] names) {
        if (names == null) {
            removeAll();
        } else {
            removeGiven(names);
        }
    }

    /**
     * Shows either all the contents of the book
     * when no arguments were provided or just the ones
     * that were added between the date in the firs member of array
     * and the date in the second and containing all the other strings as substrings
     * in their headings.
     *
     * @param show dates and substrings
     */
    @Command(name = "show", description = "Show records")
    public void show(@Parameters(arity = "0..*") String[] show) {
        if (show == null) {
            printAll();
        } else if (show.length == 1) {
            throw new IllegalArgumentException("Not enough arguments");
        } else {
            printGiven(show);
        }
    }

    /**
     * Runs the book from a command line.
     *
     * @param args CLI
     */
    public static void main(String[] args) {
        new CommandLine(new Journal()).execute(args);
    }
}
