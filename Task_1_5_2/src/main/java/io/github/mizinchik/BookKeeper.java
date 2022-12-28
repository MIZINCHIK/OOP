package io.github.mizinchik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Works with a JSON-based note-keeper placed
 * in a file bookkeeper.json wherever it's created by default.
 *
 * @author MIZINCHIK
 */
public interface BookKeeper {
    String fileName = "bookkeeper.json";
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    /**
     * Used for storing records in a book.
     */
    class Note {
        String date;
        String head;
        String body;

        /**
         * Class constructor.
         *
         * @param date of adding the note
         * @param head of the note
         * @param body of the note (its contents)
         */
        public Note(String date, String head, String body) {
            this.date = date;
            this.head = head;
            this.body = body;
        }
    }

    /**
     * Adds record to the book.
     *
     * @param recordName head of the note
     * @param recordContents contents of the note
     * @throws RuntimeException in case of troubles with io
     */
    static void addRecord(String recordName, String recordContents) throws RuntimeException {
        var date = new Date();
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new BufferedReader(new FileReader(file))) {
                Type type = new TypeToken<List<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                if (list == null) {
                    list = new ArrayList<>();
                }
                var newNote = new Note(format.format(date), recordName, recordContents);
                list.add(newNote);
                try (var writer = new FileWriter(file)) {
                    gson.toJson(list, writer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (var writer = new FileWriter(file)) {
                Gson gson = new GsonBuilder().create();
                var newNote = new Note(format.format(date), recordName, recordContents);
                var newList = new ArrayList<Note>();
                newList.add(newNote);
                gson.toJson(newList, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Deletes notes with the names given.
     *
     * @param names of notes to delete
     * @throws RuntimeException in case of troubles with the io
     */
    static void removeGiven(String[] names) throws RuntimeException {
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new BufferedReader(new FileReader(file))) {
                Type type = new TypeToken<List<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                list.removeIf(note -> Arrays.asList(names).contains(note.head));
                try (var writer = new FileWriter(file)) {
                    gson.toJson(list, writer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Removes all the notes from the book.
     *
     * @throws RuntimeException in case of troubles with the io
     */
    static void removeAll() throws RuntimeException {
        var file = new File(fileName);
        if (file.exists()) {
            try (var writer = new FileWriter(file)) {
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = new ArrayList<>();
                gson.toJson(list, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Prints all the notes from the date stated as the first member of the array
     * to the date as the second and containing all the other strings as substrings of
     * their headings.
     *
     * @param args substrings of heads of the notes to delete
     * @throws IllegalArgumentException if dates provided are incorrect
     * @throws RuntimeException in case of troubles with the io
     */
    static void printGiven(String[] args) throws IllegalArgumentException, RuntimeException {
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new BufferedReader(new FileReader(file))) {
                Type type = new TypeToken<List<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                var position = new ParsePosition(0);
                Date dateFrom = format.parse(args[0], position);
                position.setIndex(0);
                Date dateTo = format.parse(args[1], position);
                if (dateFrom == null || dateTo == null) {
                    throw new IllegalArgumentException("Incorrect date");
                }
                if (!dateTo.before(dateFrom)) {
                    position.setIndex(0);
                    for (Note note : list) {
                        Date noteDate = format.parse(note.date, position);
                        if (!noteDate.before(dateFrom) && !noteDate.after(dateTo)) {
                            for (int i = 2; i < args.length; i++) {
                                if (note.head.contains(args[i])) {
                                    System.out.println(note.date + " "
                                            + note.head + " " + note.body);
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Prints all the notes in the book.
     */
    static void printAll() {
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new BufferedReader(new FileReader(file))) {
                Type type = new TypeToken<List<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                for (Note note : list) {
                    System.out.println(note.date + " " + note.head + " " + note.body);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
