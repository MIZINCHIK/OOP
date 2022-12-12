package io.github.mizinchik;

/**
 * Represents an NSU's subject.
 *
 * @author MIZINCHIK
 */
public class SubjectImpl implements Subject {
    int termNumber;
    String name;
    String lecturer;
    Grade grade;

    /**
     * Constructs an NSU subject.
     *
     * @param termNumber in which the subject takes place
     * @param name of the subject
     * @param lecturer on the subject
     * @param grade which your work on the subject was evaluated with
     * @throws IllegalArgumentException if grade is null
     */
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

    /**
     * Get the number of the semester the subject's classes
     * are conducted in.
     *
     * @return the order number of the term
     */
    @Override
    public int getTermNumber() {
        return termNumber;
    }

    /**
     * Get the name of the subject.
     *
     * @return name of the subject
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the name of the lecturer.
     *
     * @return name of the lecturer
     */
    @Override
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Get the grade your work on the subject was evaluated with.
     *
     * @return grade evaluating the subject completion
     */
    @Override
    public Grade getGrade() {
        return grade;
    }

    /**
     * Check if the subject has been graded already.
     *
     * @return true if the subject is already graded
     */
    @Override
    public boolean isGraded() {
        return grade.getCredit() != null;
    }

    /**
     * Change or set the grade for the subject.
     *
     * @param grade to put
     */
    @Override
    public void putGrade(Integer grade) {
        this.grade.updateGrade(grade);
    }
}
