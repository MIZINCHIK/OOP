package io.github.mizinchik;

public class GradeMark implements Grade {
    private Integer mark;

    public GradeMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public Boolean getCredit() {
        if (mark > 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer getMark() {
        return mark;
    }
}
