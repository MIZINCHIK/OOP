package io.github.mizinchik;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
     * @param recordName     head of the note
     * @param recordContents contents of the note
     * @throws RuntimeException in case of troubles with io
     */
    static void addRecord(String recordName, String recordContents, List<Note> list) throws RuntimeException {
        var date = new Date();
        var newNote = new Note(format.format(date), recordName, recordContents);
        list.add(newNote);
    }

    /**
     * Deletes notes with the names given.
     *
     * @param names of notes to delete
     * @throws RuntimeException in case of troubles with the io
     */
    static void removeGiven(String[] names, List<Note> list) throws RuntimeException {
        list.removeIf(note -> Arrays.asList(names).contains(note.head));
    }

    /**
     * Prints all the notes from the date stated as the first member of the array
     * to the date as the second and containing all the other strings as substrings of
     * their headings.
     *
     * @param args substrings of heads of the notes to delete
     * @throws IllegalArgumentException if dates provided are incorrect
     * @throws RuntimeException         in case of troubles with the io
     */
    static void printGiven(String[] args, List<Note> list) throws IllegalArgumentException, RuntimeException {
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
    }

    /**
     * Prints all the notes in the book.
     */
    static void printAll(List<Note> list) {
        for (Note note : list) {
            System.out.println(note.date + " " + note.head + " " + note.body);
        }
    }
}
