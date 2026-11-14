package pe.edu.vallegrande.teacherportal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class PortalSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRedirectAnonymousUserToLogin() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    void shouldSendBrowserSecurityHeaders() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Security-Policy",
                        org.hamcrest.Matchers.containsString("default-src 'self'")))
                .andExpect(header().string("Permissions-Policy",
                        org.hamcrest.Matchers.containsString("camera=()")))
                .andExpect(header().string("Cross-Origin-Embedder-Policy", "require-corp"))
                .andExpect(header().string("X-Content-Type-Options", "nosniff"));
    }

    @Test
    @WithMockUser(username = "teacher@school.edu", roles = "TEACHER")
    void shouldRenderDashboardForTeacher() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Lista de estudiantes")));
    }

    @Test
    @WithMockUser(username = "teacher@school.edu", roles = "TEACHER")
    void shouldRejectGradeUpdateWithoutCsrfToken() throws Exception {
        mockMvc.perform(post("/grades")
                        .param("studentId", "1001")
                        .param("grade", "18")
                        .param("feedback", "Buen trabajo"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "teacher@school.edu", roles = "TEACHER")
    void shouldValidateGradeRange() throws Exception {
        mockMvc.perform(post("/grades")
                        .with(csrf())
                        .param("studentId", "1001")
                        .param("grade", "25")
                        .param("feedback", "Valor fuera del rango"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("calificación máxima es 20")));
    }
}
