package io.github.mizinchik;

public class CompositeArrayConsecutive implements CompositeArrayInterface {
    @Override
    public boolean containsComposite(int[] array) {
        for (int number : array) {
            if (!isPrime(number)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPrime(int number) {
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