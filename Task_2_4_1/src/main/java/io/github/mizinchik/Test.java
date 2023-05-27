package io.github.mizinchik;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import io.github.mizinchik.dsl.CourseConfig;

public class Test {
    public static void main(String[] args) throws URISyntaxException {
        CourseConfig config = new CourseConfig();
        URI configPath = Objects.requireNonNull(Test.class.getClassLoader().getResource("config.groovy")).toURI();
        config.runFrom(configPath);
        config.postProcess();
//        config.getGroup().getStudents().forEach(System.out::println);
        System.out.println(config);
    }
}
