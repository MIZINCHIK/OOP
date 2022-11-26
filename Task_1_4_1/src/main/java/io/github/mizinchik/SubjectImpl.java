package io.github.mizinchik;

public class SubjectImpl implements Subject {
    String name;
    String lecturer;
    Grade grade;

    public SubjectImpl(String name, String lecturer, Grade grade) {
        this.name = name;
        this.lecturer = lecturer;
        this.grade = grade;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLecturer() {
        return lecturer;
    }

    @Override
    public Grade getGrade() {
        return grade;
    }

    @Override
    public boolean isGraded() {
        return grade != null;
    }

    @Override
    public void putGrade(Grade grade) {
        this.grade = grade;
    }
}
