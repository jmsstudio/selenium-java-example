package br.com.jmsstudio.automation;

import br.com.jmsstudio.automation.config.EnvironmentManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {

    @BeforeEach
    public void setupBrowser() {
        EnvironmentManager.init();
    }

    @Test
    public void googleSearch() {
        WebDriver driver = EnvironmentManager.getDriver();

        driver.get("http://www.google.com.br");

        WebElement element = driver.findElement(By.name("q"));

        element.sendKeys("Google");
        element.submit();

        assertEquals("Google", driver.findElement(By.xpath("//*[@id=\"search\"]//div[@class=\"g\"]//h3")).getText());

    }

    @Test
    public void bingSearch() throws InterruptedException {
        WebDriver driver = EnvironmentManager.getDriver();

        driver.get("http://www.bing.com");

        WebElement element = driver.findElement(By.name("q"));

        element.sendKeys("Google");
        element.submit();

        assertEquals("Google", driver.findElement(By.xpath("//*[@id=\"b_results\"]//*[@class=' b_topTitle']")).getText());

    }


    @AfterAll
    public static void tearDown() {
        EnvironmentManager.shutDown();
    }

}
