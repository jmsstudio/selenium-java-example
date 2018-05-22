package br.com.jmsstudio.automation.bid;

import br.com.jmsstudio.automation.config.EnvironmentManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BidSystemTest {

    @BeforeEach
    public void setupBrowser() {
        EnvironmentManager.init();
    }

    @AfterAll
    public static void tearDown() {
        EnvironmentManager.shutDown();
    }

    @Test
    public void shouldCreateANewUser() {
        WebDriver driver = EnvironmentManager.getDriver();

        driver.get("http://localhost:8080/usuarios/new");

        final String userName = "John Armless";
        final String userEmail = "john.armless@johnarmless.com";

        final WebElement nameField = driver.findElement(By.name("usuario.nome"));
        final WebElement emailField = driver.findElement(By.name("usuario.email"));
        final WebElement saveButton = driver.findElement(By.id("btnSalvar"));

        nameField.sendKeys(userName);
        emailField.sendKeys(userEmail);
        saveButton.click();

        new WebDriverWait(driver, 5000).until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"content\"]/h1"), "Todos os Usuários"));

        assertTrue(driver.getPageSource().contains(userName));
        assertTrue(driver.getPageSource().contains(userEmail));
    }


}
