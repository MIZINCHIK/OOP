package io.github.mizinchik;

/**
 * Represents a higher education institution's subject.
 *
 * @author MIZINCHIK
 */
public interface Subject {
    /**
     * Get the number of the term the subject's classes
     * are conducted in.
     *
     * @return the order number of the term
     */
    int getTermNumber();

    /**
     * Get the name of the subject.
     *
     * @return name of the subject
     */
    String getName();

    /**
     * Get the name of the lecturer.
     *
     * @return name of the lecturer
     */
    String getLecturer();

    /**
     * Get the grade your work on the subject was evaluated with.
     *
     * @return grade evaluating the subject completion
     */
    Grade getGrade();

    /**
     * Check if the subject has been graded already.
     *
     * @return true if the subject is already graded
     */
    boolean isGraded();

    /**
     * Change or set the grade for the subject.
     *
     * @param grade to put
     */
    void putGrade(Integer grade);
}
