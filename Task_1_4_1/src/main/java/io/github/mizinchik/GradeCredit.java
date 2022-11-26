package io.github.mizinchik;

public class GradeCredit implements Grade {
    private final Boolean credit;

    public GradeCredit(Boolean credit) {
        this.credit = credit;
    }

    @Override
    public Boolean getCredit() {
        return credit;
    }

    @Override
    public Integer getMark() {
        if (credit) {
            return 5;
        } else {
            return 2;
        }
    }
}
