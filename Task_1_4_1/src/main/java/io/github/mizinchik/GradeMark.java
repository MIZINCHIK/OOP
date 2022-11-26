package io.github.mizinchik;

/**
 * Represents a grade which is represented by
 * an integer value.
 *
 * @author MIZINCHIK
 */
public class GradeMark implements Grade {
    private Boolean credit;
    private Integer mark;

    /**
     * Constructs a grade from an integer value of it.
     *
     * @param mark to put as a grade
     */
    public GradeMark(Integer mark) {
        if (mark == null) {
            this.mark = null;
            this.credit = null;
        } else {
            this.mark = mark;
            credit = mark > 2;
        }
    }

    /**
     * Get the grade in a boolean format.
     * Anything above 2 is considered a pass.
     * Null means the grade hasn't been put yet.
     *
     * @return 5 if passed, 2 if not
     */
    @Override
    public Boolean getCredit() {
        return credit;
    }

    /**
     * Get the grade in its genuine format.
     * Null means the grade hasn't been put yet.
     *
     * @return true if passed
     */
    @Override
    public Integer getMark() {
        return mark;
    }

    /**
     * Set a new grade value.
     *
     * @param mark to set
     */
    @Override
    public void updateGrade(Integer mark) {
        this.mark = mark;
        if (mark == null) {
            this.credit = null;
        } else {
            this.credit = mark > 2;
        }
    }
}
