package qa.guru.owner.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    //private final WebDriverConfigOld config; //для настройки без библиотеки owner
    private final WebDriverConfig config;

    public WebDriverProvider() {
        //this.config = new WebDriverConfigOld(); //для настройки без библиотеки owner
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        switch (config.getBrowser()) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }

            case EDGE: {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            default: {
                throw new RuntimeException("No such driver");
            }
        }
    }

}