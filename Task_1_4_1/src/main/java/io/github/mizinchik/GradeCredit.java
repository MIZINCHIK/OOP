package io.github.mizinchik;

public class GradeCredit implements Grade {
    private Boolean credit;
    private Integer mark;

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

    @Override
    public Boolean getCredit() {
        return credit;
    }

    @Override
    public Integer getMark() {
        return mark;
    }

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
