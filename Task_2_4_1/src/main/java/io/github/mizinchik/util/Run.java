package io.github.mizinchik.util;

import io.github.mizinchik.dsl.TaskAssignment;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.UnsupportedVersionException;

import java.io.File;

public class Run {
    public static boolean run(String labDir) {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(labDir));
        try (ProjectConnection connection = connector.connect()) {
            connection.newBuild()
                    .forTasks("test", "javadoc")
                    .run();
            return true;
        } catch (BuildException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

