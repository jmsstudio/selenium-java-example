package br.com.jmsstudio.automation.pageObjects.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
public class NewUserPage extends AbstractUserForm {

    @Getter
    private WebDriver driver;

    public void open() {
        driver.get("http://localhost:8080/usuarios/new");
    }

    public void create(final String username, final String email) {
        final WebElement nameField = driver.findElement(By.name("usuario.nome"));
        final WebElement emailField = driver.findElement(By.name("usuario.email"));
        final WebElement saveButton = driver.findElement(By.id("btnSalvar"));

        nameField.sendKeys(username);
        emailField.sendKeys(email);
        saveButton.click();
    }

}
