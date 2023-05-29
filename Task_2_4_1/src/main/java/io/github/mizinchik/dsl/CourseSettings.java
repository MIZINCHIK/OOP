package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * General settings for the whole testing process.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseSettings extends Config {
    String branch;
    boolean disableLongTests;
}
