package br.com.jmsstudio.automation.config;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

public class EnvironmentManager {

    @Getter
    private static WebDriver driver;

    public static void init() {
        if (driver == null) {
            driver = new SeleniumConfig().getDriver();
        }
    }

    public static void init(TestBrowser browser) {
        if (driver == null) {
            driver = new SeleniumConfig(browser).getDriver();
        }
    }

    public static void shutDown() {
        driver.quit();
    }


}
