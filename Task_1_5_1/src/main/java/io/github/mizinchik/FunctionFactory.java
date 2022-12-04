package io.github.mizinchik;

import java.util.Arrays;

public abstract class FunctionFactory {
    private static final String[] binaryFunctions =  {
            "+",
            "-",
            "*",
            "/",
            "log",
            "pow"
    };
    private static final String[] unaryFunctions = {
            "sqrt",
            "sin",
            "cos"
    };

    public static String[] getBinaryFunctions() {
        return binaryFunctions;
    }

    public static String[] getUnaryFunctions() {
        return unaryFunctions;
    }
}
