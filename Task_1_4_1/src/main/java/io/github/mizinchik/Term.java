package io.github.mizinchik;

import java.util.List;

/**
 * Represents a higher education institution's term
 * (i.e. a semester).
 *
 * @author MIZINCHIK
 */
public interface Term {
    /**
     * Get a grade point average for all
     * the subjects in the term.
     *
     * @return GPA
     */
    Double getGPA();

    /**
     * Get the list of all the subjects taking place on the term.
     *
     * @return list of subjects
     */
    List<Subject> getSubjects();

    /**
     * Get the name of a lecturer on a particular subject.
     *
     * @param subject which you seek for
     * @return name of the lecturer
     */
    String getLecturer(String subject);

    /**
     * Set or change the grade for a certain subject.
     *
     * @param subject to set the grade on
     * @param grade to set
     */
    void updateGrade(String subject, Integer grade);

    /**
     * Get the grade for the specific subject.
     *
     * @param subject to get the grade on
     * @return grade
     */
    Grade getGrade(String subject);
}
