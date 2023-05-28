package io.github.mizinchik;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import io.github.mizinchik.dsl.CourseConfig;
import io.github.mizinchik.dsl.TaskAssignment;
import io.github.mizinchik.git.Download;
import io.github.mizinchik.git.Run;

public class Test {
    public static void main(String[] args) throws URISyntaxException {
        CourseConfig config = new CourseConfig();
        URI configPath = Objects.requireNonNull(Test.class.getClassLoader().getResource("config.groovy")).toURI();
        config.runFrom(configPath);
        config.postProcess();
        System.out.println(config);
        TaskAssignment assignment = config.getAssignments().get(0);
//        System.out.println(Download.download(assignment.getAssignee().getRepo()));
        Run.run("src/main/resources/labs/Task_1_1_1/");
    }
}
