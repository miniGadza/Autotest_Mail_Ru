package project;

import core.BaseSeleniumPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import readProperties.ConfigProvider;
import java.time.Duration;

public class FirstPage extends BaseSeleniumPage {
    @FindBy (css = "button[data-testid='enter-mail-primary']" )
    private WebElement firstEnterButton;
    private Object ElementNotInteractableException;

    // Страница со списком новостей
    public FirstPage(){
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    public MainPage loginInEmail(String email, String password){
// Открытие окна авторизации
        firstEnterButton.click();
        driver.switchTo().defaultContent();

        try{
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@class='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(frame1);
        } catch (NoSuchElementException e){
            Assertions.fail("Нет окна авторизации");
        }

// Заполнение данных в окне авторизации
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

        try {
            WebElement testForLogin = driver.findElement(By.cssSelector("a[title='Написать письмо']"));
        } catch (NoSuchElementException e) {
            Assertions.fail("Неверные данные для авторизации");
        }
        return new MainPage();
    }
}
