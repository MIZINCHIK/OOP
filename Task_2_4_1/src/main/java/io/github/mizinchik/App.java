package io.github.mizinchik;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import io.github.mizinchik.dsl.CourseConfig;
import io.github.mizinchik.dsl.Group;
import io.github.mizinchik.dsl.Student;
import io.github.mizinchik.dsl.TaskAssignment;
import io.github.mizinchik.util.Analyze;
import io.github.mizinchik.util.Download;
import io.github.mizinchik.util.Run;
import io.github.mizinchik.util.TableChartBuilder;

public class App {
    private static final String testResDir = "/build/test-results/test/";
    private static final String documentationDir = "/build/docs/javadoc/";
    private static final String labDir = "src/main/resources/labs/";

    public static void main(String[] args) throws URISyntaxException {
        CourseConfig config = new CourseConfig();
        URI configPath = Objects.requireNonNull(App.class.getClassLoader().getResource("config.groovy")).toURI();
        config.runFrom(configPath);
        config.postProcess();
        for (Group group : config.getGroups()) {
            int tasks = 0;
            for (Student student : group.getStudents()) {
                boolean downloaded = Download.download(student.getRepo(), student.getMoniker(),
                        config.getSettings().getBranch());
                for (TaskAssignment assignment : student.getAssignments()) {
                    tasks++;
                    if (!downloaded) {
                        if (!Run.run(labDir +
                                student.getMoniker() +
                                "/" + assignment.getInfo().getId())) {
                            assignment.setBuild("Failed to build");
                            continue;
                        } else {
                            assignment.setBuild("Successfully");
                        }
                    } else {
                        assignment.setBuild("Failed to download");
                        continue;
                    }
                    Analyze analyze = new Analyze();
                    File testResFolder = new File(labDir + student.getMoniker() +
                            "/" + assignment.getInfo().getId() + "/" + testResDir);
                    String xmlFile = Objects.requireNonNull(
                            testResFolder.listFiles((dir, name) ->
                                    name.toLowerCase().endsWith(".xml")))[0].getName();
                    analyze.analyze(labDir +
                            student.getMoniker() + "/" +
                            assignment.getInfo().getId() +
                            testResDir + xmlFile, labDir +
                            student.getMoniker() + "/" +
                            assignment.getInfo().getId() + "/" + documentationDir);
                    assignment.setDocs(analyze.isDocumentationExists());
                    assignment.setTestsPassed(analyze.getPassedTests());
                    assignment.setTestsTotal(analyze.getTotalTests());
                }
            }
            group.setTasks(tasks);
        }
        TableChartBuilder.generateHTMLTableChart(config.getGroups());
    }
}
