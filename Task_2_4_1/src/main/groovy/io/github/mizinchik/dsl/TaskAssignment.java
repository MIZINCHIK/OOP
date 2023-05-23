package io.github.mizinchik.dsl;

import groovy.lang.GroovyObjectSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskAssignment extends Config {
    Student assignee;
    TaskInfo info;
    LocalDate softDeadline;
    LocalDate hardDeadline;
}
