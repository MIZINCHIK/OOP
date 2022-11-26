package io.github.mizinchik;

import java.util.List;

public interface Term {
    Double getGPA();

    List<Subject> getSubjects();

    void updateGrade(String subject, Grade grade);

    void updateGrade(String subject, Integer grade);

    Grade getGrade(String subject);
}
