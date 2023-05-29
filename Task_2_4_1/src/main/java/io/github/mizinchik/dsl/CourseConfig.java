package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseConfig extends Config {
    List<Student> allStudents;
    List<Group> groups;
    List<TaskInfo> tasks;
    List<ClassHour> classes;
    CourseSettings settings;
}
