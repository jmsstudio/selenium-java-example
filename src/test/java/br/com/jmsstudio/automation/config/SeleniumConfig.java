package br.com.jmsstudio.automation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumConfig {

    @Getter
    private WebDriver driver;

    private TestBrowser browser = TestBrowser.FIREFOX;

    public SeleniumConfig() {
        this.setupWebDriver(this.browser);
    }

    public SeleniumConfig(TestBrowser browser) {
        this.browser = browser;

        this.setupWebDriver(this.browser);
    }


    public void setupWebDriver(TestBrowser browser) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;

            default:
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
        }


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
