package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.Configuration.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class ConciseAPI {

    public abstract  WebDriver getWebDriver();

    public void open(String url) {
        getWebDriver().get(url);
    }

    public <V> V assertThat(ExpectedCondition<V> condition, long timeout) {
        return new WebDriverWait(getWebDriver(), timeout).until(condition);
    }

    public <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    public WebElement $(By locator) {
        return assertThat(visibilityOfElementLocated(locator), timeout);
    }

    public WebElement $(String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    public By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static By byText(String elementText) {
        return By.xpath( ".//*/text()[normalize-space(.) = " + Quotes.escape(elementText) + "]/parent::*");
    }

    public static By byAttributeExactText(String attributeName, String attributeValue) {
        return By.xpath(".//*[@" + attributeName + '=' + Quotes.escape(attributeValue) + ']');
    }

    public static By byAttributeText(String attributeName, String attributeValue) {
        return By.xpath(".//*[contains(@" + attributeName + "," + Quotes.escape(attributeValue) + ")]");
    }

    public static By byTitle(String title) {
        return byAttributeText("title", title);
    }

    public static By byExactTitle(String title) {
        return byAttributeExactText("title", title);
    }
}




