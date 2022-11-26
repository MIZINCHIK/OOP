package io.github.mizinchik;

public class GradeMark implements Grade {
    private final Boolean credit;
    private final Integer mark;

    public GradeMark(Integer mark) {
        if (mark == null) {
            this.mark = null;
            this.credit = null;
        } else {
            this.mark = mark;
            credit = mark > 2;
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
