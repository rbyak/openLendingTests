package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A utility class that encapsulates all scenarios wherein waiting is needed. */
public final class WaitsUtil {

    protected static final Logger LOG = LoggerFactory.getLogger(WaitsUtil.class);

    private final Waits waits;
    private final WebDriver driver;

    public WaitsUtil(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public WebElement visibility(WebElement webElement) {
        return visibility(webElement, waits.smallWait());
    }

    public WebElement visibility(By locator) {
        return visibility(locator, waits.smallWait());
    }

    public WebElement visibility(By locator, WebDriverWait webDriverWait) {
        return webDriverWait.until(visibilityOfElementLocated(locator));
    }

    public WebElement visibility(WebElement webElement, WebDriverWait webDriverWait) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public List<WebElement> visibilityOf(WebElement... webElements) throws Exception {
        List<WebElement> elements = new ArrayList<>();

        try {
            Stream.of(webElements).forEach(e -> elements.add(visibility(e)));

        } catch (NoSuchElementException | TimeoutException exception) {
            LOG.info("Browser console log.");
            throw new Exception(exception);
        }
        return elements;
    }

    public boolean titleToBe(String title) {
        return waits.smallWait().until(webDriver -> driver.getTitle().equals(title));
    }

    public boolean titleToContain(String title){
        return waits.smallWait().until(webDriver -> driver.getTitle().contains(title));
    }

    public WebElement presence(By locator) {
        try {
            return waits.smallWait().until(presenceOfElementLocated(locator));
        } catch (RuntimeException e) {
            LOG.info("Element " + locator + " not found");
        }
        return null;
    }
    public boolean presenceOf(By... locators) {
        Iterator<By> iterator = Stream.of(locators).iterator();
        List<WebElement> elements = new ArrayList<>();
        while (iterator.hasNext()) {
            elements.add(presence(iterator.next()));
        }

        return hasElements(elements);
    }

    private boolean hasElements(List<WebElement> webElements) {
        webElements.remove(null);
        return !webElements.isEmpty();
    }


    public boolean isVisible(WebElement... webElements) throws Exception {
        return hasElements(visibilityOf(webElements));
    }

    public WebElement clickable(WebElement webElement) {
        return waits.smallWait().until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
