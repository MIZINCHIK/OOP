package io.github.mizinchik.dsl;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents an OOP course students.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Config {
    String moniker;
    String name;
    String repo;
    List<TaskAssignment> assignments;
}
