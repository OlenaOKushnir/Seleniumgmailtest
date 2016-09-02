package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static core.CustomConditions.listNthElementHasText;
import static core.CustomConditions.textsOf;

public class GmailPage extends BasePage {

    @FindBy(css = "[role=main] .zA")
    private List<WebElement> mails;

    public void visit() {
        open("http://gmail.com");
    }

    public GmailPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {

        $("#Email").sendKeys(email + Keys.ENTER);
        $("#Passwd").sendKeys(password + Keys.ENTER);
    }

    public void newMail(String to, String subject) {
        $(byText("COMPOSE")).click();
        $(By.name("to")).sendKeys(to + Keys.ENTER);
        $(By.name("subjectbox")).sendKeys(subject + Keys.ENTER);
        $(byText("Send")).click();
    }

    public void assertMail(String text) {
        assertThat(listNthElementHasText(mails, 0, text));
    }

    public void assertMails(String... texts) {
        assertThat(textsOf(mails, texts));
    }

    public void switchToSent() {
        $(byExactTitle("Sent Mail")).click();
    }

    public void switchToInbox() {
        $(byTitle("Inbox")).click();
    }

    public void searchByText(String text) {
        $(By.name("q")).sendKeys(text + Keys.ENTER);
    }

    public void refresh() {
        $(".asf").click();
    }
}