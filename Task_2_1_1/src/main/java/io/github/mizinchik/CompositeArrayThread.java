package io.github.mizinchik;

import static io.github.mizinchik.CompositeArrayConsecutive.isPrime;

/**
 * Contains an array of ints.
 * Can perform both parallel (via Thread class) and
 * consecutive checks whether that array contains
 * a composite number or not.
 *
 * @author MIZINCHIK
 */
public class CompositeArrayThread implements CompositeArrayInterface {
    private final int[] array;
    private final int threads;

    /**
     * Constructs an instance from a reference
     * to an array of ints.
     *
     * @param array of ints
     * @param defaultThreads the default quantity of threads to summon
     */
    public CompositeArrayThread(int[] array, int defaultThreads) {
        this.array = array.clone();
        this.threads = defaultThreads;
    }

    /**
     * Checks whether an array contains a composite int via
     * threadsQuantity of parallel PrimeThreads running.
     *
     * @param threadsQuantity how many threads to run in parallel
     * @return true if a composite number is present in an array
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    public boolean containsComposite(int threadsQuantity) throws InterruptedException {
        PrimeThread[] threadPool = new PrimeThread[threadsQuantity];
        for (int i = 0; i < threadsQuantity; i++) {
            threadPool[i] = new PrimeThread(i, threadsQuantity, array);
            threadPool[i].start();
        }
        for (int i = 0; i < threadsQuantity; i++) {
            threadPool[i].join();
            if (threadPool[i].getResult()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether an array contains a composite int via
     * threadsQuantity of parallel PrimeThreads running.
     * Uses the number of threads specified at the moment
     * of object instantiation.
     *
     * @return true if a composite number is present in an array
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    @Override
    public boolean containsComposite() throws InterruptedException {
        return containsComposite(threads);
    }

    /**
     * Thread to which its index of the part of the
     * array to check and the total quantity of all the
     * threads in a pool are passed.
     * Checks its chunk and writes the result to a respective field.
     */
    private static class PrimeThread extends Thread {
        private final int chunkIndex;
        private final int threadsQuantity;
        private final int[] array;
        private volatile boolean result;

        /**
         * Thread constructor.
         *
         * @param chunkIndex index of its part of the array
         * @param threadsQuantity total quantity of threads in the pool
         */
        public PrimeThread(int chunkIndex, int threadsQuantity, int[] array) {
            this.chunkIndex = chunkIndex;
            this.threadsQuantity = threadsQuantity;
            this.array = array;
        }

        /**
         * Checks its chunk for composite numbers.
         */
        public void run() {
            int leftLimit = array.length / threadsQuantity * chunkIndex;
            int rightLimit = chunkIndex == threadsQuantity - 1 ? array.length
                    : array.length / threadsQuantity * (chunkIndex + 1);
            for (int i = leftLimit; i < rightLimit; i++) {
                if (!isPrime(array[i])) {
                    result = true;
                    return;
                }
            }
            result = false;
        }

        /**
         * Returns the result of a thread's work.
         *
         * @return true if the chunk contains a composite number.
         */
        public boolean getResult() {
            return result;
        }
    }
}
