package pe.edu.vallegrande.teacherportal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeacherPortalSeleniumTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1440,1000", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void teacherCanLoginAndUpdateAGrade() throws IOException {
        login();

        new Select(driver.findElement(By.id("studentId"))).selectByValue("1001");
        WebElement grade = driver.findElement(By.id("grade"));
        grade.clear();
        grade.sendKeys("19");
        driver.findElement(By.id("feedback")).sendKeys("Resultado validado con Selenium.");
        driver.findElement(By.id("save-grade-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-message")));
        WebElement row = driver.findElement(By.id("student-1001"));

        assertEquals("19.0", row.findElement(By.className("student-grade")).getText());
        assertTrue(row.findElement(By.className("student-feedback")).getText().contains("Selenium"));
        saveScreenshot("selenium-functional-test.png");
    }

    @Test
    void feedbackIsRenderedAsTextAndDoesNotExecuteXss() throws IOException {
        login();
        String payload = "<script>document.body.dataset.xss='executed'</script>";

        new Select(driver.findElement(By.id("studentId"))).selectByValue("1002");
        WebElement grade = driver.findElement(By.id("grade"));
        grade.clear();
        grade.sendKeys("17");
        driver.findElement(By.id("feedback")).sendKeys(payload);
        driver.findElement(By.id("save-grade-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-message")));
        WebElement row = driver.findElement(By.id("student-1002"));
        Object xssMarker = ((JavascriptExecutor) driver)
                .executeScript("return document.body.dataset.xss || null;");

        assertNull(xssMarker);
        assertEquals(payload, row.findElement(By.className("student-feedback")).getText());
        assertFalse(driver.getPageSource().contains("document.body.dataset.xss='executed'</script>"));
        saveScreenshot("selenium-xss-protection.png");
    }

    private void login() {
        driver.get("http://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("teacher@school.edu");
        driver.findElement(By.id("password")).sendKeys("Secure123!");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("grade-form")));
    }

    private void saveScreenshot(String fileName) throws IOException {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Path output = Path.of("target", "selenium-evidence", fileName);
        Files.createDirectories(output.getParent());
        Files.write(output, screenshot);
    }
}
