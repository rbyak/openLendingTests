package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * A class encapsulating all WebDriverWait functionality.
 */
public final class Waits {

    public static final Duration SMALL_WAIT = Duration.ofSeconds(30);
    public static final Duration LARGE_WAIT = Duration.ofSeconds(60);

    private final WebDriverWait smallWait;
    private final WebDriverWait largeWait;

    public Waits(WebDriver driver) {
        this.smallWait = new WebDriverWait(driver, SMALL_WAIT);
        this.largeWait =  new WebDriverWait(driver, LARGE_WAIT);
    }

    public WebDriverWait smallWait() {
        return smallWait;
    }

    public WebDriverWait largeWait() {
        return largeWait;
    }
}