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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeBookTest {
    @Test
    @DisplayName("Small test")
    void testSmall() {
        var calculusGrade1 = new GradeMark(5);
        var calculus1 = new SubjectImpl(1, "Calculus", "Vaskevich", calculusGrade1);
        var programmingGrade1 = new GradeMark(5);
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
        assertTrue(gradeBook.possibleHonoredGraduation());
        assertTrue(gradeBook.receiveExtraStipend());
        assertEquals(5, gradeBook.getGPA());
    }
}