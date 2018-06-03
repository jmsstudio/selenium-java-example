package br.com.jmsstudio.automation.pageObjects.auction;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

@AllArgsConstructor
public class NewAuctionPage {

    private WebDriver driver;

    public void create(final String name, final Double initialValue, final String username, final boolean used) {
        final WebElement nameField = driver.findElement(By.name("leilao.nome"));
        final WebElement initialValueField = driver.findElement(By.name("leilao.valorInicial"));
        final Select select = new Select(driver.findElement(By.name("leilao.usuario.id")));
        final WebElement cbIsUsed = driver.findElement(By.name("leilao.usado"));

        final WebElement saveButton = driver.findElement(By.xpath("//form//button"));

        nameField.sendKeys(name);

        if (initialValue != null) {
            initialValueField.sendKeys(initialValue.toString());
        }
        select.selectByVisibleText(username);

        if (used) {
            cbIsUsed.click();
        }


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

    public boolean isInvalidValueMessagePresent() {
        return isMessagePresent("Valor inicial deve ser maior que zero!");
    }


}
