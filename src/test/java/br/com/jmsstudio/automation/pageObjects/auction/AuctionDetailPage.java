package br.com.jmsstudio.automation.pageObjects.auction;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@AllArgsConstructor
public class AuctionDetailPage {

    private WebDriver driver;

    public void bid(String user, Double value) {
        final Select nameField = new Select(driver.findElement(By.name("lance.usuario.id")));
        final WebElement valueField = driver.findElement(By.name("lance.valor"));
        final WebElement bidButton = driver.findElement(By.id("btnDarLance"));

        nameField.selectByVisibleText(user);
        valueField.sendKeys(value.toString());
        bidButton.click();
    }

    public boolean isBidInTheList(String user, Double value) {
        //waits for element to be in the list
        WebElement table = driver.findElement(By.id("lancesDados"));
        boolean isUserInTheList = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElement(table, user));

        return isUserInTheList && table.findElements(By.xpath("//tr/td[text()='" + value.toString() +  "']")).size() == 1;

    }
}
