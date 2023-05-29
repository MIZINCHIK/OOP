package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
