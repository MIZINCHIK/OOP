package io.github.mizinchik;

import java.util.List;

public interface Term {
    Double getGPA();

    List<Subject> getSubjects();

    String getLecturer(String subject);

    void updateGrade(String subject, Integer grade);

    Grade getGrade(String subject);
}
