package io.github.mizinchik;

public interface BookKeeper {
    void addRecord(String recordName, String recordContents);

    void removeAll();

    void printAll();

    void printAllContaining(String start, String finish, String[] contents);
}
