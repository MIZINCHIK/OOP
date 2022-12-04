package io.github.mizinchik;

import java.util.function.UnaryOperator;

public class SquareRoot implements UnaryOperator<Double> {
    @Override
    public Double apply(Double aDouble) {
        return Math.sqrt(aDouble);
    }
}
