package io.github.mizinchik;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a grade book for
 * the Novosibirsk State University.
 *
 * @author MIZINCHIK
 */
public class GradeBookImpl implements GradeBook {
    private Double Gpa;
    private Integer diplomaGrade;
    private int termNumber;
    private final List<Term> terms;
    private List<Subject> markedSubjects;

    /**
     * Constructs a grade book from terms and a thesis grade.
     *
     * @param diplomaGrade if known
     * @param termNumber which term you a currently in
     * @param terms to get a diploma
     * @throws IllegalArgumentException there are absent terms
     */
    public GradeBookImpl(Integer diplomaGrade, int termNumber,
                         List<Term> terms) throws IllegalArgumentException {
        if (terms == null || termNumber == 0 || terms.contains(null)) {
            throw new IllegalArgumentException("Can't create a GradeBook with 0 terms");
        }
        this.diplomaGrade = diplomaGrade;
        this.termNumber = termNumber;
        this.terms = terms;
        setMarkedSubjects();
        computeGPA();
    }

    /**
     * Get a grade point average for all
     * the subjects in the previous terms.
     * Computes it each time it's called due to the
     * presence of the ability to change the state of grades
     * individually without notifying the parent.
     *
     * @return GPA
     */
    @Override
    public Double getGpa() {
        computeGPA();
        return Gpa;
    }

    /**
     * Get a grade of the thesis.
     *
     * @return diploma grade
     */
    @Override
    public Integer getDiplomaGrade() {
        return diplomaGrade;
    }

    /**
     * Set the thesis' grade.
     *
     * @param grade of the diploma
     */
    @Override
    public void setDiplomaGrade(Integer grade) {
        diplomaGrade = grade;
    }

    /**
     * Get the grade of a subject.
     *
     * @param subject which you seek for
     * @param term in which the subject was evaluated
     * @return the grade
     */
    @Override
    public Grade getSubjectGrade(String subject, int term) {
        return terms.get(term - 1).getGrade(subject);
    }

    /**
     * Set the grade of a subject.
     *
     * @param subject which you seek for
     * @param term in which the subject was evaluated
     * @param grade of the evaluation
     */
    @Override
    public void setSubjectGrade(String subject, int term, Integer grade) {
        terms.get(term - 1).updateGrade(subject, grade);
    }

    /**
     * Get the name of a lecturer on the subject.
     *
     * @param subject which you seek for
     * @param term in which the subject was evaluated
     * @return the lecturer's name
     */
    @Override
    public String getLecturer(String subject, int term) {
        return terms.get(term - 1).getLecturer(subject);
    }

    /**
     * Get the amount of terms to complete to get
     * a diploma.
     *
     * @return the amount of terms
     */
    @Override
    public int getTermsAmount() {
        return terms.size();
    }

    /**
     * Get the number of the current term.
     *
     * @return the order number
     */
    @Override
    public int getTermNumber() {
        return termNumber;
    }

    /**
     * Get the current term.
     *
     * @return the current term
     */
    @Override
    public Term getCurrentTerm() {
        if (termNumber < terms.size()) {
            return terms.get(termNumber - 1);
        } else {
            return null;
        }
    }

    /**
     * Changes the current term to the next one.
     */
    @Override
    public void goNextTerm() {
        termNumber++;
        setMarkedSubjects();
    }

    /**
     * Get the list of all the terms required to
     * get a diploma.
     *
     * @return list of terms
     */
    @Override
    public List<Term> getTerms() {
        return terms;
    }


    /**
     * Set the list of all the subject that took place in
     * all the previous terms.
     */
    private void setMarkedSubjects() {
        markedSubjects = terms.subList(0, termNumber - 1).stream()
                .flatMap(term -> term.getSubjects().stream())
                .collect(Collectors.toList());
    }

    /**
     * Get all the subjects which classes where conducted
     * in the previous terms. And thus they are already
     * necessarily evaluated.
     *
     * @return marked subjects
     */
    @Override
    public List<Subject> getMarkedSubjects() {
        return markedSubjects;
    }

    /**
     * Check if it's still possible to graduate with honors.
     * Graduation with honors is possible if no grades under 4 points
     * were received, if the GPA is 4.75 or above and if the thesis
     * is evaluated as excellent.
     *
     * @return true if honored graduation is possible
     */
    @Override
    public boolean possibleHonoredGraduation() {
        return ((terms.stream().flatMap(term -> term.getSubjects().stream())
                .mapToDouble(x -> x.isGraded() ? x.getGrade().getMark() : 5)
                .average().orElse(Double.NaN) >= 4.75)
                && (markedSubjects == null || markedSubjects.stream().map(x -> x.getGrade().getMark()).noneMatch(x -> x < 4))
                && (diplomaGrade == null || diplomaGrade == 5));
    }

    /**
     * Check if you were granted with an extra stipend.
     * You are granted with it if your previous term was completed
     * with excellent marks only.
     *
     * @return true if you are receiving an extra stipend
     */
    @Override
    public boolean receiveExtraStipend() {
        if (termNumber == 1) {
            return false;
        }
        return terms.get(termNumber - 2).getGpa() == 5.0;
    }

    /**
     * Computes the GPA.
     * If you have just started your education it is set to zero.
     */
    private void computeGPA() {
        if (termNumber == 1) {
            Gpa = (double) 0;
        } else {
            Gpa = markedSubjects.stream().mapToDouble(x -> x.getGrade().getMark())
                    .average().orElse(Double.NaN);
        }
    }
}