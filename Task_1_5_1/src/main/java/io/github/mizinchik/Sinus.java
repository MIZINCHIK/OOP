package io.github.mizinchik;

import java.util.function.UnaryOperator;

public class Sinus implements UnaryOperator<Double> {
    @Override
    public Double apply(Double aDouble) {
        return Math.sin(aDouble);
    }
}
