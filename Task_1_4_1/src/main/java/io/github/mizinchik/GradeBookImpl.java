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
        computeGPA();
        GPAComputed = true;
        setMarkedSubjects();
    }

    @Override
    public Double getGPA() {
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
    public int getTermsAmount() {
        return terms.size();
    }

    @Override
    public int getTermNumber() {
        return termNumber;
    }

    @Override
    public Term getCurrentTerm() {
        return terms.get(termNumber - 1);
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
                .mapToDouble(x -> x.getGrade() == null ? 5 : x.getGrade().mark())
                .average().orElse(Double.NaN) >= 4.5)
                && (markedSubjects.stream().map(x -> x.getGrade().mark()).noneMatch(x -> x < 4))
                && (diplomaGrade == null || diplomaGrade == 5));
    }

    @Override
    public boolean receiveExtraStipend() {
        return terms.get(termNumber - 2).getGPA() == 5.0;
    }

    private void computeGPA() {
        GPA = markedSubjects.stream().mapToDouble(x -> x.getGrade().mark())
                .average().orElse(Double.NaN);
        GPAComputed = true;
    }
}