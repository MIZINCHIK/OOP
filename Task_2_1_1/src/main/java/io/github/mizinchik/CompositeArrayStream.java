package io.github.mizinchik;

import java.util.Arrays;
import java.util.List;

public class CompositeArrayStream extends CompositeArrayConsecutive {
    public CompositeArrayStream(int[] array) {
        super(array);
    }

    boolean containsCompositeStream() {
        List<Integer> list = Arrays.stream(getArray()).boxed().toList();
        return list.parallelStream()
                .filter(number -> !isPrime(number))
                .findFirst().orElse(null) != null;
    }
}
