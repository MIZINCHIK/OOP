package io.github.mizinchik;

public class SubjectImpl implements Subject {
    String name;
    Grade grade;

    public SubjectImpl(String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String getName() {
        return name;
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
