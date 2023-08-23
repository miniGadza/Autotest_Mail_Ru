package project;

import core.BaseSeleniumPage;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import readProperties.ConfigProvider;
import java.time.Duration;

public class FirstPage extends BaseSeleniumPage {
    @FindBy (css = "button[data-testid='enter-mail-primary']" )
    private WebElement firstEnterButton;


    public FirstPage(){
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    public MainPage loginInEmail(String email, String password){

        firstEnterButton.click();

        driver.switchTo().defaultContent(); // Проверка, что изначально фокус не на iframe

        WebElement frame1 = driver.findElement(By.xpath("//iframe[@class='ag-popup__frame__layout__iframe']")); // Добавить try catch
        driver.switchTo().frame(frame1);


        WebElement firstLoginInput = driver.findElement(By.xpath("//input[@class='input-0-2-71']"));
        firstLoginInput.click();
        firstLoginInput.sendKeys(email);

        WebElement buttonToEnterPassword = driver.findElement(By.xpath("//button[@data-test-id='next-button']"));
        buttonToEnterPassword.click();

        WebElement firstPasswordInput = driver.findElement(By.xpath("//input[@class='input-0-2-71 withIcon-0-2-72']"));
        firstPasswordInput.click();
        firstPasswordInput.sendKeys(password);

        WebElement buttonToLogin = driver.findElement(By.xpath("//button[@data-test-id='submit-button']"));
        buttonToLogin.click();
        return new MainPage();
    }
}
