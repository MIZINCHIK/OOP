package io.github.mizinchik;
import picocli.CommandLine.Option;

class Journal {
    @Option(names = "-add", arity = "2", description = "add a new record")
    String[] add;

    @Option(names = "-rm", arity = "1..", description = "remove given records")
    String[] names;

    @Option(names = "-show", arity = "1..", description = "one or more files to archive")
    String[] show;
}
