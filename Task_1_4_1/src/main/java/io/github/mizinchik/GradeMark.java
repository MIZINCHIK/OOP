package io.github.mizinchik;

public record GradeMark(Integer mark) implements Grade {

    @Override
    public Boolean credit() {
        if (mark > 2) {
            return true;
        } else {
            return false;
        }
    }
}
