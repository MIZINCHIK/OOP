package io.github.mizinchik;

public class CompositeArrayConsecutive implements CompositeArrayInterface {
    private final int[] array;

    public CompositeArrayConsecutive(int[] array) {
        this.array = array;
    }

    @Override
    public int[] getArray() {
        return array;
    }

    @Override
    public boolean containsComposite() {
        for (int number : array) {
            if (!isPrime(number)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        } else {
            for (int divisor = 2; divisor <= Math.sqrt(number); divisor++) {
                if (number % divisor == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}