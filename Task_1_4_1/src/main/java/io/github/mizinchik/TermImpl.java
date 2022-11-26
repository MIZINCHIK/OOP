package io.github.mizinchik;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Novosibirsk State University's semester.
 *
 * @author MIZINCHIK
 */
public class TermImpl implements Term {
    private final Map<String, Subject> subjects;
    private Double GPA;

    /**
     * Constructs a term from a list of subjects.
     *
     * @param subjects of which the semester consists
     * @throws IllegalArgumentException is subjects are missing
     */
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

    /**
     * Get a grade point average for all
     * the subjects in the term.
     *
     * @return GPA
     */
    @Override
    public Double getGPA() {
        computeGPA();
        return GPA;
    }

    /**
     * Get the list of all the subjects taking place on the term.
     *
     * @return list of subjects
     */
    @Override
    public List<Subject> getSubjects() {
        return subjects.values().stream().toList();
    }

    /**
     * Get the name of a lecturer on a particular subject.
     *
     * @param subject which you seek for
     * @return name of the lecturer
     */
    @Override
    public String getLecturer(String subject) {
        return subjects.get(subject).getLecturer();
    }

    /**
     * Set or change the grade for a certain subject.
     *
     * @param subject to set the grade on
     * @param grade to set
     */
    @Override
    public void updateGrade(String subject, Integer grade) {
        subjects.get(subject).putGrade(grade);
    }

    /**
     * Get the grade for the specific subject.
     *
     * @param subject to get the grade on
     * @return grade
     */
    @Override
    public Grade getGrade(String subject) {
        return subjects.get(subject).getGrade();
    }

    /**
     * Computes the GPA of the semester.
     */
    private void computeGPA() {
        GPA = subjects.values().stream()
                .mapToDouble(subject -> subject.isGraded() ? subject.getGrade().getMark() : 0)
                .average().orElse(Double.NaN);
    }
}
