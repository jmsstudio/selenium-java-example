package br.com.jmsstudio.automation.pageObjects.auction;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class AuctionListPage {

    private WebDriver driver;

    public void open() {
        driver.get("http://localhost:8080/leiloes");
    }

    public NewAuctionPage goToNew() {
        driver.findElement(By.linkText("Novo Leilão")).click();
        return new NewAuctionPage(this.driver);
    }

    public boolean assertAuctionIsOnList(final String name, final Double initialValue, final String username, final boolean used) {
        return driver.getPageSource().contains(name) && driver.getPageSource().contains(initialValue.toString()) &&
                driver.getPageSource().contains(username) && driver.getPageSource().contains(used ? "Sim" : "Não");
    }

    public AuctionDetailPage goToDetails(String auctionName) {
        driver.findElement(By.xpath("//table//tr[td[contains(text(),'" + auctionName +"')]]/td[last()]/a[text()='exibir']")).click();
        return new AuctionDetailPage(driver);
    }
}
