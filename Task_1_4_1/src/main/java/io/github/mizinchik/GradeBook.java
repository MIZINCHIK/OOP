package io.github.mizinchik;

import java.util.List;

public interface GradeBook {
    Double getGPA();

    Integer getDiplomaGrade();

    void setDiplomaGrade(Integer grade);

    int getTermsAmount();

    int getTermNumber();

    Term getCurrentTerm();

    void goNextTerm();

    List<Term> getTerms();

    List<Subject> getSubjects();

    boolean possibleHonoredGraduation();

    boolean receiveExtraStipend();
}
