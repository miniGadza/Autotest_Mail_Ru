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

// Страница со списком всех писем
    public MainPage(){
        PageFactory.initElements(driver, this);
        driver.switchTo().defaultContent();
    }

// Удаление существующей папки для писем (Если не найдено, то тест не падает)
    public MainPage deleteFolderOfMails (String name) throws InterruptedException {
        try{
        Actions actions = new Actions(driver);
        WebElement checkInputNameFolder = driver.findElement(By.cssSelector("a[title='" + name +", нет писем']"));
        actions.contextClick(checkInputNameFolder).perform();
        WebElement deleteFolderButton = driver.findElement(By.cssSelector("span[class='list-item__ico list-item__ico_delete']"));
        deleteFolderButton.click();
        WebElement deleteAcceptButton = driver.findElement(By.cssSelector("span[class='button2 button2_base button2_primary button2_fluid button2_hover-support']"));
        deleteAcceptButton.click();
        } catch (NoSuchElementException e){
            return this;
        }
        return this;
    }

// Создание новой папки для писем
    public MainPage createNewFolderOfMails (String name) throws InterruptedException {
        Thread.sleep(1000);
        newFolderButton.click();
        WebElement inputNameFolder = driver.findElement(By.cssSelector("input[name='name']"));
        inputNameFolder.click();
        inputNameFolder.sendKeys(name);

        WebElement createNewFolderButton = driver.findElement(By.cssSelector("button[type='submit']"));
        createNewFolderButton.click();

        try{
            WebElement checkCreatedFolder = driver.findElement(By.cssSelector("a[title='" + name +", нет писем']"));
        } catch (NoSuchElementException e){
            Assertions.fail("Папка с таким названием уже существует");
        }
        return this;
    }

// Новое письмо
    public CreatingMessagePage createNewMessage() throws InterruptedException {
        Thread.sleep(2000);
        newMailButton.click();
        return new CreatingMessagePage();
    }

// Предварительная очистка корзины с удалёнными письмами (для дальнейшей проверки удаления письма)
    public MainPage deleteMessagesFromTrashbin(){
        try{
            WebElement deleteFromTrashbinButton = driver.findElement(By.xpath("//span[@class='button2 button2_badge button2_hover-support']"));
            deleteFromTrashbinButton.click();
            WebElement deleteConfirmFromTrashbin = driver.findElement(By.cssSelector("span[class='button2 button2_base button2_primary button2_hover-support']"));
            deleteConfirmFromTrashbin.click();
            }
        catch (NoSuchElementException e){
            return this;
        }
        return this;
    }

// Удаление письма
    public MainPage deleteMessage() throws InterruptedException {
        Thread.sleep(60000); // Письмо отправляется самому себе, нужно время чтобы пришло
        try {
        WebElement selectMessageForDelete = driver.findElement(By.xpath("//div[@title='Выделить']"));
        selectMessageForDelete.click();

        WebElement deleteMessageButton = driver.findElement(By.xpath("//div[@class='portal-menu-element portal-menu-element_remove portal-menu-element_expanded portal-menu-element_not-touch']"));
        deleteMessageButton.click();

        WebElement confirmDeleteMessage = driver.findElement(By.xpath("//span[@class='button2 button2_base button2_primary button2_fluid button2_hover-support']"));
        confirmDeleteMessage.click();
        } catch (NoSuchElementException e){
            Assertions.fail("Письмо не отправилось (или не успело прийти)");
        }

        Thread.sleep(2000);
        try{
            WebElement deleteFromTrashbinButton = driver.findElement(By.xpath("//span[@class='button2 button2_badge button2_hover-support']"));
        } catch (NoSuchElementException e){
            Assertions.fail("Письмо не удалилось, корзина пустая");
        }
        return this;
    }

// Выход с аккаунта
    public FirstPage logOut(){
        WebElement profileIcon = driver.findElement(By.xpath("//span[@class='ph-project__user-icon svelte-bp6up3']"));
        profileIcon.click();

        WebElement exitButton = driver.findElement(By.xpath("//div[@data-testid='whiteline-account-exit']"));
        exitButton.click();

        try{
            WebElement testForLogOut = driver.findElement(By.cssSelector("button[data-testid='enter-mail-primary']"));
        } catch (NoSuchElementException e){
            Assertions.fail("Выход не совершён");
        }
        return new FirstPage();
    }
}
