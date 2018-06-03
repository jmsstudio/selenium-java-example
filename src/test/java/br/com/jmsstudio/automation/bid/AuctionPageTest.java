package br.com.jmsstudio.automation.bid;

import br.com.jmsstudio.automation.config.EnvironmentManager;
import br.com.jmsstudio.automation.creator.ScenarioDataCreator;
import br.com.jmsstudio.automation.pageObjects.auction.AuctionDetailPage;
import br.com.jmsstudio.automation.pageObjects.auction.AuctionListPage;
import br.com.jmsstudio.automation.pageObjects.auction.NewAuctionPage;
import br.com.jmsstudio.automation.pageObjects.user.NewUserPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuctionPageTest extends AbstractPageTest {

    private AuctionListPage auctionListPage;

    @BeforeAll
    public static void setupBrowser() {
        EnvironmentManager.init();
    }

    @BeforeEach
    public void init() {
        final WebDriver driver = EnvironmentManager.getDriver();

        this.auctionListPage = new AuctionListPage(EnvironmentManager.getDriver());
        clearData(driver);
    }

    @AfterAll
    public static void tearDown() {
        clearData(EnvironmentManager.getDriver());
        EnvironmentManager.shutDown();
    }

    @Test
    public void shouldCreateANewAuctionSuccessfully() {
        final String userName = "John Doe";
        final NewUserPage newUserPage = new NewUserPage(EnvironmentManager.getDriver());
        newUserPage.open();
        newUserPage.create(userName, "johndoe@email.com");

        final String auctionName = "Stove";
        final Double initialValue = 500D;
        final boolean isUsed = true;

        this.auctionListPage.open();
        NewAuctionPage newAuctionPage = this.auctionListPage.goToNew();

        newAuctionPage.create(auctionName, initialValue, userName, isUsed);

        assertTrue(this.auctionListPage.assertAuctionIsOnList(auctionName, initialValue, userName, isUsed));
    }

    @Test
    public void shouldCreateANewEmptyAuctionAndShowErrorMessage() {
        final String userName = "John Doe";
        final NewUserPage newUserPage = new NewUserPage(EnvironmentManager.getDriver());
        newUserPage.open();
        newUserPage.create(userName, "johndoe@email.com");

        this.auctionListPage.open();
        NewAuctionPage newAuctionPage = this.auctionListPage.goToNew();

        newAuctionPage.create("", null, userName, false);

        assertTrue(newAuctionPage.isNomeObrigatorioMessagePresent());
        assertTrue(newAuctionPage.isInvalidValueMessagePresent());
    }

    @Test
    public void shouldCreateBidInAnAuction() {
        final String auctionOwnerUserName = "John Doe";
        final String userName = "Mary Doe";

        final String auctionName = "Freezer";
        final Double initialValue = 380D;
        final boolean isUsed = true;

        new ScenarioDataCreator(EnvironmentManager.getDriver())
                .createUser(auctionOwnerUserName, "johndoe@email.com")
                .createUser(userName, "marydoe@email.com")
                .createAuction(auctionName, initialValue, auctionOwnerUserName, isUsed);

        assertTrue(this.auctionListPage.assertAuctionIsOnList(auctionName, initialValue, auctionOwnerUserName, isUsed));

        //bid
        final AuctionDetailPage auctionDetailPage = this.auctionListPage.goToDetails(auctionName);
        auctionDetailPage.bid(userName, 400D);

        assertTrue(auctionDetailPage.isBidInTheList(userName, 400D));
    }
}
