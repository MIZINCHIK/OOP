package io.github.mizinchik;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        Journal journal = new Journal();
        new CommandLine(journal).parseArgs(args);

    }
}