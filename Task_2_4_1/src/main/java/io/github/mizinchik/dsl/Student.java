package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Config {
    String moniker;
    String name;
    String repo;
    List<TaskAssignment> assignments;
}
