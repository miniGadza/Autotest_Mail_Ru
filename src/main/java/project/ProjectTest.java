package project;

import core.BaseSeleniumClass;
import org.junit.Test;
import readProperties.ConfigProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTest extends BaseSeleniumClass {

// Основной тестовый класс
    @Test
    public void testing() throws InterruptedException {
       FirstPage firstPage = new FirstPage();
       String UniqueTestValue = "Test" + new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
       firstPage
               .loginInEmail(ConfigProvider.USER1_LOGIN, ConfigProvider.USER1_PASSWORD)
               .deleteFolderOfMails("TestingFolder")
               .createNewFolderOfMails(UniqueTestValue)
               .deleteMessagesFromTrashbin()
               .createNewMessage()
               .fillingNewMessage(UniqueTestValue)
               .closeNewMessageWindow()
               .deleteMessage()
               .logOut();
    }
}
