package io.github.mizinchik.git;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class Run {
    public static void run(String labDir) {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(labDir));
        ProjectConnection connection = connector.connect();
        connection.newBuild()
                .forTasks("test")
                .run();
        connection.close();
    }
}

