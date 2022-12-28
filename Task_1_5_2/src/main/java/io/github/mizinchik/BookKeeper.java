package io.github.mizinchik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public interface BookKeeper {
    static String fileName = "bookkeeper.json";
    static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    class Note {
        String date;
        String head;
        String body;

        public Note(String date, String head, String body) {
            this.date = date;
            this.head = head;
            this.body = body;
        }
    }

    static void addRecord(String recordName, String recordContents) throws RuntimeException {
        var date = new Date();
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new FileReader(file);
            var writer = new FileWriter(file)) {
                Type type = new TypeToken<ArrayList<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                if (list == null) {
                    list = new ArrayList<>();
                }
                var newNote = new Note(format.format(date), recordName, recordContents);
                list.add(newNote);
                gson.toJson(list, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (var writer = new FileWriter(file)) {
                Gson gson = new GsonBuilder().create();
                gson.toJson(new Note(format.format(date), recordName, recordContents), writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void removeGiven(String[] names) throws RuntimeException {
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new FileReader(file);
            var writer = new FileWriter(file)) {
                Type type = new TypeToken<ArrayList<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                list.removeIf(note -> Arrays.asList(names).contains(note.head));
                gson.toJson(list, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

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

    static void printGiven(String[] args) throws IllegalArgumentException {
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new FileReader(file)) {
                Type type = new TypeToken<ArrayList<Note>>(){}.getType();
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
                        if (noteDate.after(dateFrom) && noteDate.before(dateTo)) {
                            for (int i = 2; i < args.length; i++) {
                                if (note.head.contains(args[i])) {
                                    System.out.println(note.date + note.head + note.body);
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

    static void printAll() {
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new FileReader(file)) {
                Type type = new TypeToken<ArrayList<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
                for (Note note : list) {
                    System.out.println(note.date + note.head + note.body);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
