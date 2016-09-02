package core;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomConditions {

    public static ExpectedCondition<List<WebElement>> textsOf(final List<WebElement> elements, final String... texts) {

        if (texts.length == 0) {
            throw new IllegalArgumentException("Array of expected texts is empty.");
        }
        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {
            List<String> actualTexts;

            public List<WebElement> apply(WebDriver driver) {

                actualTexts = getTexts(elements);
                if (actualTexts.size() != texts.length) {
                    return null;
                }
                for (int i = 0; i < texts.length; i++) {
                    if (!actualTexts.get(i).contains(texts[i])) {
                        return null;
                    }
                }
                return elements;
            }

            public String toString() {
                return String.format("Expected texts: %s, Actual texts: %s", Arrays.toString(texts), String.join(", ", actualTexts));
            }
        });
    }

    public static ExpectedCondition<WebElement> listNthElementHasText(final List<WebElement> elements, final int index, final String text) {

            return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {
                private String actualText;
            public WebElement apply(WebDriver driver) {
                WebElement element = elements.get(index);
                    actualText =  element.getText();
                    if (actualText.contains(text));
                    { return element;}
            }

            public String toString() {
                return String.format("%dth element \nof %s list \nto have text: %s\n", index + 1, actualText, text);
            }
        });
    }

    public static <V> ExpectedCondition<V> elementExceptionsCatcher(final ExpectedCondition<V> condition) {
        return new ExpectedCondition<V>() {
            public V apply(WebDriver input) {
                try {
                    return condition.apply(input);
                } catch (StaleElementReferenceException | ElementNotVisibleException | IndexOutOfBoundsException e) {
                    return null;
            }}

            public String toString() {
                return condition.toString();
            }
        };
    }

    public static List getTexts(List<WebElement> elements) {
        List<String> actualTexts;
        actualTexts = new ArrayList<String>();
        for (WebElement element : elements) {
            actualTexts.add(element.getText());
        }
        return actualTexts;
    }
}
