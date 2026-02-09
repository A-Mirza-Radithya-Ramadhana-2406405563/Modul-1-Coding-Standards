package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class DeleteProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void deleteProductFlow_isCorrect(ChromeDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String createUrl = baseUrl + "/product/create";
        driver.get(createUrl);

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys("Sampo cap Delete");

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys("61");

        driver.findElement(By.tagName("form")).submit();

        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(), 'Sampo cap Delete')]/following-sibling::td//button[contains(text(), 'Delete')]")
        ));

        deleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[contains(text(), 'Sampo cap Delete')]")));

        String pageSource = driver.getPageSource();
        assertFalse(pageSource.contains("Sampo cap Delete"));
    }
}
