package io.github.mizinchik;

import static io.github.mizinchik.BookKeeper.addRecord;
import static io.github.mizinchik.BookKeeper.printAll;
import static io.github.mizinchik.BookKeeper.printGiven;
import static io.github.mizinchik.BookKeeper.removeGiven;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import io.github.mizinchik.BookKeeper.Note;
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
    static String fileName = "bookkeeper.json";

    /**
     * Gets contents of the JSON-book as List.
     *
     * @return list of notes in a book
     */
    public List<Note> getFromBook() {
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new BufferedReader(new FileReader(file))) {
                Type type = new TypeToken<List<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                if (list == null) {
                    list = new ArrayList<>();
                }
                return list;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Converts the list of notes into a JSON-file.
     *
     * @param list of notes
     */
    public void writeToBook(List<Note> list) {
        var file = new File(fileName);
        try (var writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a note to the book.
     *
     * @param add note to add
     */
    @Command(name = "add", description = "Add a new record")
    public void add(@Parameters(arity = "2") String[] add) {
        List<Note> list = getFromBook();
        addRecord(add[0], add[1], list);
        writeToBook(list);
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
            writeToBook(new ArrayList<>());
        } else {
            List<Note> list = getFromBook();
            removeGiven(names, list);
            writeToBook(list);
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
        List<Note> list = getFromBook();
        if (show == null) {
            printAll(list);
        } else if (show.length == 1) {
            throw new IllegalArgumentException("Not enough arguments");
        } else {
            printGiven(show, list);
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
