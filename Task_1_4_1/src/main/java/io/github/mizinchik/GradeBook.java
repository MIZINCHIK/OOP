package io.github.mizinchik;

import java.util.List;

public interface GradeBook {
    Double getGPA();

    Double getSavedGPA();

    Integer getDiplomaGrade();

    void setDiplomaGrade(Integer grade);

    Grade getSubjectGrade(String subject, int term);

    void setSubjectGrade(String subject, int term, Grade grade);

    void setSubjectGrade(String subject, int term, Integer grade);

    int getTermsAmount();

    int getTermNumber();

    Term getCurrentTerm();

    void goNextTerm();

    List<Term> getTerms();

    List<Subject> getMarkedSubjects();

    boolean possibleHonoredGraduation();

    boolean receiveExtraStipend();
}
