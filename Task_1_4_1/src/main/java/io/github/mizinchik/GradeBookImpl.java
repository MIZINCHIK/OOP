package io.github.mizinchik;

import java.util.List;
import java.util.stream.Collectors;

public class GradeBookImpl implements GradeBook {
    private boolean GPAComputed;
    private Double GPA;
    private Integer diplomaGrade;
    private int termNumber;
    private final List<Term> terms;
    private List<Subject> markedSubjects;

    public GradeBookImpl(Integer diplomaGrade, int termNumber, List<Term> terms) {
        this.diplomaGrade = diplomaGrade;
        this.termNumber = termNumber;
        this.terms = terms;
        setMarkedSubjects();
        computeGPA();
        GPAComputed = true;
    }

    @Override
    public Double getGPA() {
        computeGPA();
        return GPA;
    }

    @Override
    public Double getSavedGPA() {
        if (!GPAComputed) {
            computeGPA();
        }
        return GPA;
    }

    @Override
    public Integer getDiplomaGrade() {
        return diplomaGrade;
    }

    @Override
    public void setDiplomaGrade(Integer grade) {
        diplomaGrade = grade;
    }

    @Override
    public Grade getSubjectGrade(String subject, int term) {
        return terms.get(term - 1).getGrade(subject);
    }

    @Override
    public void setSubjectGrade(String subject, int term, Grade grade) {
        terms.get(term - 1).updateGrade(subject, grade);
    }

    @Override
    public void setSubjectGrade(String subject, int term, Integer grade) {
        terms.get(term - 1).updateGrade(subject, grade);
    }

    @Override
    public int getTermsAmount() {
        return terms.size();
    }

    @Override
    public int getTermNumber() {
        return termNumber;
    }

    @Override
    public Term getCurrentTerm() {
        if (termNumber < terms.size()) {
            return terms.get(termNumber - 1);
        } else {
            return null;
        }
    }

    @Override
    public void goNextTerm() {
        termNumber++;
        setMarkedSubjects();
    }

    @Override
    public List<Term> getTerms() {
        return terms;
    }

    private void setMarkedSubjects() {
        markedSubjects = terms.subList(0, termNumber - 1).stream()
                .flatMap(term -> term.getSubjects().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Subject> getMarkedSubjects() {
        return markedSubjects;
    }

    @Override
    public boolean possibleHonoredGraduation() {
        return ((terms.stream().flatMap(term -> term.getSubjects().stream())
                .mapToDouble(x -> !x.isGraded() ? 5 : x.getGrade().getMark())
                .average().orElse(Double.NaN) >= 4.75)
                && (markedSubjects == null || markedSubjects.stream().map(x -> x.getGrade().getMark()).noneMatch(x -> x < 4))
                && (diplomaGrade == null || diplomaGrade == 5));
    }

    @Override
    public boolean receiveExtraStipend() {
        if (termNumber == 1) {
            return false;
        }
        return terms.get(termNumber - 2).getGPA() == 5.0;
    }

    private void computeGPA() {
        if (termNumber == 1) {
            GPA = (double) 0;
            GPAComputed = true;
        } else {
            GPA = markedSubjects.stream().mapToDouble(x -> x.getGrade().getMark())
                    .average().orElse(Double.NaN);
            GPAComputed = true;
        }
    }
}