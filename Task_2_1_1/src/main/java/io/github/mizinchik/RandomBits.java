package io.github.mizinchik;

import java.util.concurrent.ThreadLocalRandom;

import static io.github.mizinchik.CompositeArrayConsecutive.isPrime;

public class RandomBits {
    public static int arrayRandom = nthBitRandom(31);

    /**
     * Returns a candidate for a primality,
     * a random number of n bits.
     * Required to build a large array of prime numbers.
     *
     * @param n number of bits in a random number
     * @return random number
     */
    private static int nthBit(int n) {
        int max = (int) Math.pow(2, n) - 1;
        int min = (int) Math.pow(2, n - 1) + 1;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Generates an int number of n bits.
     *
     * @param n bits in a random number
     * @return random number of n bits
     */
    public static int nthBitRandom(int n) {
        int result = nthBit(n);
        while (!isPrime(result)) {
            result = nthBit(n);
        }
        return result;
    }
}
