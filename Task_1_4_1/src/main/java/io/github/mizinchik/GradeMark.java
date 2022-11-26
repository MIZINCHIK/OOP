package io.github.mizinchik;

public record GradeMark(Integer mark) implements Grade {

    @Override
    public Boolean credit() {
        return mark > 2;
    }
}
