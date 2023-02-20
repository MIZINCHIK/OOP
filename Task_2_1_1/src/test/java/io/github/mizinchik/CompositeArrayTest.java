package io.github.mizinchik;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static io.github.mizinchik.CompositeArrayConsecutive.isPrime;
import static org.junit.jupiter.api.Assertions.*;

public class CompositeArrayTest {
    private int bits = 31;

    private static int nBitRandom(int n) {
        int max = (int)Math.pow(2, n) - 1;
        int min = (int)Math.pow(2, n - 1) + 1;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    @Test
    @DisplayName("Single prime checker test")
    void singlePrimeTest() {
        assertEquals(true, isPrime(1_000_000_007));
        assertEquals(false, isPrime(44_041 * 46_051));
        assertEquals(true, isPrime(1000004099));
        assertEquals(true, isPrime(2000004023));
        assertEquals(true, isPrime(2000012563));
        assertEquals(true, isPrime(2000012233));
        assertEquals(true, isPrime(2000014889));
        assertEquals(true, isPrime(2000015819));
        assertEquals(true, isPrime(2000016517));
        assertEquals(false, isPrime(1));
        assertEquals(false, isPrime(0));
        assertEquals(true, isPrime(2));
    }

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

    @Test
    @DisplayName("Initial test")
    void testInitial() throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            int[] array = new int[100_000];
            for (int i = 0; i < 1000; i++) {
                int randPrime = nBitRandom(bits);
                while (!isPrime(randPrime)) {
                    randPrime = nBitRandom(bits);
                }
                array[i] = randPrime;
            }
            var consecutiveChecker = new CompositeArrayConsecutive(array);
            var threadChecker = new CompositeArrayThread(array);
            var streamChecker = new CompositeArrayStream(array);
            boolean consecutiveRes = consecutiveChecker.containsComposite();
            boolean threadRes = threadChecker.containsComposite(4);
            boolean streamRes = streamChecker.containsCompositeStream();
            assertEquals(consecutiveRes, threadRes);
            assertEquals(consecutiveRes, streamRes);
        }
    }
}