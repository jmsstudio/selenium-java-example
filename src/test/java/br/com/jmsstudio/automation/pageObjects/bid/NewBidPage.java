package br.com.jmsstudio.automation.pageObjects.bid;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@AllArgsConstructor
public class NewBidPage {

    private WebDriver driver;

    public void create(final String name, final Double initialValue, final String username, final boolean used) {
        final WebElement nameField = driver.findElement(By.name("leilao.nome"));
        final WebElement initialValueField = driver.findElement(By.name("leilao.valorInicial"));
        final Select select = new Select(driver.findElement(By.name("leilao.usuario.id")));
        final WebElement cbIsUsed = driver.findElement(By.name("leilao.usado"));

        final WebElement saveButton = driver.findElement(By.xpath("//form//button"));

        nameField.sendKeys(name);
        initialValueField.sendKeys(initialValue.toString());
        select.selectByVisibleText(username);

        if (used) {
            cbIsUsed.click();
        }


        saveButton.click();
    }

}
