package io.github.mizinchik;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeBookTest {
    @Test
    @DisplayName("Small test")
    void testSmall() {
        var calculusGrade1 = new GradeMark(5);
        var calculus1 = new SubjectImpl(1, "Calculus", "Vaskevich", calculusGrade1);
        var programmingGrade1 = new GradeMark(4);
        var programming1 = new SubjectImpl(1, "Programming", "Gatilov", programmingGrade1);
        var izmorGrade = new GradeCredit(null);
        var izmor = new SubjectImpl(2, "Izmor", "HeZe", izmorGrade);
        var calculusGrade2 = new GradeMark(null);
        var programmingGrade2 = new GradeMark(null);
        var calculus2 = new SubjectImpl(2, "Calculus", "Vaskevich", calculusGrade2);
        var programming2 = new SubjectImpl(2, "Programming", "Gatilov", programmingGrade2);
        var firstTerm = new ArrayList<Subject>();
        firstTerm.add(calculus1);
        firstTerm.add(programming1);
        var secondTerm = new ArrayList<Subject>();
        secondTerm.add(calculus2);
        secondTerm.add(programming2);
        secondTerm.add(izmor);
        var term1 = new TermImpl(firstTerm);
        var term2 = new TermImpl(secondTerm);
        var termList = new ArrayList<Term>();
        termList.add(term1);
        termList.add(term2);
        var gradeBook = new GradeBookImpl(null, 2, termList);
        assertEquals("Vaskevich", gradeBook.getLecturer("Calculus", 1));
        assertEquals(2, calculus2.getTermNumber());
        assertEquals(2, gradeBook.getTermNumber());
        assertEquals(2, gradeBook.getTermsAmount());
        assertTrue(gradeBook.possibleHonoredGraduation());
        assertFalse(gradeBook.receiveExtraStipend());
        assertNull(gradeBook.getSubjectGrade("Izmor", 2).getMark());
        assertEquals(4.5, gradeBook.getGPA());
        assertEquals(5, gradeBook.getSubjectGrade("Calculus", 1).getMark());
        assertNull(gradeBook.getDiplomaGrade());
        programming1.grade.updateGrade(3);
        assertFalse(gradeBook.possibleHonoredGraduation());
        assertFalse(gradeBook.receiveExtraStipend());
        programming1.grade.updateGrade(5);
        assertTrue(gradeBook.receiveExtraStipend());
        izmorGrade.updateGrade(3);
        assertEquals(5, izmorGrade.getMark());
        assertTrue(gradeBook.possibleHonoredGraduation());
        gradeBook.setSubjectGrade("Calculus", 2, 4);
        gradeBook.setSubjectGrade("Programming", 2, 4);
        gradeBook.setSubjectGrade("Programming", 1, 4);
        assertFalse(gradeBook.possibleHonoredGraduation());
        gradeBook.setSubjectGrade("Calculus", 2, 5);
        assertFalse(gradeBook.possibleHonoredGraduation());
        gradeBook.setSubjectGrade("Programming", 2, 5);
        assertTrue(gradeBook.possibleHonoredGraduation());
        assertEquals(4.5, gradeBook.getGPA());
        gradeBook.goNextTerm();
        assertEquals(4.8, gradeBook.getGPA());
        gradeBook.setDiplomaGrade(4);
        assertFalse(gradeBook.possibleHonoredGraduation());
        assertNull(gradeBook.getCurrentTerm());
        assertEquals(5, gradeBook.getMarkedSubjects().size());
        assertEquals(2, gradeBook.getTerms().size());
    }

    @Test
    @DisplayName("Grade Test")
    void testGrade() {
        var calculusGrade = new GradeMark(null);
        var izmorGrade = new GradeCredit(null);
        calculusGrade.updateGrade(null);
        assertNull(calculusGrade.getCredit());
        assertNull(calculusGrade.getMark());
        izmorGrade.updateGrade(null);
        assertNull(izmorGrade.getMark());
        assertNull(izmorGrade.getCredit());
        izmorGrade.updateGrade(2);
        assertEquals(2, izmorGrade.getMark());
        assertEquals(false, izmorGrade.getCredit());
        izmorGrade.updateGrade(3);
        assertEquals(5, izmorGrade.getMark());
        assertEquals(true, izmorGrade.getCredit());
        var newCredit = new GradeCredit(false);
        assertFalse(newCredit.getCredit());
    }
}