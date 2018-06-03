package br.com.jmsstudio.automation.bid;

import br.com.jmsstudio.automation.config.EnvironmentManager;
import br.com.jmsstudio.automation.pageObjects.bid.BidListPage;
import br.com.jmsstudio.automation.pageObjects.bid.NewBidPage;
import br.com.jmsstudio.automation.pageObjects.user.NewUserPage;
import br.com.jmsstudio.automation.pageObjects.user.UserListPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BidPageTest extends AbstractPageTest {

    private BidListPage bidListPage;

    @BeforeAll
    public static void setupBrowser() {
        EnvironmentManager.init();
    }

    @BeforeEach
    public void init() {
        final WebDriver driver = EnvironmentManager.getDriver();

        this.bidListPage = new BidListPage(EnvironmentManager.getDriver());
        clearData(driver);
    }

    @AfterAll
    public static void tearDown() {
        clearData(EnvironmentManager.getDriver());
        EnvironmentManager.shutDown();
    }

    @Test
    public void shouldCreateANewBidSuccessfully() {
        final String userName = "John Doe";
        final NewUserPage newUserPage = new NewUserPage(EnvironmentManager.getDriver());
        newUserPage.open();
        newUserPage.create(userName, "johndoe@email.com");

        final String bidName = "Stove";
        final Double initialValue = 500D;
        final boolean isUsed = true;

        this.bidListPage.open();
        NewBidPage newBidPage = this.bidListPage.goToNew();

        newBidPage.create(bidName, initialValue, userName, isUsed);

        assertTrue(this.bidListPage.assertBidIsOnList(bidName, initialValue, userName, isUsed));
    }

    @Test
    public void shouldCreateANewEmptyBidAndShowErrorMessage() {
        final String userName = "John Doe";
        final NewUserPage newUserPage = new NewUserPage(EnvironmentManager.getDriver());
        newUserPage.open();
        newUserPage.create(userName, "johndoe@email.com");

        this.bidListPage.open();
        NewBidPage newBidPage = this.bidListPage.goToNew();

        newBidPage.create("", null, userName, false);

        assertTrue(newBidPage.isNomeObrigatorioMessagePresent());
        assertTrue(newBidPage.isInvalidValueMessagePresent());
    }

}
