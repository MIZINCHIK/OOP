package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseSettings extends Config {
    String branch;
    boolean disableLongTests;
}
