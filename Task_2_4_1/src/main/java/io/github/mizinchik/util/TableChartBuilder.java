package io.github.mizinchik.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import io.github.mizinchik.dsl.Group;
import lombok.Cleanup;
import lombok.SneakyThrows;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableChartBuilder {
    public static final String resultDir = "src/main/resources/results/";
    public static final String templatePath = "template.ftl";

    @SneakyThrows
    public static void generateHTMLTableChart(List<Group> groups) {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setClassForTemplateLoading(TableChartBuilder.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        File out = new File(resultDir, "output.html");
        @Cleanup Writer fileWriter = new FileWriter(out);
        Template template = configuration.getTemplate(templatePath);
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("groups", groups);
        template.process(dataModel, fileWriter);
    }
}
