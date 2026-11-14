package pe.edu.vallegrande.teacherportal.service;

import org.junit.jupiter.api.Test;
import pe.edu.vallegrande.teacherportal.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentServiceTest {

    private final StudentService service = new StudentService();

    @Test
    void shouldUpdateStudentGradeAndTrimFeedback() {
        Student updated = service.updateGrade(1001L, 19.0, "  Trabajo sobresaliente.  ");

        assertEquals(19.0, updated.grade());
        assertEquals("Trabajo sobresaliente.", updated.feedback());
    }

    @Test
    void shouldRejectUnknownStudent() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateGrade(9999L, 15.0, "Comentario"));
    }
}
