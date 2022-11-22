package io.github.mizinchik;

import java.util.List;
import java.util.Map;

public class TermImpl implements Term {
    Map<String, Subject> subjects;
    Double GPA;

    @Override
    public Double getGPA() {
        return GPA;
    }

    @Override
    public void addSubject(Subject subject) {
        subjects.put(subject.getName(), subject);

    }

    @Override
    public List<Subject> getSubjects() {
        return null;
    }

    @Override
    public void updateGrade(String subject, Grade grade) {
        subjects.get(subject).putGrade(grade);
        computeGPA();
    }

    private void computeGPA() {
        GPA = subjects.values().stream()
                .mapToDouble(subject -> subject.getGrade().getMark())
                .average().orElse(Double.NaN);
    }
}
