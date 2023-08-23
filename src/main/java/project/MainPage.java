package project;

import core.BaseSeleniumPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {

    @FindBy(css = "a[title='Написать письмо']" )
    private WebElement newMailButton;

    @FindBy(xpath = "//*[text() = 'Новая папка']")
    private WebElement newFolderButton;

    public MainPage(){
        PageFactory.initElements(driver, this);
        driver.switchTo().defaultContent();
    }

    public MainPage deleteFolderOfMails () throws InterruptedException {
        try{
        Actions actions = new Actions(driver);
        WebElement checkInputNameFolder = driver.findElement(By.cssSelector("a[title='Test1, нет писем']"));
        actions.contextClick(checkInputNameFolder).perform();
        WebElement deleteFolderButton = driver.findElement(By.cssSelector("span[class='list-item__ico list-item__ico_delete']"));
        deleteFolderButton.click();
        WebElement deleteAcceptButton = driver.findElement(By.cssSelector("span[class='button2 button2_base button2_primary button2_fluid button2_hover-support']"));
        deleteAcceptButton.click();}
        catch (NoSuchElementException e){
            return this;
        }
        return this;
    }

    public MainPage createNewFolderOfMails () throws InterruptedException {

        Thread.sleep(1000);
        newFolderButton.click();
        WebElement inputNameFolder = driver.findElement(By.cssSelector("input[name='name']"));
        inputNameFolder.click();
        inputNameFolder.sendKeys("Test1");

        WebElement createNewFolderButton = driver.findElement(By.cssSelector("button[type='submit']"));
        createNewFolderButton.click();
        return this;
    }

    public MainPage deleteMessagesFromTrashbin(){
        try{
            Actions actions = new Actions(driver);
            WebElement deleteFromTrashbinButton = driver.findElement(By.xpath("//*[@id='sideBarContent']/div/nav/a[12]/div/div[3]/span"));
            deleteFromTrashbinButton.click();
            WebElement deleteConfirmFromTrashbin = driver.findElement(By.cssSelector("span[class='button2 button2_base button2_primary button2_hover-support']"));
            deleteConfirmFromTrashbin.click();
            }
        catch (NoSuchElementException e){
            return this;
        }
        return this;
    }

    public MainPage deleteMessage() throws InterruptedException {
        Thread.sleep(2000);

        return this;
    }


}
