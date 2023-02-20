package io.github.mizinchik;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeArrayStream extends CompositeArrayConsecutive {
    public CompositeArrayStream(int[] array) {
        super(array);
    }

    boolean containsCompositeStream() {
        List<Integer> list = Arrays.stream(getArray()).boxed().collect(Collectors.toList());
        return list.parallelStream()
                .filter(number -> !isPrime(number))
                .findFirst().orElse(null) != null;
    }
}
