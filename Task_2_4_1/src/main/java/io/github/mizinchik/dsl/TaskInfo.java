package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Contains all the necessary information about an OOP task.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskInfo extends Config {
    String id;
    String title;
    Integer points;
}
