package id.ac.ui.cs.advprog.eshop.functional;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateProductFunctionalTest extends BaseFunctionalTest {
    @Test
    void createProductFlow_isCorrect(ChromeDriver driver) throws Exception {
        String url = baseUrl + "/product/create";
        driver.get(url);

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys("Sampo cap Test");

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys("61");

        driver.findElement(By.tagName("form")).submit();

        assertTrue(driver.getCurrentUrl().contains("/product/list"));

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Sampo cap Test"));
        assertTrue(pageSource.contains("61"));
    }
}
