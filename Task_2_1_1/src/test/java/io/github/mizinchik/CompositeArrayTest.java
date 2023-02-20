package io.github.mizinchik;

import static io.github.mizinchik.CompositeArrayConsecutive.isPrime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * A test class for CompositeArrayConsecutive,
 * CompositeArrayThread and CompositeArrayStream classes.
 *
 * @author MIZINCHIK
 */
public class CompositeArrayTest {

    /**
     * Returns a candidate for a primality,
     * a random number of n bits.
     * Required to build a large array of prime numbers.
     *
     * @param n number of bits in a random number
     * @return random number
     */
    private static int nBit(int n) {
        int max = (int)Math.pow(2, n) - 1;
        int min = (int)Math.pow(2, n - 1) + 1;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static int nBitRandom(int n) {
        int result = nBit(n);
        while (!isPrime(result)) {
            result = nBit(n);
        }
        return result;
    }

    /**
     * Tests the isPrime method.
     */
    @Test
    @DisplayName("Single prime checker test")
    void singlePrimeTest() {
        assertTrue(isPrime(1_000_000_007));
        assertFalse(isPrime(44_041 * 46_051));
        assertTrue(isPrime(1000004099));
        assertTrue(isPrime(2000004023));
        assertTrue(isPrime(2000012563));
        assertTrue(isPrime(2000012233));
        assertTrue(isPrime(2000014889));
        assertTrue(isPrime(2000015819));
        assertTrue(isPrime(2000016517));
        assertFalse(isPrime(1));
        assertFalse(isPrime(0));
        assertTrue(isPrime(2));
    }

    /**
     * Tests the reference example.
     *
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Test
    @DisplayName("Reference test")
    void testReference() throws InterruptedException {
        int[] arraySmall = new int[] {6,8,7,13,9,4};
        int[] arrayLarge = new int[] {6997901, 6997927, 6997937
                ,6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        var consecutiveSmall = new CompositeArrayConsecutive(arraySmall);
        var consecutiveLarge = new CompositeArrayConsecutive(arrayLarge);
        assertTrue(consecutiveSmall.containsComposite());
        assertFalse(consecutiveLarge.containsComposite());
        var threadSmall = new CompositeArrayThread(arraySmall);
        var threadLarge = new CompositeArrayThread(arrayLarge);
        assertTrue(threadSmall.containsComposite(4));
        assertFalse(threadLarge.containsComposite(4));
        var streamSmall = new CompositeArrayStream(arraySmall);
        var streamLarge = new CompositeArrayStream(arrayLarge);
        assertTrue(streamSmall.containsCompositeStream());
        assertFalse(streamLarge.containsCompositeStream());
    }

    /**
     * Tests large arrays of large primes and large composite numbers.
     *
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Test
    @DisplayName("Initial test")
    void testInitial() throws InterruptedException {
        int size = 100_000;
        int[] arrayPrimes = new int[size];
        int[] arrayNotPrimes = new int[size];
        for (int i = 0; i < size; i++) {
            int bits = 31;
            int randPrime = nBitRandom(bits);
            bits = 15;
            int randPrime1 = nBitRandom(bits);
            int randPrime2 = nBitRandom(bits);
            arrayPrimes[i] = randPrime;
            arrayNotPrimes[i] = randPrime2 * randPrime1;
        }
        var consecutiveChecker1 = new CompositeArrayConsecutive(arrayPrimes);
        var threadChecker1 = new CompositeArrayThread(arrayPrimes);
        var streamChecker1 = new CompositeArrayStream(arrayPrimes);
        boolean consecutiveRes1 = consecutiveChecker1.containsComposite();
        boolean threadRes1 = threadChecker1.containsComposite(4);
        boolean streamRes1 = streamChecker1.containsCompositeStream();
        assertEquals(consecutiveRes1, threadRes1);
        assertEquals(consecutiveRes1, streamRes1);
        assertFalse(consecutiveRes1);
        var consecutiveChecker2 = new CompositeArrayConsecutive(arrayNotPrimes);
        var threadChecker2 = new CompositeArrayThread(arrayNotPrimes);
        var streamChecker2 = new CompositeArrayStream(arrayNotPrimes);
        boolean consecutiveRes2 = consecutiveChecker2.containsComposite();
        boolean threadRes2 = threadChecker2.containsComposite(4);
        boolean streamRes2 = streamChecker2.containsCompositeStream();
        assertEquals(consecutiveRes2, threadRes2);
        assertEquals(consecutiveRes2, streamRes2);
        assertTrue(consecutiveRes2);
    }
}