package io.github.mizinchik;

/**
 * Interface for the classes storing
 * data structures of numbers with the ability
 * to check whether a composite number
 * is present.
 *
 * @author MIZINCHIK
 */
public interface CompositeArrayInterface {
    /**
     * Checks whether an instance contains a composite number.
     *
     * @return true if a composite number is present
     */
    boolean containsComposite() throws InterruptedException;
}
