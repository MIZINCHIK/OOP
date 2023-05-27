package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseConfig extends Config {
    Group group;
    List<TaskInfo> tasks;
    List<TaskAssignment> assignments;
    List<ClassHour> classes;
    CourseSettings settings;
}
