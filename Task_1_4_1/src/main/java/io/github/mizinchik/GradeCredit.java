package io.github.mizinchik;

public class GradeCredit implements Grade {
    private final Boolean credit;
    private final Integer mark;

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
}
