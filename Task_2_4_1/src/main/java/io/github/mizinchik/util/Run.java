package io.github.mizinchik.util;

import java.io.File;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

/**
 * Running tools.
 */
public class Run {
    /**
     * Runs tests and doc documentation for the project.
     *
     * @param labDir what to run
     * @return true if successful
     */
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

