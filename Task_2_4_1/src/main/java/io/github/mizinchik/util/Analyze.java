package io.github.mizinchik.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Analyze {
    public static String resultDir = "src/main/resources/results/";
    private int passedTests;
    private int totalTests;
    private boolean documentationExists;

    public void analyze(String testResPath, String documentationDir) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document junitDoc = builder.parse(new File(testResPath));
        junitDoc.getDocumentElement().normalize();
        Element junitTestSuite = (Element) junitDoc.getElementsByTagName("testsuite").item(0);
        totalTests = Integer.parseInt(junitTestSuite.getAttribute("tests"));
        int failedTests = Integer.parseInt(junitTestSuite.getAttribute("failures"));
        passedTests = totalTests - failedTests;

        Path documentationPath = Paths.get(documentationDir);
        documentationExists = Files.exists(documentationPath);
    }

    public void writeReport() {
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table border='1'>");
        htmlTable.append("<tr><th>Tests Passed</th><th>Documentation</th></tr>");
        htmlTable.append("<tr>");
        htmlTable.append("<td>").append(passedTests).append("/").append(totalTests).append("</td>");
        htmlTable.append("<td>").append(documentationExists ? "Exists" : "Not Found").append("</td>");
        htmlTable.append("</tr>");
        htmlTable.append("</table>");

        File out = new File(resultDir, "output.html");
        try (FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.write(htmlTable.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
