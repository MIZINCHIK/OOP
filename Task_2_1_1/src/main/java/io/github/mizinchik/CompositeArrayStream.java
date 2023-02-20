package io.github.mizinchik;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains an array of ints.
 * Can perform both parallel (via ParallelStream) and
 * consecutive checks whether that array contains
 * a composite number or not.
 *
 * @author MIZINCHIK
 */
public class CompositeArrayStream extends CompositeArrayConsecutive {
    /**
     * Constructs an instance from a reference
     * to an array of ints.
     *
     * @param array of ints
     */
    public CompositeArrayStream(int[] array) {
        super(array);
    }

    /**
     * Checks whether an array contains a composite int via
     * parallelStream on a List of Integers.
     *
     * @return true if a composite number is present in an array
     */
    boolean containsCompositeStream() {
        List<Integer> list = Arrays.stream(getArray()).boxed().collect(Collectors.toList());
        return list.parallelStream()
                .filter(number -> !isPrime(number))
                .findFirst().orElse(null) != null;
    }
}
