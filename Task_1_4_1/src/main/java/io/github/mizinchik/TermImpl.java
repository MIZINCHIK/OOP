package io.github.mizinchik;

import java.util.List;
import java.util.Map;

public class TermImpl implements Term {
    private Map<String, Subject> subjects;
    private Double GPA;
    private boolean GPAComputed;

    public TermImpl(Map<String, Subject> subjects) {
        this.subjects = subjects;
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

    private void computeGPA() {
        GPA = subjects.values().stream()
                .mapToDouble(subject -> subject.getGrade().getMark())
                .average().orElse(Double.NaN);
    }
}
