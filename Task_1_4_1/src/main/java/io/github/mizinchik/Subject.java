package io.github.mizinchik;

public interface Subject {
    String getName();

    String getLecturer();

    Grade getGrade();

    boolean isGraded();

    void putGrade(Grade grade);
}
