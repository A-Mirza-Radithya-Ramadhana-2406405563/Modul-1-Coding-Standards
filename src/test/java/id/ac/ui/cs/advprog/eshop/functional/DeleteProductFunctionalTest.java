package id.ac.ui.cs.advprog.eshop.functional;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteProductFunctionalTest extends BaseFunctionalTest {
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
