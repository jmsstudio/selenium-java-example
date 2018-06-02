package br.com.jmsstudio.automation.pageObjects.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
public class EditUserPage extends UserForm {

    @Getter
    private WebDriver driver;

    public void open(Integer id) {
        driver.get("http://localhost:8080/usuarios/" + id + "/edit");
    }

    public void edit(final String username, final String email) {
        final WebElement nameField = driver.findElement(By.name("usuario.nome"));
        final WebElement emailField = driver.findElement(By.name("usuario.email"));
        final WebElement saveButton = driver.findElement(By.id("btnSalvar"));

        nameField.sendKeys(username);
        emailField.sendKeys(email);
        saveButton.click();
    }

    public EditUserPage changeName(String newUserName) {
        final WebElement nameField = driver.findElement(By.name("usuario.nome"));

        nameField.clear();
        nameField.sendKeys(newUserName);
        return this;
    }

    public EditUserPage changeEmail(String newUserEmail) {
        final WebElement emailField = driver.findElement(By.name("usuario.email"));

        emailField.clear();
        emailField.sendKeys(newUserEmail);
        return this;
    }

    public void save() {
        final WebElement saveButton = driver.findElement(By.id("btnSalvar"));
        saveButton.click();
    }
}
