package io.github.mizinchik;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Class running benchmarks from the
 * BenchConcurrent class.
 *
 * @author MIZINCHIK
 */
public class BenchRunner {
    /**
     * Runs benchmarks.
     *
     * @param args command line arguments (not used)
     * @throws RunnerException something wrong with JMH
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchConcurrent.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
