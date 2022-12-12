package io.github.mizinchik;

/**
 * Represents a grade which is represented by
 * a boolean value. True means the form of evaluation was passed.
 *
 * @author MIZINCHIK
 */
public class GradeCredit implements Grade {
    private Boolean credit;
    private Integer mark;

    /**
     * Constructs a grade from a boolean value of it.
     *
     * @param credit to put as a grade
     */
    public GradeCredit(Boolean credit) {
        if (credit == null) {
            this.credit = null;
            this.mark = null;
        } else {
            this.credit = credit;
            if (credit) {
                this.mark = 5;
            } else {
                this.mark = 2;
            }
        }
    }

    /**
     * Get the grade in its genuine format.
     * Null means the grade hasn't been put yet.
     *
     * @return true if passed
     */
    @Override
    public Boolean getCredit() {
        return credit;
    }

    /**
     * Get the grade in a numerical format.
     * Null means the grade hasn't been put yet.
     *
     * @return 5 if passed, 2 if not
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
        if (mark == null) {
            this.mark = null;
            this.credit = null;
        } else if (mark > 2) {
            this.mark = 5;
            this.credit = true;
        } else {
            this.mark = 2;
            this.credit = false;
        }
    }
}
