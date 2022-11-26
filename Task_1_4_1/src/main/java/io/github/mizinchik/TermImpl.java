package io.github.mizinchik;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TermImpl implements Term {
    private final Map<String, Subject> subjects;
    private Double GPA;

    public TermImpl(List<Subject> subjects) throws IllegalArgumentException {
        if (subjects == null || subjects.contains(null)) {
            throw new IllegalArgumentException("Null parsed to Term constructor");
        }
        this.subjects = new HashMap<>();
        for (Subject subject : subjects) {
            if (this.subjects.containsKey(subject.getName())) {
                throw new IllegalArgumentException("Subjects with identical names");
            }
            this.subjects.put(subject.getName(), subject);
        }
    }

    @Override
    public Double getGPA() {
        computeGPA();
        return GPA;
    }

    @Override
    public List<Subject> getSubjects() {
        return subjects.values().stream().toList();
    }

    @Override
    public String getLecturer(String subject) {
        return subjects.get(subject).getLecturer();
    }

    @Override
    public void updateGrade(String subject, Integer grade) {
        subjects.get(subject).putGrade(grade);
    }

    @Override
    public Grade getGrade(String subject) {
        return subjects.get(subject).getGrade();
    }

    private void computeGPA() {
        GPA = subjects.values().stream()
                .mapToDouble(subject -> subject.isGraded() ? subject.getGrade().getMark() : 0)
                .average().orElse(Double.NaN);
    }
}
