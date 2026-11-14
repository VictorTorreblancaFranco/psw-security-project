package pe.edu.vallegrande.teacherportal.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.vallegrande.teacherportal.model.GradeForm;
import pe.edu.vallegrande.teacherportal.service.StudentService;

@Controller
public class PortalController {

    private final StudentService studentService;

    public PortalController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        if (!model.containsAttribute("gradeForm")) {
            model.addAttribute("gradeForm", new GradeForm());
        }
        addDashboardData(model, authentication);
        return "dashboard";
    }

    @PostMapping("/grades")
    public String updateGrade(
            @Valid @ModelAttribute("gradeForm") GradeForm form,
            BindingResult bindingResult,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            addDashboardData(model, authentication);
            return "dashboard";
        }

        studentService.updateGrade(form.getStudentId(), form.getGrade(), form.getFeedback());
        redirectAttributes.addFlashAttribute("successMessage", "Calificación actualizada correctamente.");
        return "redirect:/dashboard";
    }

    private void addDashboardData(Model model, Authentication authentication) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("teacherName", authentication.getName());
    }
}
