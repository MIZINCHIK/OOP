package io.github.mizinchik;

import java.util.Arrays;

/**
 * Abstract class for the method factories for functions in
 * a simple command-line calculator.
 * Contains all the names of the binary and unary functions.
 *
 * @author MIZINCHIK
 */
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
