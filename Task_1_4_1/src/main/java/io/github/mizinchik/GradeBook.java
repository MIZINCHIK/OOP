package io.github.mizinchik;

import java.util.List;

/**
 * Represents a grade book for
 * a higher education institution.
 *
 * @author MIZINCHIK
 */
public interface GradeBook {
    /**
     * Get a grade point average for all
     * the subjects in the previous terms.
     *
     * @return GPA
     */
    Double getGPA();

    /**
     * Get a grade of the thesis.
     *
     * @return diploma grade
     */
    Integer getDiplomaGrade();

    /**
     * Set the thesis' grade.
     *
     * @param grade of the diploma
     */
    void setDiplomaGrade(Integer grade);

    /**
     * Get the grade of a subject.
     *
     * @param subject which you seek for
     * @param term in which the subject was evaluated
     * @return the grade
     */
    Grade getSubjectGrade(String subject, int term);

    /**
     * Set the grade of a subject.
     *
     * @param subject which you seek for
     * @param term in which the subject was evaluated
     * @param grade of the evaluation
     */
    void setSubjectGrade(String subject, int term, Integer grade);

    /**
     * Get the name of a lecturer on the subject.
     *
     * @param subject which you seek for
     * @param term in which the subject was evaluated
     * @return the lecturer's name
     */
    String getLecturer(String subject, int term);

    /**
     * Get the amount of terms to complete to get
     * a diploma.
     *
     * @return the amount of terms
     */
    int getTermsAmount();

    /**
     * Get the number of the current term.
     *
     * @return the order number
     */
    int getTermNumber();

    /**
     * Get the current term.
     *
     * @return the current term
     */
    Term getCurrentTerm();

    /**
     * Changes the current term to the next one.
     */
    void goNextTerm();

    /**
     * Get the list of all the terms required to
     * get a diploma.
     *
     * @return list of terms
     */
    List<Term> getTerms();

    /**
     * Get all the subjects which classes where conducted
     * in the previous terms. And thus they are already
     * necessarily evaluated.
     *
     * @return marked subjects
     */
    List<Subject> getMarkedSubjects();

    /**
     * Check if it's still possible to graduate with honors.
     *
     * @return true if honored graduation is possible
     */
    boolean possibleHonoredGraduation();

    /**
     * Check if you were granted with an extra stipend.
     *
     * @return true if you are receiving an extra stipend
     */
    boolean receiveExtraStipend();
}
