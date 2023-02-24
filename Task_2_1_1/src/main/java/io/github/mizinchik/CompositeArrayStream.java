package io.github.mizinchik;

import static io.github.mizinchik.CompositeArrayConsecutive.isPrime;

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
public class CompositeArrayStream implements CompositeArrayInterface {
    private final int[] array;

    /**
     * Constructs an instance from a reference
     * to an array of ints.
     *
     * @param array of ints
     */
    public CompositeArrayStream(int[] array) {
        this.array = array.clone();
    }

    /**
     * Checks whether an array contains a composite int via
     * parallelStream on a List of Integers.
     *
     * @return true if a composite number is present in an array
     */
    @Override
    public boolean containsComposite() {
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        return list.parallelStream()
                .filter(number -> !isPrime(number))
                .findFirst().orElse(null) != null;
    }
}
