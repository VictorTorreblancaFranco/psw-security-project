package pe.edu.vallegrande.teacherportal.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GradeForm {

    @NotNull(message = "Selecciona un estudiante")
    private Long studentId;

    @NotNull(message = "La calificación es obligatoria")
    @DecimalMin(value = "0.0", message = "La calificación mínima es 0")
    @DecimalMax(value = "20.0", message = "La calificación máxima es 20")
    private Double grade;

    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 180, message = "El comentario no debe superar 180 caracteres")
    private String feedback;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
