package io.github.mizinchik;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import io.github.mizinchik.dsl.CourseConfig;
import io.github.mizinchik.dsl.TaskAssignment;
import io.github.mizinchik.util.Analyze;
import io.github.mizinchik.util.Download;
import io.github.mizinchik.util.Run;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class Test {
    public static void main(String[] args) throws URISyntaxException {
        String testResPath = "/build/test-results/test/TEST-io.github.mizinchik.HeapSortTest.xml";
        String documentationDir = "/build/docs/javadoc/";
        CourseConfig config = new CourseConfig();
        URI configPath = Objects.requireNonNull(Test.class.getClassLoader().getResource("config.groovy")).toURI();
        config.runFrom(configPath);
        config.postProcess();
        System.out.println(config);
        TaskAssignment assignment = config.getAssignments().get(0);
        System.out.println(Download.download(assignment.getAssignee().getRepo(), assignment.getAssignee().getMoniker()));
        Run.run("src/main/resources/labs/" + assignment.getAssignee().getMoniker() + "/" + assignment.getInfo().getId());
        Analyze analysis = new Analyze();
        try {
            analysis.analyze("src/main/resources/labs/" + assignment.getAssignee().getMoniker() + "/" + assignment.getInfo().getId()
                    + "/" + testResPath, "src/main/resources/labs/" + assignment.getAssignee().getMoniker() + "/" + assignment.getInfo().getId()
                    + "/" + documentationDir);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        analysis.writeReport();
    }
}
