package io.github.mizinchik;

public class CompositeArrayThread extends CompositeArrayConsecutive {

    public CompositeArrayThread(int[] array) {
        super(array);
    }

    public boolean containsComposite(int threadsQuantity) throws InterruptedException {
        PrimeThread[] threadPool = new PrimeThread[threadsQuantity];
        for (int i = 0; i < threadsQuantity; i++) {
            threadPool[i] = new PrimeThread(i, threadsQuantity);
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

    private class PrimeThread extends Thread {
        private final int chunkIndex;
        private final int threadsQuantity;
        private boolean result;

        public PrimeThread(int chunkIndex, int threadsQuantity) {
            this.chunkIndex = chunkIndex;
            this.threadsQuantity = threadsQuantity;
        }

        public void run() {
            int leftLimit = getArray().length / threadsQuantity * chunkIndex;
            int rightLimit = chunkIndex == threadsQuantity - 1 ? getArray().length
                    : getArray().length / threadsQuantity * (chunkIndex + 1);
            for (int i = leftLimit; i < rightLimit; i++) {
                if (!isPrime(getArray()[i])) {
                    result = true;
                    return;
                }
            }
            result = false;
        }

        public boolean getResult() {
            return result;
        }
    }
}
