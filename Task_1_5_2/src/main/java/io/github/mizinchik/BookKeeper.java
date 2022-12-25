package io.github.mizinchik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public interface BookKeeper {
    static String fileName = "bookkeeper.json";

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

    static void addRecord(String recordName, String recordContents) {
        var format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        var date = new Date();
        var file = new File(fileName);
        if (file.exists()) {
            try (var reader = new FileReader(file)) {
                Type type = new TypeToken<ArrayList<Note>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Note> list = gson.fromJson(reader, type);
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

    static void removeGiven(String[] names) {
        for (String name : names) {

        }
    }

    static void print(String[] args) {

    }

    static void printAll() {

    }
}
