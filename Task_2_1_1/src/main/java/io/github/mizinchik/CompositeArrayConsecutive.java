package io.github.mizinchik;

/**
 * Contains an array of ints.
 * Can perform consecutive checks whether that array contains
 * a composite number or not.
 *
 * @author MIZINCHIK
 */
public class CompositeArrayConsecutive implements CompositeArrayInterface {
    private final int[] array;

    /**
     * Constructs an instance from a reference
     * to an array of ints.
     *
     * @param array of ints
     */
    public CompositeArrayConsecutive(int[] array) {
        this.array = array.clone();
    }

    /**
     * Checks whether an array contains a composite int.
     *
     * @return true if a composite number is present in an array
     */
    @Override
    public boolean containsComposite() {
        for (int number : array) {
            if (!isPrime(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether an int passed is prime.
     *
     * @param number to check for primality
     * @return true if prime
     */
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