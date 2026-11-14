package pe.edu.vallegrande.teacherportal.model;

public record Student(
        long id,
        String code,
        String fullName,
        String course,
        double grade,
        String feedback) {
}
