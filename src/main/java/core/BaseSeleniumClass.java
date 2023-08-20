package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

abstract public class BaseSeleniumClass {
    protected WebDriver driver;

    @Before
    public void settingInitialization(){
        WebDriverManager.chromedriver().setup();
        //ChromeOptions opt = new ChromeOptions();
        driver = new ChromeDriver();
      //  opt.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver.manage().window().maximize(); // Во избежании случайного открытия сайта в маленьком разрешении
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        BaseSeleniumPage.setDriver(driver);
    }


    @After
    public void ending(){
        driver.close(); // Выключение драйвера
        driver.quit(); // Закрытие браузера
    }
}
