package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Represents an assigned task to some student of the OOP course.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskAssignment extends Config {
    TaskInfo info;
    LocalDate softDeadline;
    LocalDate hardDeadline;
    String build;
    boolean docs;
    int testsTotal;
    int testsPassed;
}
