package io.github.mizinchik;

import static io.github.mizinchik.RandomBits.nthBitRandom;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark class for testing CompositeArrayConsecutive,
 * CompositeArrayThread and CompositeArrayStream classes.
 *
 * @author MIZINCHIK
 */
public class BenchConcurrent {
    /**
     * State class, stores
     * and array of a 1_000
     * random prime 31bit ints
     * for each benchmark.
     */
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public volatile int[] arrayPrimes = arrayRandomPrime();
    }

    /**
     * Generator of an array of
     * a 1_000 random int 31bit primes.
     *
     * @return array of a 1_000 random int 31bit primes
     */
    private static int[] arrayRandomPrime() {
        int size = 1_000;
        int[] arrayPrimes = new int[size];
        for (int i = 0; i < size; i++) {
            int bits = 31;
            int randPrime = nthBitRandom(bits);
            arrayPrimes[i] = randPrime;
        }
        return arrayPrimes;
    }

    /**
     * Benchmark for a consecutive approach.
     *
     * @param state array of prime numbers
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchConsecutive(BenchmarkState state) {
        var consecutiveChecker = new CompositeArrayConsecutive(state.arrayPrimes);
        consecutiveChecker.containsComposite();
    }

    /**
     * Benchmark for an approach with 1 thread (equal to previous).
     *
     * @param state array of prime numbers
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread1(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes, 1);
        threadChecker.containsComposite(1);
    }

    /**
     * Benchmark for an approach with 2 parallel threads.
     *
     * @param state array of prime numbers
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread2(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes, 1);
        threadChecker.containsComposite(2);
    }

    /**
     * Benchmark for an approach with 3 parallel threads.
     *
     * @param state array of prime numbers
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread3(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes, 1);
        threadChecker.containsComposite(3);
    }

    /**
     * Benchmark for an approach with 4 parallel threads.
     *
     * @param state array of prime numbers
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread4(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes, 1);
        threadChecker.containsComposite(4);
    }

    /**
     * Benchmark for an approach with 8 parallel threads.
     *
     * @param state array of prime numbers
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread8(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes, 1);
        threadChecker.containsComposite(8);
    }

    /**
     * Benchmark for an approach with 16 parallel threads.
     *
     * @param state array of prime numbers
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread16(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes, 1);
        threadChecker.containsComposite(16);
    }

    /**
     * Benchmark for an approach with parallelStream.
     *
     * @param state array of prime numbers
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchStream(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayStream(state.arrayPrimes);
        threadChecker.containsComposite();
    }
}