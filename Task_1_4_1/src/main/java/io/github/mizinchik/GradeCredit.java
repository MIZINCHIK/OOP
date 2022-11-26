package io.github.mizinchik;

public record GradeCredit(Boolean credit) implements Grade {

    @Override
    public Integer mark() {
        if (credit) {
            return 5;
        } else {
            return 2;
        }
    }
}
