package io.github.mizinchik;

import static io.github.mizinchik.RandomBits.nthBitRandom;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class BenchConcurrent {
    @State(Scope.Benchmark)
    public static class BenchmarkState{
        public volatile int[] arrayPrimes = arrayRandomPrime();
    }

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

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchConsecutive(BenchmarkState state) throws InterruptedException {
        var consecutiveChecker = new CompositeArrayConsecutive(state.arrayPrimes);
        consecutiveChecker.containsComposite();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread1(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes);
        threadChecker.containsComposite(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread2(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes);
        threadChecker.containsComposite(2);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread4(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes);
        threadChecker.containsComposite(4);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread8(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes);
        threadChecker.containsComposite(8);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchThread16(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayThread(state.arrayPrimes);
        threadChecker.containsComposite(16);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchStream(BenchmarkState state) throws InterruptedException {
        var threadChecker = new CompositeArrayStream(state.arrayPrimes);
        threadChecker.containsCompositeStream();
    }
}