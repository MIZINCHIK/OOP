package io.github.mizinchik;

import java.util.List;

public interface GradeBook {
    Double getGPA();

    Integer getDiplomaGrade();

    int getTermNumber();

    Term getCurrentTerm();

    List<Term> getTerms();

    List<Subject> getSubjects();

    boolean possibleHonoredGraduation();

    boolean receiveExtraStipend();
}
