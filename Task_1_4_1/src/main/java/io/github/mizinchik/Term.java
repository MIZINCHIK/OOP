package io.github.mizinchik;

import java.util.List;

public interface Term {
    Double getGPA();

    void addSubject(Subject subject);

    List<Subject> getSubjects();

    void updateGrade(String subject, Grade grade);
}
