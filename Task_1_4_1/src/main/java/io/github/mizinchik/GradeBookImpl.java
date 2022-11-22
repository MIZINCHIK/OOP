package io.github.mizinchik;

import java.util.List;
import java.util.stream.Collectors;

public class GradeBookImpl implements GradeBook {
    Double GPA;
    Integer diplomaGrade;
    int termNumber;
    List<Term> terms;

    public GradeBookImpl(Integer diplomaGrade, int termNumber, List<Term> terms) {
        this.diplomaGrade = diplomaGrade;
        this.termNumber = termNumber;
        this.terms = terms;
        computeGPA();
    }

    @Override
    public Double getGPA() {
        return GPA;
    }

    @Override
    public Integer getDiplomaGrade() {
        return diplomaGrade;
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
    public List<Term> getTerms() {
        return terms;
    }

    @Override
    public List<Subject> getSubjects() {
        return terms.stream()
                .flatMap(term -> term.getSubjects().stream())
                .collect(Collectors.toList());
    }

    @Override
    public boolean possibleHonoredGraduation() {
        return false;
    }

    @Override
    public boolean receiveExtraStipend() {
        return false;
    }

    private void computeGPA() {
        return;
    }
}