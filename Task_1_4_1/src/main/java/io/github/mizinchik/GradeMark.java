package io.github.mizinchik;

public class GradeMark implements Grade {
    private Boolean credit;
    private Integer mark;

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
