package io.github.mizinchik.util;

import lombok.Data;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Analyzes project documentation and test report.
 */
@Data
public class Analyze {
    private int passedTests;
    private int totalTests;
    private String documentationExists;

    /**
     * Analyzes project documentation and test report.
     *
     * @param testResPath where the test results are located
     * @param documentationDir where the documentation should be
     */
    @SneakyThrows
    public void analyze(String testResPath, String documentationDir) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document junitDoc = builder.parse(new File(testResPath));
        junitDoc.getDocumentElement().normalize();
        Element junitTestSuite = (Element) junitDoc.getElementsByTagName("testsuite").item(0);
        totalTests = Integer.parseInt(junitTestSuite.getAttribute("tests"));
        int failedTests = Integer.parseInt(junitTestSuite.getAttribute("failures"));
        passedTests = totalTests - failedTests;
        Path documentationPath = Paths.get(documentationDir);
        documentationExists = Files.exists(documentationPath) ? "Generated" : "Missing";
    }
}
