package io.github.mizinchik;

public class SubjectImpl implements Subject {
    int termNumber;
    String name;
    String lecturer;
    Grade grade;

    public SubjectImpl(int termNumber, String name, String lecturer,
                       Grade grade) throws IllegalArgumentException {
        if (grade == null) {
            throw new IllegalArgumentException("Grade can't be null");
        }
        this.termNumber = termNumber;
        this.name = name;
        this.lecturer = lecturer;
        this.grade = grade;
    }

    @Override
    public int getTermNumber() {
        return termNumber;
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
        return grade.getCredit() != null;
    }

    @Override
    public void putGrade(Integer grade) {
        this.grade.updateGrade(grade);
    }
}
