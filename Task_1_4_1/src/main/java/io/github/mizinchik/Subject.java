package io.github.mizinchik;

public interface Subject {
    String getName();

    Grade getGrade();

    boolean isGraded();

    void putGrade(Grade grade);
}
