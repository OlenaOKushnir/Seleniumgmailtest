import core.Configuration;

import org.junit.BeforeClass;
import org.junit.Test;
import pages.GmailPage;

import java.util.Date;

import static Config.TestData.EMAIL;
import static Config.TestData.PASSWORD;

public class GmailTest extends BaseTest {

    @BeforeClass
    public static void timeout() {
        Configuration.timeout = 20;
    }
    public static GmailPage page = new GmailPage(driver);

    @Test
    public void testLoginSendAndSearchMail() {

        String subject = "my new mail " + new Date().toString();

        page.visit();

        page.login(EMAIL, PASSWORD);
        page.newMail(EMAIL, subject);

        page.refresh();

        page.assertMail(subject);

        page.switchToSent();
        page.assertMail(subject);

        page.switchToInbox();

        page.searchByText(subject);
        page.assertMails(subject);
    }
}

