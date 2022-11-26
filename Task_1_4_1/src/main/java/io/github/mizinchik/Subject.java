package io.github.mizinchik;

public interface Subject {
    int getTermNumber();

    String getName();

    String getLecturer();

    Grade getGrade();

    boolean isGraded();

    void putGrade(Grade grade);

    void putGrade(Integer grade);
}
