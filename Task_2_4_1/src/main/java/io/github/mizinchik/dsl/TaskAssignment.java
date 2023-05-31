package io.github.mizinchik.dsl;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents an assigned task to some student of the OOP course.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskAssignment extends Config {
    TaskInfo info;
    LocalDate softDeadline;
    LocalDate hardDeadline;
    String build = "";
    String docs = "";
    int testsTotal;
    int testsPassed;
}
