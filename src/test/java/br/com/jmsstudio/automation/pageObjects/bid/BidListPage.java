package br.com.jmsstudio.automation.pageObjects.bid;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class BidListPage {

    private WebDriver driver;

    public void open() {
        driver.get("http://localhost:8080/leiloes");
    }

    public NewBidPage goToNew() {
        driver.findElement(By.linkText("Novo Leilão")).click();
        return new NewBidPage(this.driver);
    }

    public boolean assertBidIsOnList(final String name, final Double initialValue, final String username, final boolean used) {
        return driver.getPageSource().contains(name) && driver.getPageSource().contains(initialValue.toString()) &&
                driver.getPageSource().contains(username) && driver.getPageSource().contains(used ? "Sim" : "Não");
    }



}
