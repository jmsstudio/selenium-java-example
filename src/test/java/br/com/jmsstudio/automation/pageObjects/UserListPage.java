package br.com.jmsstudio.automation.pageObjects;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@AllArgsConstructor
public class UserListPage {

    private WebDriver driver;

    public void open() {
        driver.get("http://localhost:8080/usuarios");
    }

    public NewUserPage goToNew() {
        driver.findElement(By.linkText("Novo Usuário")).click();
        return new NewUserPage(this.driver);
    }

    public boolean assertUserIsOnList(String userName, String userEmail) {
        return driver.getPageSource().contains(userName) && driver.getPageSource().contains(userEmail);
    }

    public void deleteLine(final int linePosition) {
        driver.findElement(By.xpath("(//*[@id=\"content\"]/table//button)[" + linePosition + "]")).click();
        driver.switchTo().alert().accept();
    }

    public Integer getTotalTableRows() throws InterruptedException {
        new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe("http://localhost:8080/usuarios"));
        Thread.sleep(100);
        return driver.findElements(By.xpath("//table/tbody/tr")).size();
    }
}
