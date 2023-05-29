package io.github.mizinchik.dsl;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a group in terms of NSU.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Group extends Config {
    String name;
    List<Student> students;
    Integer tasks;
}
