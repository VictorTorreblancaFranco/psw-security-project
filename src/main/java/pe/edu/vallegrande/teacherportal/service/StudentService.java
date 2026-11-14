package pe.edu.vallegrande.teacherportal.service;

import org.springframework.stereotype.Service;
import pe.edu.vallegrande.teacherportal.model.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Long, Student> students = new LinkedHashMap<>();

    public StudentService() {
        students.put(1001L, new Student(1001L, "ST-001", "Ana Torres", "Software Testing", 16.0,
                "Buen avance en automatización."));
        students.put(1002L, new Student(1002L, "ST-002", "Luis Mendoza", "Software Testing", 14.0,
                "Reforzar casos negativos."));
        students.put(1003L, new Student(1003L, "ST-003", "María Salas", "Software Testing", 18.0,
                "Excelente documentación."));
    }

    public synchronized List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public synchronized Student updateGrade(long studentId, double grade, String feedback) {
        Student current = students.get(studentId);
        if (current == null) {
            throw new IllegalArgumentException("El estudiante no existe");
        }

        Student updated = new Student(
                current.id(), current.code(), current.fullName(), current.course(), grade, feedback.trim());
        students.put(studentId, updated);
        return updated;
    }
}
