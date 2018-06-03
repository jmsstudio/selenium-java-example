package br.com.jmsstudio.automation.creator;

import br.com.jmsstudio.automation.pageObjects.auction.AuctionListPage;
import br.com.jmsstudio.automation.pageObjects.auction.NewAuctionPage;
import br.com.jmsstudio.automation.pageObjects.user.NewUserPage;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class ScenarioDataCreator {

    private WebDriver driver;

    public ScenarioDataCreator createUser(final String username, final String email) {
        final NewUserPage newUserPage = new NewUserPage(driver);
        newUserPage.open();
        newUserPage.create(username, email);

        return this;
    }

    public ScenarioDataCreator createAuction(String auctionName, Double initialValue, String auctionUserName, boolean isUsed) {
        final AuctionListPage auctionListPage = new AuctionListPage(driver);
        auctionListPage.open();
        NewAuctionPage newAuctionPage = auctionListPage.goToNew();

        newAuctionPage.create(auctionName, initialValue, auctionUserName, isUsed);
        return this;
    }
}
