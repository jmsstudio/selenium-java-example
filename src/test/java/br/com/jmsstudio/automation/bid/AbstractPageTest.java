package br.com.jmsstudio.automation.bid;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPageTest {

    protected static void clearData(WebDriver driver) {
        driver.get("http://localhost:8080/apenas-teste/limpa");
    }

}
