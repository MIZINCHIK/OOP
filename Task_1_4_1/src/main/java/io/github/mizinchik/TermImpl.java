package io.github.mizinchik;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TermImpl implements Term {
    private final Map<String, Subject> subjects;
    private Double GPA;
    private boolean GPAComputed;

    public TermImpl(List<Subject> subjects) {
        this.subjects = new HashMap<>();
        for (Subject subject : subjects) {
            this.subjects.put(subject.getName(), subject);
        }
        computeGPA();
        GPAComputed = true;
    }

    @Override
    public Double getGPA() {
        if (!GPAComputed){
            computeGPA();
            GPAComputed = true;
        }
        return GPA;
    }

    @Override
    public void addSubject(Subject subject) {
        subjects.put(subject.getName(), subject);
        GPAComputed = false;
    }

    @Override
    public List<Subject> getSubjects() {
        return null;
    }

    @Override
    public void updateGrade(String subject, Grade grade) {
        subjects.get(subject).putGrade(grade);
        GPAComputed = false;
    }

    @Override
    public Grade getGrade(String subject) {
        return subjects.get(subject).getGrade();
    }

    private void computeGPA() {
        GPA = subjects.values().stream()
                .mapToDouble(subject -> subject.getGrade().mark())
                .average().orElse(Double.NaN);
    }
}
