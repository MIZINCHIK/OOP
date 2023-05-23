package io.github.mizinchik.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URL;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Config {
    String moniker;
    String name;
    URL repo;
}
