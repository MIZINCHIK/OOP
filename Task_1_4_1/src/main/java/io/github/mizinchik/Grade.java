package io.github.mizinchik;

/**
 * Represents a grade which may be a
 * pass-or-fail one or a real mark.
 *
 * @author MIZINCHIK
 */
public interface Grade {
    /**
     * Get the grade in a pass-or-fail
     * format.
     *
     * @return true if passed
     */
    Boolean getCredit();

    /**
     * Get the grade in a mark format.
     *
     * @return mark
     */
    Integer getMark();

    /**
     * Set a new grade value.
     *
     * @param mark to set
     */
    void updateGrade(Integer mark);
}
