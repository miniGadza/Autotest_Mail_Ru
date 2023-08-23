package project;

import core.BaseSeleniumClass;
import org.junit.Test;
import readProperties.ConfigProvider;

public class ProjectTest extends BaseSeleniumClass {

    @Test
    public void testing() throws InterruptedException {
       FirstPage firstPage = new FirstPage();
       firstPage
               .loginInEmail(ConfigProvider.USER1_LOGIN, ConfigProvider.USER1_PASSWORD)
               .deleteFolderOfMails()
               .createNewFolderOfMails()
               .deleteMessagesFromTrashbin()
               .deleteMessage();
    }
}
