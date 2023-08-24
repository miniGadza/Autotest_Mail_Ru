package project;

import core.BaseSeleniumPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

public class CreatingMessagePage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@tabindex='100']")
    public WebElement emailToField;

    @FindBy(xpath = "//input[@name='Subject']")
    public WebElement subjectField;

    @FindBy(xpath = "//div[@contenteditable='true']/div")
    public WebElement messageBody;

    @FindBy(xpath = "//button[@data-test-id='send']")
    public WebElement sendButton;

    public CreatingMessagePage(){
        PageFactory.initElements(driver, this);
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://e.mail.ru/inbox/"));
    }

    public CreatingMessagePage fillingNewMessage(String UniqueValue){
        emailToField.sendKeys(ConfigProvider.USER1_LOGIN);
        subjectField.sendKeys("TestSubject" + UniqueValue);
        messageBody.sendKeys("TestMessageBody" + UniqueValue);
        sendButton.click();
        return this;
    }

    public MainPage closeNewMessageWindow(){
        WebElement closeButton = driver.findElement(By.cssSelector("span[title='Закрыть']"));
        closeButton.click();
        return new MainPage();
    }

}
