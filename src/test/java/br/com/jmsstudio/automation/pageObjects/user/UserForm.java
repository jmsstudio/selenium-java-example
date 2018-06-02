package br.com.jmsstudio.automation.pageObjects.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public abstract class UserForm {

    protected abstract WebDriver getDriver();

    private Boolean isMessagePresent(final String message) {
        return Arrays.stream(getDriver().findElement(By.xpath("//*[@id=\"content\"]")).getText().split("\n"))
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
