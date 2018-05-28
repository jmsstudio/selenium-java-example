package br.com.jmsstudio.automation.pageObjects.user;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

@AllArgsConstructor
public class NewUserPage {

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

    private Boolean isMessagePresent(final String message) {
        return Arrays.stream(driver.findElement(By.xpath("//*[@id=\"content\"]")).getText().split("\n"))
                .map(message::equals)
                .filter(Boolean::booleanValue).findFirst().orElse(false);
    }

    public boolean isNomeObrigatorioMessagePresent() {
        return isMessagePresent("Nome obrigatorio!");
    }

    public boolean isEmailObrigatorioMessagePresent() {
        return isMessagePresent("E-mail obrigatorio!");
    }

}
