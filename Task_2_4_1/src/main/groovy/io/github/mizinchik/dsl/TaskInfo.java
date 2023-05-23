package io.github.mizinchik.dsl;

import groovy.lang.GroovyObjectSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskInfo extends Config {
    String id;
    String title;
    Integer points;
}
