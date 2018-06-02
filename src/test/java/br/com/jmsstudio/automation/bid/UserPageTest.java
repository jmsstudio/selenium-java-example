package br.com.jmsstudio.automation.bid;

import br.com.jmsstudio.automation.config.EnvironmentManager;
import br.com.jmsstudio.automation.pageObjects.user.NewUserPage;
import br.com.jmsstudio.automation.pageObjects.user.UserListPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserPageTest {

    private UserListPage userListPage;

    @BeforeAll
    public static void setupBrowser() {
        EnvironmentManager.init();
    }

    @BeforeEach
    public void init() {
        final WebDriver driver = EnvironmentManager.getDriver();

        this.userListPage = new UserListPage(driver);
        driver.get("http://localhost:8080/apenas-teste/limpa");
    }

    @AfterAll
    public static void tearDown() {
        EnvironmentManager.shutDown();
    }

    @Test
    public void shouldCreateANewUser() {
        final String userName = "John Armless";
        final String userEmail = "john.armless@johnarmless.com";

        this.userListPage.open();
        this.userListPage.goToNew().create(userName, userEmail);

        assertTrue(this.userListPage.assertUserIsOnList(userName, userEmail));
    }

    @Test
    public void shouldShowErrorMessageWhenTryingToCreateAUserWithoutName() {
        final String userName = "";
        final String userEmail = "john.armless@johnarmless.com";

        this.userListPage.open();
        NewUserPage newUserPage = this.userListPage.goToNew();
        newUserPage.create(userName, userEmail);

        assertTrue(newUserPage.isNomeObrigatorioMessagePresent());
    }

    @Test
    public void shouldShowErrorMessageWhenTryingToCreateAUserWithoutNameAndEmail() {
        this.userListPage.open();
        NewUserPage newUserPage = this.userListPage.goToNew();
        newUserPage.create("", "");

        assertTrue(newUserPage.isNomeObrigatorioMessagePresent());
        assertTrue(newUserPage.isEmailObrigatorioMessagePresent());
    }

    @Test
    public void shouldDeleteAnUser() throws InterruptedException {
        final String userName = "John Armless";
        final String userEmail = "john.armless@johnarmless.com";

        this.userListPage.open();
        this.userListPage.goToNew().create(userName, userEmail);

        assertTrue(this.userListPage.assertUserIsOnList(userName, userEmail));

        Integer totalTableRows = this.userListPage.getTotalTableRows() -1;

        this.userListPage.deleteLine(1);

        assertEquals(totalTableRows -1, this.userListPage.getTotalTableRows() -1 );
    }

    @Test
    public void shouldUpdateAnUser() {
        final String userName = "John Armless";
        final String userEmail = "john.armless@johnarmless.com";

        this.userListPage.open();
        this.userListPage.goToNew().create(userName, userEmail);

        assertTrue(this.userListPage.assertUserIsOnList(userName, userEmail));

        final String newUserName = "Mary Poppins";
        final String newUserEmail = "mary@popins.com";

        this.userListPage.goToEdit().changeName(newUserName).changeEmail(newUserEmail).save();

        assertTrue(this.userListPage.assertUserIsOnList(newUserName, newUserEmail));
        assertFalse(this.userListPage.assertUserIsOnList(userName, userEmail));
    }


}
